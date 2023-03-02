
import java.util.Scanner;
 class Cinema {


    public static void print(char[][] cinema){

        int rows=cinema.length;
        int seats=cinema[1].length;
        System.out.println("Cinema:");
        System.out.print("  ");
        for(int i=1;i<=seats;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i=0;i<rows;i++){
            for(int j=0;j<seats;j++){
                if(j==0){
                    System.out.print(i+1+" ");
                }
                System.out.print(cinema[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static char[][] setCinema(char[][] cinema,int row,int seat){

        if(cinema.length*cinema[1].length>60&&row>cinema.length/2)
            System.out.println("Ticket price: $8");
        else
            System.out.println("Ticket price: $10");
        // System.out.println("Cinema:");
        // System.out.print("  ");
        // for(int i=1;i<=cinema[i].length;i++){
        //     System.out.print(i+" ");
        // }
        System.out.println();
        for(int i=0;i<cinema.length;i++){
            for(int j=0;j<cinema[i].length;j++){
                if(i==row-1&&j==seat-1)
                    cinema[i][j]='B';
            }
            // System.out.println();
        }
        return cinema;
    }
    public static void statistics(int pT,char[][] cinema){
        int rows=cinema.length;
        int seats=cinema[1].length;
        int tS=rows*seats;
        int tI=rows*seats>60?rows/2*seats*10+(rows-rows/2)*seats*8:rows*seats;
        int cI=0;
        float p=((float)pT/tS)*100;
        for(int i=0;i<cinema.length;i++){
            for(int j=0;j<cinema[i].length;j++){
                if(cinema[i][j]=='B'&&(i+1)<=rows/2)
                    cI+=10;
                else if(cinema[i][j]=='B'&&(i+1)>rows/2)
                    cI+=8;

            }
            // System.out.println();
        }
        System.out.printf("Number of purchased tickets: %d%nPercentage: %.2f%c %nCurrent income: $%d%nTotal income: $%d%n",pT,p,'%',cI,tI);

    }

public static void main(String[] args){
        // Write your code here
        Scanner s=new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows=s.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats=s.nextInt();
        int purchasedTickets=0;
        char[][] cinema=new char[rows][seats];
        for(int i=0;i<cinema.length;i++){
            for(int j=0;j<cinema[i].length;j++){
                cinema[i][j]='S';
            }
        }
        while(true){
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice =s.nextInt();
            if(choice==1){
                print(cinema);
            }
            else if(choice==2){
                System.out.println("Enter a row number:");
                int row=s.nextInt();
                System.out.println("Enter a seat number in that row:");
                int seat=s.nextInt();
                if(row>rows||seat>seats){
                    System.out.println("Wrong input!");
                }else if(cinema[row-1][seat-1]=='B'){
                    System.out.println("That ticket has already been purchased");
                    while(cinema[row-1][seat-1]=='B'){
                        System.out.println("Enter a row number:");
                        row=s.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seat=s.nextInt();
                    }
                    cinema=setCinema(cinema,row,seat);
                    purchasedTickets++;
                }
                else{
                    cinema=setCinema(cinema,row,seat);
                    purchasedTickets++;
                }

            }
            else if(choice==3){
                statistics(purchasedTickets,cinema);
            }
            else if(choice==0)
                break;
            else{
                System.out.println("Wrong input!");
            }

            // System.out.println("Total income:");
            // if(rows*seats<=60)
            //     System.out.println("$"+rows*seats*10);
            // else{
            //     int total=rows/2*seats*10+(rows-rows/2)*seats*8;
            //     System.out.println("$"+total);
            // }

        }


    }
}