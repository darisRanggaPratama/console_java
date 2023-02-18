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
        setIntegerArrayValue(0, 15);
        setStringArrayValue();

        // Get data kurang dari nilai tertentu
        getLessThan(500);
        // Get nilai tertentu dan ganjil
        getNumberOverAndOdd(500);
        // Get nilai tertentu
        getNumberValue(10);

        // Get value dengan jumlah kata tertentu
        getMoreWords(2);
        // Get huruf pertama
        getFirstLetter("e");
        // Get Kata
        getWord("al");
        // Get Kata terakhir
        getLastWord("ackerman");
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

    private static void getNumberOverAndOdd(int value) {
        showFullIntegerArray();

        System.out.println("\n\nElemen array bernilai ganjil & lebih dari: " + value);
        int[] overAndOdd = Arrays.stream(numbers)
                .filter(n -> n > value && n % 2 != 0)
                .toArray();

        for (int number : overAndOdd) {
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

    private static void getFirstLetter(String value) {
        String[] firstLetter = Arrays.stream(words)
                .filter(x -> x.startsWith("e"))
                .toArray(String[]::new);

        System.out.println("\nKata diawali huruf: " + value);
        for (String word : firstLetter) {
            System.out.println(word);
        }
    }

    private static void getWord(String value) {
        Stream<String> getWordStream = Arrays.stream(words)
                .filter(str -> str.contains(value));

        System.out.println("\nKata: " + value + " ditemukan: ");

        getWordStream.forEach(System.out::println);
    }

    private static void getLastWord(String value) {
        Stream<String> lastWordStream = Arrays.stream(words)
                .filter(str -> str.endsWith("ackerman"));
        System.out.println("\nSuku kata terakhir ditemukan: " + value);
        lastWordStream.forEach(System.out::println);
    }
}
/*
1. setIntegerArrayValue(int first, int last)
a. Memasukkan elemen-elemen array yang berasal dari nilai random yang ditentukan dari variable first dan last

2. showFullIntegerArray()
a. Mengurutkan semua elemen array secara ascending
b. Membalikkan pengurutan menjadi descending
c. Menampilkan hasilnya dengan perulangan (loop: for)

3. getLessThan(int value)
a. Memanggil method showFullIntegerArray()
b. Mengambil elemen array yang di bawah nilai tertentu menggunakan stream
c. Menampilkan hasilnya dengan perulangan (loop: for)

4. getNumberOverAndOdd(int value)
a. Memanggil method showFullIntegerArray()
b. Mengambil elemen array yang di atas nilai tertentu dan bernilai ganjil (nilai yang bila habis dibagi 2 sisanya tidak 0)
c. Menampilkan hasilnya dengan perulangan (loop: for)

5. getNumberValue(int value)
a. Mengambil elemen array dengan kriteria value tertentu
b. Jika ditemukan tampilkan pesan. Dan jika tidak ada menampilkan pesan tidak ditemukan

6. setStringArrayValue()
a. Mengisi array dengan bebera elemen string dimana kapasitas array 11 elemen.

7. getMoreWords(int value)
a. Mengurutkan semua elemen array secara menanjak (ascending) menggunakan stream
b. Menampilkan hasilnya dengan perulangan (loop: for)
c. Mengurutkan semua elemen array secara terbalik (descending)
d. Menampilkan hasilnya dengan perulangan (loop: for)

8. getMoreWords(int value)
a. Memanggil method getMoreWords(int value)
b. Memecah 1 kata menjadi beberapa suku kata menggunakan stream
c. Menampung hasilnya dalam variable getMoreWords
d. Menampilkan hasilnya melalui perulangan (loop: for)

9. getFirstLetter(String value)
a. Mengambil kata yang memiliki awalan huruf tertentu
b. Menampung hasilnya di variable firstLetter
c. Menampilkan hasilnya menggunakan perulangan (loop: for)

10. getWord(String value)
a. Mengambil elemen array yang memiliki nilai tertentu
b. Menampilkan hasilnya

11. getLastWord(String value)
a. Mengambil elemen array yang memili suku kata terakhir tertentu
c. Menampilkan hasilnya bila ditemukan
*/
