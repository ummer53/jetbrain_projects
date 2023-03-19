package numbers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    private static boolean status = true;
    private static final Scanner scanner = new Scanner(System.in);
    private static  String[] properties = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY"};
    private static  String[] methods = {"isEven", "isOdd", "isBuzz", "isDuck", "isPalindromic", "isGapful", "isSpy", "isSquare", "isSquare"};



    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        printWelcomeAndInstructions();

        while (status) {
            System.out.print("Enter a request: ");
            String[] request = scanner.nextLine().split(" ");

            if (validateInput(request)) {
                if (request.length == 1) {
                    printPropertiesNumber(request[0]);
                } else if (request.length == 2) {
                    for (int i = 0; i < Long.parseLong(request[1]); i++) {
                        printPropertiesNumberOneLine(i + Long.parseLong(request[0]));
                    }
                } else if (request.length == 3) {
                    Class<?>[] parameters = new Class<?>[]{String.class};
                    String methodName = "is" + request[2].substring(0, 1).toUpperCase() + request[2].substring(1).toLowerCase();
                    Method method = Main.class.getMethod(methodName, parameters);
                    numbersWithThisProperty(method, request);
                } else {
                    Class<?>[] parameters = new Class<?>[]{String.class};
                    String methodName1 = "is" + request[2].substring(0, 1).toUpperCase() + request[2].substring(1).toLowerCase();
                    String methodName2 = "is" + request[3].substring(0, 1).toUpperCase() + request[3].substring(1).toLowerCase();
                    Method method1 = Main.class.getMethod(methodName1, parameters);
                    Method method2 = Main.class.getMethod(methodName2, parameters);
                    if (checkProperties(methodName1,methodName2)) {
                        if (mutuallyExclusive(methodName1, methodName2)) {
                            warningMessage(methodName1, methodName2);
                            continue;
                        } else {
                            numbersWithTheseProperty(method1, method2, request);
                        }
                    }
                }
            }
        }
    }

    private static boolean checkProperties(String methodName1, String methodName2) {
        String message = "";
        boolean property= false;
        if (!contains(methodName1) && !contains(methodName2)) {
            message = String.format("The properties [%s, %s] are wrong.%n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]",
                    methodName1.substring(2).toUpperCase(),methodName2.substring(2).toUpperCase());
        } else if (!contains(methodName1)) {
            message = String.format("The property [%s] is wrong.%n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]",
                    methodName1.substring(2).toUpperCase());
        } else if (!contains(methodName2)) {
            message = String.format("The property [%s] is wrong.%n" +
                            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]",
                    methodName2.substring(2).toUpperCase());
        }
        else {
            property=true;
        }
        System.out.println(message);
        return property;
    }

    private static boolean contains(String method) {
        String property = method.substring(2).toUpperCase();
        for ( String str : properties) {
            if ((str.toUpperCase()).equals(property)) {
                return true;
            }
        }
        return false;
    }

    private static void numbersWithTheseProperty(Method method1, Method method2, String[] request) throws InvocationTargetException, IllegalAccessException {
        int count = 0;
        long numberIncrement = Long.parseLong(request[0]);
        while (count < Long.parseLong(request[1])) {
            if ((boolean) method1.invoke(null, String.valueOf(numberIncrement)) &&
                    (boolean) method2.invoke(null, String.valueOf(numberIncrement))) {
                printPropertiesNumberOneLine(numberIncrement);
                count++;
            }else {

            }
            numberIncrement++;
        }
    }

    private static void warningMessage(String method1, String method2) {
        String warning= String.format("The request contains mutually exclusive properties: [%s, %s]%nThere are no numbers with these properties.",
                method1.substring(2).toUpperCase(),method2.substring(2).toUpperCase());
        System.out.println(warning);
    }

    private static boolean mutuallyExclusive(String method1, String method2) {
        if (method1.equals("isEven") && method2.equals("isOdd") || method2.equals("isEven") && method1.equals("isOdd")) {
            return true;
        } else if (method1.equals("isDuck") && method2.equals("isSpy") || method2.equals("isDuck") && method1.equals("isSpy")) {
            return true;
        } else if (method1.equals("isSunny") && method2.equals("isSquare") || method2.equals("isSunny") && method1.equals("isSquare")) {
            return true;
        }
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
                - two natural numbers and a property to search for;
                - two natural numbers and two properties to search for;
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
    }

    private static void printPropertiesNumberOneLine(long i) {
        System.out.printf("%s is %s%s%s%s%s%s%s%s%n", i,
                isBuzz(String.valueOf(i)) ? "buzz, " : "",
                isDuck(String.valueOf(i)) ? "duck, " : "",
                isPalindromic(String.valueOf(i)) ? "palindromic, " : "",
                isGapful(String.valueOf(i)) ? "gapful, " : "",
                isSpy(String.valueOf(i)) ? "spy, " : "",
                isEven(String.valueOf(i)) ? "even, " : "odd, ",
                isSquare(String.valueOf(i)) ? "square, " : "",
                isSunny(String.valueOf(i)) ? "sunny, " : "");
    }

    private static void numbersWithThisProperty(Method method, String[] request) throws InvocationTargetException, IllegalAccessException {
        int count = 0;
        long numberIncrement = Long.parseLong(request[0]);
        while (count < Long.parseLong(request[1])) {
            if ((boolean) method.invoke(null, String.valueOf(numberIncrement))) {
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
            return isNatural(input[0], 0) && isNatural(input[1], 1) && isProperty(input[2]);
        } else if (input.length==4) {

            return isNatural(input[0], 0) && isNatural(input[1], 1) && checkProperties(
                    "is" + input[2].substring(0,1).toLowerCase() + input[2].substring(1),
                    "is" + input[3].substring(0,1).toLowerCase() + input[3].substring(1));
        }
        return false;
    }

    private static boolean isProperty(String property) {
        property = property.toLowerCase();
        boolean isProperty = property.equals("even") || property.equals("odd") || property.equals("buzz") || property.equals("duck") || property.equals("palindromic") || property.equals("gapful") || property.equals("spy") ||
                property.equals("sunny") || property.equals("square") ;
        if (!isProperty) {
            System.out.println("The property [" + property + "] is wrong.\n" +
                               "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SUNNY, SQUARE]");
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

}
