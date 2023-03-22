public class Main {

    public static void main(String[] args) {
        int counter = 0;

        // write your code here
        String str = "STAR";
        for ( Secret secret : Secret.values()) {
            if (secret.toString().substring(0,4).equals(str)) {
                counter++;
            }
        }

        System.out.println(counter);
    }
}

/* sample enum for inspiration
enum Secret {
    STAR, CRASH, START, // ...
}
*/

