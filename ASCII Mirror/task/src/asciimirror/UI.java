package asciimirror;

import java.util.Scanner;

public class UI {
    private Scanner scanner;

    public UI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        Animal animal = new Animal("cow");
        animal.printAnimal();
    }
}