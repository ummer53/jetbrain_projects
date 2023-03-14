package asciimirror;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import  java.io.File;;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException,FileNotFoundException, NoSuchFileException,InvalidPathException {
       Scanner scanner = new Scanner(System.in);
//        System.out.println("Input the file path:");
//        String str=scanner.nextLine();
//        System.out.println(str);
//        UI userInterface = new UI(scanner);
//        userInterface.start();
        System.out.println("Input the file path:");
        String pathToFile=scanner.next();
        File file=null;
        int length=0;
        try{
            file=new File(pathToFile);
            Scanner s = new Scanner(file);
            String str="";

            while(s.hasNext()){
                String strTemp=s.nextLine();
                if(strTemp.length()>str.length()){
                    str=strTemp;
                    length=str.length();
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found! ");
        }
        ArrayList<String> list=processFile2(file,length);
        for(String item:list){
            System.out.println(item);
        }


    }
    public static String readFileAsString(String fileName) throws IOException,AccessDeniedException{
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        }catch(AccessDeniedException e)
        {
            System.out.println("File not found!");
        }
        return "";
    }

    private static ArrayList processFile(File file,int length) throws  NoSuchFileException {
        ArrayList <String> animal=new ArrayList<>();
        String str="";
        try{
            Scanner s=new Scanner(file);
            while(s.hasNext()){
                str=s.nextLine();
                if(str.length()<length){
                    for(int i=str.length();i<length;i++){
                        str=str+" ";
                    }
                }
                str=str+" | "+str;
                animal.add(str);
            }
        } catch (FileNotFoundException e) {

        }
        return animal;
    }
    private static ArrayList processFile2(File file,int length) throws  NoSuchFileException {
        ArrayList <String> animal=new ArrayList<>();
        String str="";
        try{
            Scanner s=new Scanner(file);
            while(s.hasNext()){
                str=s.nextLine();
                if(str.length()<length){
                    for(int i=str.length();i<length;i++){
                        str=str+" ";
                    }
                }
                String str1=reverse(str);
                str=str+" | "+str1;
                animal.add(str);
            }
        } catch (FileNotFoundException e) {

        }
        return animal;
    }
    private static  String reverse(String str){
        String str1="";
        for(int i=str.length()-1;i>=0;i--){
            char c=str.charAt(i);
            if(c=='('){
                c=')';
            } else if (c==')') {
                c='(';
            } else if (c=='\\') {
                c='/';
            } else if (c=='/') {
                c='\\';
            } else if (c=='<') {
                c='>';
            } else if (c=='>') {
                c='<';
            } else if (c=='[') {
                c=']';
            } else if (c==']') {
                c='[';
            }
            str1=str1+c;
        }
        return  str1;
    }
}