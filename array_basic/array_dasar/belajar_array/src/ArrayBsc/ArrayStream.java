package ArrayBsc;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ArrayStream {
    // Ciptakan Array
    // Array integer: numbers
    private static int[] numbers = new int[10];

    // Array String: words
    private static String[] words = new String[11];
    private static Random random = new Random();

    public static void main(String[] args) {
        // Set array value
        setIntegerArrayValue(0, 1000);
        setStringArrayValue();

        // Get data kurang dari nilai tertentu
        getLessThan(500);
        // Get nilai tertentu dan ganjil
        getNumberOverAndOdd(500);
        // Get nilai tertentu
        getNumberValue(10);

        // Get value dengan jumlah kata tertentu
        getMoreWords(3);
        // Get huruf pertama
        getFirstLetter("e");
        // Get Kata
        getWord("al");
        // Get Kata terakhir
        getLastWord("ackerman");



    }

    private static void setStringArrayValue() {
        words[0] = "raden rangga pratama";
        words[1] = "haifa azzura";
        words[2] = "muhammad nizham al Islam";
        words[3] = "antika";
        words[4] = "armin";
        words[5] = "eren yeager";
        words[6] = "levi ackerman";
        words[7] = "mikasa ackerman";
        words[8] = "mochi";
        words[9] = "panther";
        words[10] = "oyen indra praja";
    }

    private static void showFullStringArray() {
        // Sort: Ascending. Dengan stream
        String[] ascending = Arrays.stream(words)
                                    .sorted()
                                    .toArray(String[]::new);

        System.out.println("\nTampilkan seluruh nama(Ascending): ");
        for (String name : ascending) {
            System.out.println(name);
        }

        // Sort: Descending. Dengan stream
        String[] descending = Arrays.stream(words)
                                    .sorted((x, y) -> y.compareTo(x))
                                    .toArray(String[]::new);

        System.out.println("\nTampilkan seluruh nama(Descending): ");
        for (String name : descending) {
            System.out.println(name);
        }
    }

    private static void getMoreWords(int value) {
        showFullStringArray();
        // Get nama dengan suku kata tertentu
        System.out.println("\nNama dengan jumlah suku kata: " + value);
        String[] getMoreWords = Arrays.stream(words)
                                        .filter(x -> x.split(" ").length == value)
                                        .toArray(String[]::new);

        for (String words : getMoreWords) {
            System.out.println(words);
        }
    }

    private static void getFirstLetter(String value){
        String[] firstLetter = Arrays.stream(words)
                                    .filter(x -> x.startsWith("e"))
                                    .toArray(String[]::new);

        System.out.println("\nKata diawali huruf: " + value);
        for (String word : firstLetter) {
            System.out.println(word);
        }
    }

    private static void getWord(String value){
        Stream<String> getWordStream = Arrays.stream(words)
                                            .filter(str -> str.contains(value));

        System.out.println("\nKata: " + value + " ditemukan: ");

        getWordStream.forEach(System.out::println);
    }

    private static void getLastWord(String value){
        Stream<String> lastWordStream = Arrays.stream(words)
                                                .filter(str -> str.endsWith("ackerman"));
        System.out.println("\nSuku kata terakhir ditemukan: " +  value);
        lastWordStream.forEach(System.out::println);
    }

    private static void setIntegerArrayValue(int first, int last) {
        for (int x = first; x < numbers.length; x++) {
            numbers[x] = random.nextInt(last);
        }
    }

    // Display full array item
    private static void showFullIntegerArray() {
        System.out.println("\n\nSemua elemen array: ");
        // Sort: Ascending
        Arrays.sort(numbers);
        // Sort: Descending
        int[] descendingArray = IntStream.range(0, numbers.length)
                                            .map(i -> numbers[numbers.length - i - 1])
                                            .toArray();
        for (int number : descendingArray) {
            System.out.print(number + " ");
        }
    }

    private static void getLessThan(int value) {
        showFullIntegerArray();

        int[] lessThan = IntStream.of(numbers)
                                    .filter(x -> x < value)
                                    .toArray();

        System.out.println("\n\nAmbil elemen array kurang dari: " + value);

        for (int number : lessThan) {
            System.out.print(number + " ");
        }
    }

    private static void getNumberOverAndOdd(int value){
       showFullIntegerArray();

        System.out.println("\n\nElemen array bernilai ganjil & lebih dari: " + value);
       int[] overAndOdd = Arrays.stream(numbers)
               .filter(n -> n > value && n % 2 != 0)
               .toArray();

       for(int number : overAndOdd){
           System.out.print(number + " ");
       }
    }

    private static void getNumberValue(int value) {
        int[] getNumber = IntStream.of(numbers)
                                    .filter(x -> x == value)
                                    .toArray();

        if (getNumber.length > 0) {
            System.out.println("\n\nNilai array: " + getNumber[0] + " ditemukan");
        } else {
            System.out.println("\n\nTidak ada nilai: " + value + " di array");
        }
    }
}
/*
 * Penjelasan algoritma di atas:

 * setIntegerArrayValue(int first, int last)
 * 1. Membuat array dengan kapasitas 10 dan mengisi dengan nilai random antara 0 sampai 1000.
 * a. Melakukan impor package java.util.Random untuk dapat menggunakan kelas Random yang dibutuhkan untuk menghasilkan nilai acak.
 * b. Membuat array numbers dengan kapasitas 10.
 * c. Membuat objek random dari kelas Random.
 * d. Melakukan perulangan sebanyak panjang array numbers, dimana setiap elemen diisi dengan nilai acak antara 0 sampai 1000 menggunakan method nextInt() dari objek random.
 *
 * showFullIntegerArray()
 * 2. Mengurutkan semua nilai elemen array secara descending.
 * a. Melakukan impor package java.util.Arrays untuk dapat menggunakan method sort() yang berguna untuk mengurutkan array.
 * b. Melakukan pengurutan array numbers menggunakan method sort() dari kelas Arrays.
 * c. Membuat array baru descendingArray dengan panjang yang sama dengan array numbers.
 * d. Menggunakan stream IntStream untuk membuat index yang akan digunakan untuk mengakses elemen array numbers secara terbalik (terbesar ke terkecil).
 * e. Mengisi setiap elemen pada descendingArray dengan elemen pada array numbers yang diakses secara terbalik menggunakan index yang dibuat pada stream.
 *
 * getLessThan(int value)
 * 3. Mengambil elemen array yang bernilai kurang dari 500 menggunakan stream.
 * a. Menggunakan method of() dari kelas IntStream untuk membuat stream dari array descendingArray.
 * b. Melakukan operasi filtering menggunakan method filter() untuk menyaring elemen yang nilainya kurang dari 500.
 *
 * getNumberValue(int value)
 * 4. Mengambil elemen array yang bernilai 10 bila ada.
 * a. Melakukan operasi filtering menggunakan method filter() untuk menyaring elemen yang nilainya sama dengan 10.
 * b. Tampilkan pesan bila ditemukan




 * */
