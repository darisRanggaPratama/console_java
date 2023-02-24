package MVC2;

import java.util.Scanner;

class Views {
    public void displayData(String[] data) {
        System.out.println("Data: ");
        for (String datum : data) {
            System.out.println(datum);
        }
    }

    public String[] getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter data (comma-separated): ");
        String input = scanner.nextLine();
        return input.split(",");
    }
}

