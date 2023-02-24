package MVC1;

import java.util.Scanner;

class View {
    public void displayName(String name) {
        System.out.println("Name: " + name);
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        return scanner.nextLine();
    }
}
