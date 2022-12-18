package ArraysCls;

import java.util.Arrays;

public class ClsArrays {
    // Create Array
   static int[] angka = {1, 2, 466, 86, 324, 23, 1000, 8678, 24, 214, 213, 0, -1};
    public static void main(String[] args) {
    // Read and sort item
        readArray();
        // Find item in array
        findItemArray();
        // Copy part of array
       copyPartArray();
        // copy item with range
     copyItemRange();
// Crud Array
      //  crudArray();

    }
    static void crudArray(){
       // Create (Menambahkan elemen baru ke dalam array)
        int[] array = new int[5]; // membuat array integer dengan 5 elemen
        array[0] = 100; // menambahkan elemen pertama dengan nilai 10
        array[1] = 20; // menambahkan elemen kedua dengan nilai 20
        array[2] = 330; // menambahkan elemen ketiga dengan nilai 30
        array[3] = 40; // menambahkan elemen keempat dengan nilai 40
        array[4] = 50; // menambahkan elemen kelima dengan nilai 50
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        // Read (Membaca elemen dari array)
        int elemenKeEmpat = array[3]; // mengambil elemen keempat dari array
        System.out.println(elemenKeEmpat);

        // Update (Mengubah nilai elemen dari array)
        array[3] = 45; // mengubah nilai elemen keempat menjadi 45
        System.out.println(Arrays.toString(array));

        // Delete (Menghapus elemen dari array)
        int[] arrayBaru = new int[4]; // membuat array baru dengan 4 elemen
// mengisi array baru dengan elemen dari array kecuali elemen keempat
        arrayBaru[0] = array[0];
        arrayBaru[1] = array[1];
        arrayBaru[2] = array[2];
        arrayBaru[3] = array[4];
        array = arrayBaru; // mengganti referensi array dengan array baru
        System.out.println(Arrays.toString(array));
    }
    static void copyItemRange(){
        int[] result = Arrays.copyOfRange(angka, 6, 11);
        System.out.println(Arrays.toString(result));
    }

    static void copyPartArray(){
        int[] result = Arrays.copyOf(angka, 5);
        System.out.println(Arrays.toString(result));
    }

    static void readArray(){
        Arrays.sort(angka);
        System.out.println(Arrays.toString(angka));
    }

    static void findItemArray(){
        System.out.println(Arrays.binarySearch(angka, 9));
        System.out.println(Arrays.binarySearch(angka, 0));
    }


}
