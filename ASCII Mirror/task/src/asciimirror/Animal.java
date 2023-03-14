package asciimirror;

public class Animal {
    private final String animal;

    public Animal(String animal) {
        this.animal = animal.toLowerCase();
    }

    public void printAnimal() {
        if (this.animal.equals("cow")) {
            printCow();
        } else {
            System.out.println("No such animal exists!");
        }
    }

    private void printCow() {
        System.out.print("            ^__^\n" +
                "    _______/(oo)\n" +
                "/\\/(       /(__)\n" +
                "   | w----||    \n" +
                "   ||     ||    ");
    }
}