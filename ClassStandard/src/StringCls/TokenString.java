package StringCls;

import java.util.StringTokenizer;

public class TokenString extends StringMain {
    String strToke = "ifa;izam;0;1;2;3;4;5;6;7;8;9";
    StringTokenizer sitoke = new StringTokenizer(strToke, ";");
    static TokenString toke = new TokenString();
    public static void main(String[] args) {
        toke.strToken();

    }

    void strToken(){
        while (sitoke.hasMoreTokens()){
            String value = sitoke.nextToken();
            show(value);
        }
    }
}

// Terakhir menit 22

/*
* StringTokenizer adalah kelas yang terdapat dalam paket java.util yang digunakan untuk memecah string menjadi sekumpulan token (potongan-potongan string) berdasarkan pemisah yang ditentukan. StringTokenizer menyediakan metode yang mudah digunakan untuk mengambil token satu per satu atau sebagai sekumpulan token dalam bentuk array atau sebagai sebuah string. StringTokenizer juga dapat digunakan untuk menghitung jumlah token yang dihasilkan dari string yang dipisahkan.
* */

/*
* Pada kode di atas, kita membuat string yang akan dibagi menjadi token dan membuat objek StringTokenizer dengan menggunakan string tersebut dan menentukan karakter koma (;) sebagai pemisah token. Kemudian, kita menampilkan jumlah token yang dihasilkan dan menampilkan setiap token satu per satu menggunakan loop: while.
* */



// Bila load text dengan kapasitas besar
// StringTokenizer: Hemat konsumsi RAM
// Split: Boros/ membutuhkan RAM besar