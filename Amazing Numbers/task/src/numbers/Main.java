package numbers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    private static boolean status = true;
    private static final Scanner scanner = new Scanner(System.in);
    private static  String[] properties = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD"};
    private static List<String>  props;
    private static  String[] methods = {"isEven", "isOdd", "isBuzz", "isDuck", "isPalindromic", "isGapful", "isSpy", "isSquare", "isSquare", "isJumping", "isHappy", "isSad"};
    private static List<Method> mets;
    private static Map<String, String> exclusive;




    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /*
        Map to take care of Mutually Exclusive properties
         */
        printWelcomeAndInstructions();
        exclusive=new HashMap<>();
        exclusive=new HashMap<>();
        for (String prop : properties) {
            exclusive.put(prop, ("-" + prop));
        }
        exclusive.put("HAPPY", "SAD");
        exclusive.put("ODD", "EVEN");
        exclusive.put("SUNNY", "SQUARE");
        exclusive.put("DUCK", "SPY");
        exclusive.put("-HAPPY", "-SAD");
        exclusive.put("-ODD", "-EVEN");
       // exclusive.put("-SUNNY", "-SQUARE");
      //  exclusive.put("-DUCK", "-SPY");
//        exclusive.put("isEven", "isOdd");
//        exclusive.put("isSunny", "isSquare");
//        exclusive.put("isDuck", "isSpy");

        props = new ArrayList<>();
        for (String p : properties) {
            props.add(p);
        }

        Class<?>[] parameters = new Class<?>[]{String.class};

/*
while loop is set to let the user run the program as many times as user wants
status true means loop will run one more time
status false means loop will terminate
once user enters the loop will terminate only after entering 0 as first input
 */

        while (status) {
            boolean negate = false;   // to check whether - is written in with property name
            String methodName = "";   // to extract method name from property
            System.out.print("Enter a request: ");
            String[] request = scanner.nextLine().split(" ");     // take request input from user and convert it into array
/*
 First step is to check for valid input:
 which contains different procedures for different length of input request array
 */
            if (validateInput(request)) {
                if (request.length == 1) {      // if only  1 no. is entered all properties  of that number will be displayed as true of false
                    printPropertiesNumber(request[0]);
                } else if (request.length == 2) {
                    for (int i = 0; i < Long.parseLong(request[1]); i++) {
                        printPropertiesNumberOneLine(i + Long.parseLong(request[0]));
                    }
                } else if (request.length == 3) {
                   // Class<?>[] parameters = new Class<?>[]{String.class};
                    if (request[2].charAt(0) == '-') {
                        negate = true;
                        methodName = "is" + request[2].substring(1, 2).toUpperCase() + request[2].substring(2).toLowerCase();
                    }
                    else {
                        methodName = "is" + request[2].substring(0, 1).toUpperCase() + request[2].substring(1).toLowerCase();
                    }
                    Method method = Main.class.getMethod(methodName, parameters);
                    numbersWithThisProperty(method, request, negate);
                } else if(request.length > 3) {
                   // Class<?>[] parameters = new Class<?>[]{String.class};
                    List<Method> methodList=new ArrayList<>();
                    for (int i = 2; i < request.length; i++) {
                        if (request[i].charAt(0) == '-') {
                            methodName = "isNot" + request[i].substring(1, 2).toUpperCase() + request[i].substring(2).toLowerCase();
                            negate = true;
                        }
                        else {
                            methodName = "is" + request[i].substring(0, 1).toUpperCase() + request[i].substring(1).toLowerCase();
                        }
                        methodList.add(Main.class.getMethod(methodName, parameters));
                    }
                        if (mutuallyExclusive(request)) {
                            continue;
                        } else {
                            numbersWithTheseProperty(methodList, request);
                        }
                    }
                }
            }
        }


    private static void numbersWithTheseProperty(List<Method> methodList, String[] request) throws InvocationTargetException, IllegalAccessException {
        int count = 0;
        //boolean invoke = true;
        long numberIncrement = Long.parseLong(request[0]);
        while (count < Long.parseLong(request[1])) {
            boolean invoke = true;
            for (Method method : methodList) {
                invoke = invoke && (boolean) method.invoke(null, String.valueOf(numberIncrement));
            }
            if (invoke) {
                printPropertiesNumberOneLine(numberIncrement);
                count++;
            }
            numberIncrement++;
        }
    }


    private static boolean mutuallyExclusive(String[] request) {
        for (int i = 2; i < request.length; i++) {
            String property1 = request[i].toUpperCase();
            for (int j = i; j < request.length; j++) {
                String property2 = request[j].toUpperCase();
                if (property1.equals(exclusive.get(property2))
                 || property2.equals(exclusive.get(property1)) ||
                        property2.equals("-" + property1) ||
                property1.equals("-" + property2)){
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                                    "There are no numbers with these properties.%n",property1.toUpperCase(), property2.toUpperCase());
                    return true;
                }
            }
        }
//        for (int j = 0; j < methodList.size(); j++) {
//        String method1 = methodList.get(j).getName();
//            for (int i = j; i < methodList.size(); i++) {
//                String method2 = methodList.get(i).getName();
//                if (method1.equals(exclusive.get(method2)) || method2.equals(exclusive.get(method1))) {
//                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
//                                    "There are no numbers with these properties.",
//                            method1.substring(2).toUpperCase(), method2.substring(2).toUpperCase());
//                    return true;
//                }
//            }
//        }
        return false;
    }

    private static void printWelcomeAndInstructions() {
        System.out.println("""
                Welcome to Amazing Numbers!
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameters show how many consecutive numbers are to be processed;
                - two natural numbers and a properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.""");
    }

    private static void printPropertiesNumber(String number) {
        System.out.println("Properties of " + number);
        System.out.println("        buzz: " + isBuzz(number));
        System.out.println("        duck: " + isDuck(number));
        System.out.println(" palindromic: " + isPalindromic(number));
        System.out.println("      gapful: " + isGapful(number));
        System.out.println("         spy: " + isSpy(number));
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
        System.out.println("      square: " + isSquare(number));
        System.out.println("       sunny: " + isSunny(number));
        System.out.println("     jumping: " + isJumping(number));
        System.out.println("       happy: " + isHappy(number));
        System.out.println("         sad: " + !isHappy(number));
    }

    private static void printPropertiesNumberOneLine(long i) {
        System.out.printf("%s is %s%s%s%s%s%s%s%s%s%s%n", i,
                isBuzz(String.valueOf(i)) ? "buzz, " : "",
                isDuck(String.valueOf(i)) ? "duck, " : "",
                isPalindromic(String.valueOf(i)) ? "palindromic, " : "",
                isGapful(String.valueOf(i)) ? "gapful, " : "",
                isSpy(String.valueOf(i)) ? "spy, " : "",
                isEven(String.valueOf(i)) ? "even, " : "odd, ",
                isSquare(String.valueOf(i)) ? "square, " : "",
                isSunny(String.valueOf(i)) ? "sunny, " : "",
                isJumping(String.valueOf(i)) ? "jumping, " : "",
                isHappy(String.valueOf(i))  ? "happy " : "sad");
    }

    private static void numbersWithThisProperty(Method method, String[] request, boolean negate) throws InvocationTargetException, IllegalAccessException {
        int count = 0;
        long numberIncrement = Long.parseLong(request[0]);
        while (count < Long.parseLong(request[1])) {
            if (negate == false &&  (boolean) method.invoke(null, String.valueOf(numberIncrement))) {
                printPropertiesNumberOneLine(numberIncrement);
                count++;
            } else if (negate == true && !(boolean) method.invoke(null, String.valueOf(numberIncrement))) {
                printPropertiesNumberOneLine(numberIncrement);
                count++;
            }
            numberIncrement++;
        }
    }

    private static boolean validateInput(String[] input) {
        if (input.length == 1) {
            if (input[0].equals("0")) {
                System.out.println("Goodbye!");
                status = false;
                return false;
            }
            return isNatural(input[0], 0);
        } else if (input.length == 2) {
            return isNatural(input[0], 0) && isNatural(input[1], 1);
        } else if (input.length == 3) {
            boolean valid = true;
            if (input[2].charAt(0) == '-'){
                valid = isNatural(input[0], 0) && isNatural(input[1], 1) && isProperty(input[2].substring(1));
                if (!isProperty(input[2].substring(1))) {
                    System.out.printf("The property [" + input[2].toUpperCase() + "] is wrong.\n" +
                            "Available properties:\n" + props + "%n");
                }
                return valid;
            }
            valid = isNatural(input[0], 0) && isNatural(input[1], 1) && isProperty(input[2]);
            if (!isProperty(input[2])) {
                System.out.printf("The property [" + input[2].toUpperCase() + "] is wrong.\n" +
                        "Available properties:\n" + props + "%n");
            }
            return valid;
        } else if (input.length > 3) {
            List<String> inValid = new ArrayList<>();   // to store invalid properties
            boolean isValidInput= true;
            if (!isNatural(input[0], 0))
                return false;
            if (!isNatural(input[1], 1))
                return false;
            for (int i = 2; i < input.length; i++) {
                inValid.add(input[i].toUpperCase());
                if (input[i].charAt(0) == '-'){
                    if (isProperty(input[i].substring(1))) {
                        inValid.remove(input[i].toUpperCase());
                    }
                    isValidInput &= isProperty(input[i].substring(1));
                }
                else {
                    if (isProperty(input[i].substring(0))) {
                        inValid.remove(input[i].toUpperCase());
                    }
                    isValidInput &= isProperty(input[i]);
                }
            }
            if (isValidInput) {
                return true;
            }
            if (inValid.size() == 1) {
                System.out.printf("The property " + inValid + " is wrong.%n" +
                        "Available properties: %n" + props + "%n");
                return false;
            }
            if (inValid.size() > 1) {

                System.out.printf("The properties " + inValid + " are wrong.\n" +
                        "Available properties:\n" + props + "%n");
            }
            return isValidInput;
        }
        return false;
    }
    private static boolean isProperty(String property) {
        property = property.toUpperCase();
        boolean isProperty = false;
        for (String prop : properties) {
            if ( prop.equals(property)) {
                return true;
            }
        }
        return isProperty;
    }

    private static boolean isNatural(String number, int position) {
        boolean isNatural = Long.parseLong(number) > 0;
        if (!isNatural) {
            System.out.printf("The %s parameter should be a natural number%s%n", position == 0 ? "first" : "second", position == 1 ? "." : " or zero.");
        }
        return isNatural;
    }













    public static boolean isEven(String number) {
        return Long.parseLong(number) % 2 == 0;
    }

    public static boolean isOdd(String number) {
        return !isEven(number);
    }
    public static boolean isBuzz(String number) {
        return Long.parseLong(number) % 7 == 0 || Long.parseLong(number) % 10 == 7;
    }

    public static boolean isDuck(String number) {
        return String.valueOf(number).contains("0");
    }

    public static boolean isPalindromic(String number) {
        String numberString = String.valueOf(number);
        return numberString.equals(new StringBuilder(numberString).reverse().toString());
    }

    public static boolean isGapful(String number) {
        return number.length() > 2 && Long.parseLong(number) % Long.parseLong(number.charAt(0) + "" + number.charAt(number.length() - 1)) == 0;
    }

    public static boolean isSpy(String number) {
        String numberString = String.valueOf(number);
        int sum = 0;
        int product = 1;
        for (int i = 0; i < numberString.length(); i++) {
            int digit = Character.getNumericValue(numberString.charAt(i));
            sum += digit;
            product *= digit;
        }
        return sum == product;
    }

    public static boolean isSquare(String number) {
        return ((long)Math.sqrt(Double.valueOf(number))) * ((long)Math.sqrt(Double.valueOf(number))) == Long.valueOf(number);
    }

    public static boolean isSunny(String  number) {
        number= String.valueOf((Long.valueOf(number)+1));
        return isSquare(number);
    }

    public static boolean isJumping(String number) {
        char[] charArray = number.toCharArray();
        char temp = charArray[0];
        for (int i = 1; i < charArray.length; i++ ) {
            if (Math.abs(charArray[i] - temp) != 1) {
                return false;
            }
            else {
                temp = charArray[i];
            }
        }
        return true;
    }

    public static boolean isHappy(String number) {
        long num = Long.valueOf(number);
        char[] charArray = number.toCharArray();
        if (num == 1) {
            return true;
        }
        while (num / 10 > 0 ) {
            long sum = 0;
            for (char c : charArray) {
                sum += (Long.valueOf(c - '0')) * (Long.valueOf(c - '0'));
            }
            if (sum == 1 ) {
                return true;
            }
            else {
                num = sum;
                number = String.valueOf(num);
                charArray = number.toCharArray();
            }
        }
        return num == 1 || num == 7;
    }

    public static boolean isNotEven(String number) {
        return !isEven(number);
    }

    public static boolean isNotOdd(String number) {
        return !isOdd(number);
    }
    public static boolean isNotBuzz(String number) {
        return !isBuzz(number);
    }

    public static boolean isNotDuck(String number) {
        return !isDuck(number);
    }

    public static boolean isNotPalindromic(String number) {
        return !isPalindromic(number);
    }

    public static boolean isNotGapful(String number) {
        return !isGapful(number);
    }

    public static boolean isNotSpy(String number) {
        return !isSpy(number);
    }

    public static boolean isNotSquare(String number) {
        return !isSquare(number);
    }

    public static boolean isNotSunny(String  number) {
        return !isSunny(number);
    }

    public static boolean isNotJumping(String number) {
        return !isJumping(number);
    }

    public static boolean isNotHappy(String number) {
        return !isHappy(number);
    }

    public static boolean isSad(String number) {
        return !isHappy(number);
    }

    public static boolean isNotSad(String number) {
        return !isSad(number);
    }


}
