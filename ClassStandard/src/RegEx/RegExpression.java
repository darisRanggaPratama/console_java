package RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpression {
    public static void main(String[] args) {
        vocalRegex();

        polaRegex();

        angkaRegex();
    }

    static void angkaRegex(){
        // Kita akan mencoba mencocokkan pola dengan string "123-456-7890"
        String phoneNumber = "123-456-7890";

        // Pola yang kita gunakan adalah "\\d{3}-\\d{3}-\\d{4}"
        // \\d menandakan digit, dan kita menggunakan kurung {} untuk menandakan bahwa kita mengharapkan tiga digit berturut-turut
        String phoneNumberPattern = "\\d{3}-\\d{3}-\\d{4}";

        // Membuat objek Pattern dari pola yang kita definisikan
        Pattern pattern = Pattern.compile(phoneNumberPattern);

        // Membuat objek Matcher yang akan mencocokkan string dengan pola yang telah kita tentukan
        Matcher matcher = pattern.matcher(phoneNumber);

        // Menggunakan metode matches() untuk mengecek apakah string sesuai dengan pola yang kita tentukan
        boolean isMatch = matcher.matches();

        // Menampilkan hasil pencocokan
        if (isMatch) {
            System.out.println("Nomor telepon sesuai dengan pola yang telah ditentukan");
        } else {
            System.out.println("Nomor telepon tidak sesuai dengan pola yang telah ditentukan");
        }
    }
    static void polaRegex(){
        String nama = "Raden Rangga pratama. Belajar java dasar. Test Now.";
        Pattern pat = Pattern.compile("[a-zA-Z]*[a][a-zA-Z]*");
        Matcher mat = pat.matcher(nama);

        while(mat.find()){
            String result = mat.group();
            System.out.println(result);
        }
    }

    static void vocalRegex() {
        // Membuat objek Pattern dengan menggunakan regex untuk mencari huruf vokal
        Pattern pattern = Pattern.compile("[aeiou]");

        // Membuat objek Matcher dengan menggunakan string yang akan dicari pola regex-nya
        Matcher matcher = pattern.matcher("abcdefghijklmnopqrstuvwxyz");

        // Mencetak hasil pencarian pola regex dalam string
        while (matcher.find()) {
            System.out.println("Huruf vokal ditemukan pada indeks: " + matcher.start());
        }
    }
}
// Penjelasan vocalRegex()
/*
* Dalam kode di atas, pertama-tama kita mengimport kelas Pattern dan Matcher dari paket java.util.regex. Kemudian, kita membuat objek pattern dengan menggunakan regex yang akan digunakan untuk mencari pola dalam string. Regex yang digunakan dalam contoh ini adalah "[aeiou]", yang berarti kita akan mencari huruf vokal dalam string.

* Setelah itu, kita membuat objek matcher dengan menggunakan string yang akan dicari pola regex-nya. Dalam contoh ini, string yang digunakan adalah "abcdefghijklmnopqrstuvwxyz", yaitu semua huruf dari a sampai z.

* Terakhir, kita menggunakan perulangan while untuk mencetak hasil pencarian pola regex dalam string. Menggunakan metode find() pada objek matcher, kita bisa mengetahui apakah pola regex ditemukan dalam string atau tidak. Jika ditemukan, kita bisa mencetak indeks dimana pola regex ditemukan dengan menggunakan metode start() pada objek matcher.
* */

// Penjelasan angkaRegex()
/*
* Pada contoh di atas, kita telah menggunakan pola reguler (Regex) untuk mengecek apakah string "123-456-7890" sesuai dengan pola yang kita tentukan, yaitu "\d{3}-\d{3}-\d{4}". Pola ini menandakan bahwa kita mengharapkan tiga digit berturut-turut, dipisahkan oleh tanda strip (-).

* Kita menggunakan kelas Pattern dan Matcher dari package java.util.regex untuk mengelola pola dan mencocokkannya dengan string yang kita masukkan. Kemudian, kita menggunakan metode matches() dari kelas Matcher untuk mengecek apakah string sesuai dengan pola yang telah kita tentukan.
*
* */
