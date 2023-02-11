package ArrayBsc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Main {
    // Membuat array dengan 5 elemen
    // Array integer
    private static int[] numbers = new int[5];
    // Array String
    private static String[] words = new String[5];
    // ArrayLIst
    private static ArrayList<Integer> alist = new ArrayList<Integer>();

    public static void main(String[] args) {
        valueArray();
        showArrayNumber();
        arrayNumberShow();
        getItemArrayNumber(2);
        arrayNumberDelete(3);
        arrayNumberDeleteOtherWay(2);
        arrayNumberDeleteAll();
        arrayNumberDeleteAllOtherWay();

        arrayStringShow();
        showArrayString();
        editArrayString(4, "kontras");
        arrayStringAscending();
        arrayStringDescending();
    }

    private static void valueArray() {
        // Mengisi elemen array (angka) dengan nilai
        numbers[0] = 1;
        numbers[1] = 200;
        numbers[2] = 30;
        numbers[3] = 4000;
        numbers[4] = 15;

        // Array (string)
        words[0] = "rangga";
        words[1] = "antika";
        words[2] = "nizham";
        words[3] = "haifa";
        words[4] = "oyen";
    }

    private static void showArrayNumber() {
        // Menampilkan elemen array 1 per 1
        System.out.println("\nMenampilkan elemen array 1 per 1:");
        for (int x = 0; x < numbers.length; x++) {
            System.out.println(x + " " + numbers[x]);
        }
    }

    private static void arrayNumberShow() {
        System.out.println("\nMenampilkan elemen array 1 per 1(foreach):");
        for (int number : numbers) {
            System.out.println(" " + number);
        }
    }

    private static void getItemArrayNumber(int index) {
        System.out.println("\nAmbil 1 item di index " + index + ": " + numbers[index]);
    }

    private static void arrayNumberDelete(int index) {
        showArrayNumber();

        // Create ArrayList from Array
        System.out.println("\nCreate ArrayList from Array.");
        for (int number : numbers) {
            alist.add(number);
        }

        System.out.println("\nArrayList item: ");
        for (int x = 0; x < alist.size(); x++) {
            System.out.println(x + " " + alist.get(x));
        }

        // Delete 1 item
        alist.remove(index);
        System.out.println("\nHapus elemen index: " + index);

        System.out.println("\nArrayList item. Setelah dihapus: ");
        for (int x = 0; x < alist.size(); x++) {
            System.out.println(x + " " + alist.get(x));
        }

        // Create Array from ArrayList
        System.out.println("\nCreate Array from ArrayList.");
        numbers = new int[4];
        for (int y = 0; y < alist.size(); y++) {
            numbers[y] = alist.get(y);
        }

        showArrayNumber();
    }

    private static void arrayNumberDeleteOtherWay(int index) {
        showArrayNumber();

        int[] number = new int[numbers.length - 1];
        for (int x = 0, y = 0; x < numbers.length; x++) {
            if (x != index) {
                number[y] = numbers[x];
                y++;
            }
        }

        numbers = number;

        System.out.println("\nHapus elemen index: " + index + ". Cara lain: ");

        showArrayNumber();
    }

    private static void arrayNumberDeleteAll() {
        showArrayNumber();
        Arrays.fill(numbers, 0);
        System.out.println("\nDelete all array item.");
        showArrayNumber();
    }

    private static void arrayNumberDeleteAllOtherWay() {
        showArrayNumber();
        numbers = new int[0];
        System.out.println("\nDelete all array item.");
        showArrayNumber();
    }

    private static void arrayStringShow() {
        System.out.println("\nMenampilkan elemen array 1 per 1(foreach):");
        for (String word : words) {
            System.out.println(" " + word);
        }
    }

    private static void showArrayString() {
        // Menampilkan elemen array 1 per 1
        System.out.println("\nMenampilkan elemen array 1 per 1:");
        for (int x = 0; x < words.length; x++) {
            System.out.println(x + " " + words[x]);
        }
    }

    private static void editArrayString(int index, String value) {
        showArrayString();
        words[index] = value;
        System.out.println("\nEdit index: " + index + ". Value: " + value);
        showArrayString();
    }

    private static void arrayStringAscending() {
        showArrayString();
        Arrays.sort(words);
        System.out.println("\nSort Ascending");
        showArrayString();
    }

    private static void arrayStringDescending() {
        showArrayString();
        Arrays.sort(words, Collections.reverseOrder());
        System.out.println("\nSort Descending");
        showArrayString();
    }
}