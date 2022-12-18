package property;

import java.io.*;
import java.util.Properties;

public class AppProperties  {
    public static void main(String[] args) {
        loadProperties();

        writeProperties();

        createReadProperties();

        createReadDeleteProperties();

        crudProperties();
    }
    static void crudProperties(){
        // Membuat objek Properties
        Properties properties = new Properties();

        // Menyimpan objek Properties ke file
        try (FileOutputStream output = new FileOutputStream("src/crud.properties.txt")) {
            // Menambahkan beberapa properti ke objek Properties
            properties.setProperty("nama", "Tika");
            properties.setProperty("umur", "40");
            properties.setProperty("pekerjaan", "UI/UX");
            properties.store(output, "Informasi Pribadi");
        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal simpan ke file: " + exception);
        }

        // Membaca objek Properties dari file
        try (FileInputStream input = new FileInputStream("src/crud.properties.txt")) {
            properties.load(input);
            // Menampilkan semua properti yang ada di objek Properties
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                System.out.println(key + ": " + value);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal load file: " + exception);
        }

        try (FileInputStream input = new FileInputStream("src/crud.properties.txt"))  {
            properties.load(input);
            // Menghapus properti dari objek Properties
            properties.remove("umur");

            // Menampilkan kembali semua properti yang ada di objek Properties setelah properti "umur" dihapus
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                System.out.println(key + ": " + value);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal load file: " + exception);
        }

        try (FileInputStream input = new FileInputStream("src/crud.properties.txt"))  {
            properties.load(input);
            // Meng-update properti dari objek Properties
            properties.setProperty("pekerjaan", "UI/UX Designer");

            // Menampilkan kembali semua properti yang ada di objek Properties setelah properti "umur" dihapus
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                System.out.println(key + ": " + value);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal load file: " + exception);
        }
    }

    static void createReadDeleteProperties(){
        // Membuat objek Properties
        Properties properties = new Properties();

        // Menyimpan objek Properties ke file
        try (FileOutputStream output = new FileOutputStream("src/properties.txt")) {
            // Menambahkan beberapa properti ke objek Properties
            properties.setProperty("nama", "Ifa");
            properties.setProperty("umur", "28");
            properties.setProperty("pekerjaan", "UI/UX");
            properties.store(output, "Informasi Pribadi");
        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal simpan ke file: " + exception);
        }

        // Membaca objek Properties dari file
        try (FileInputStream input = new FileInputStream("src/properties.txt")) {
            properties.load(input);
            // Menampilkan semua properti yang ada di objek Properties
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                System.out.println(key + ": " + value);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal load file: " + exception);
        }

        try (FileInputStream input = new FileInputStream("src/properties.txt"))  {
            properties.load(input);
            // Menghapus properti dari objek Properties
            properties.remove("umur");

            // Menampilkan kembali semua properti yang ada di objek Properties setelah properti "umur" dihapus
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                System.out.println(key + ": " + value);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal load file: " + exception);
        }
    }
    static void createReadProperties(){
        // Membuat objek Properties baru
        Properties prop = new Properties();

        try {
            // Menambahkan beberapa properti ke objek Properties
            prop.setProperty("nama", "Izam");
            prop.setProperty("umur", "30");
            prop.setProperty("pekerjaan", "senior software engineer");

            prop.store(new FileOutputStream("src/data.properties"), "Contoh data");
        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal simpan ke file: " + exception);
        }

        try{
            prop.load(new FileInputStream("src/data.properties"));

            // Menampilkan nilai dari properti "nama"
            System.out.println("Nama: " + prop.getProperty("nama"));

            // Menampilkan nilai dari properti "umur"
            System.out.println("Umur: " + prop.getProperty("umur"));

            // Menampilkan nilai dari properti "pekerjaan"
            System.out.println("Pekerjaan: " + prop.getProperty("pekerjaan"));

            // Menampilkan semua properti yang ada dalam objek Properties
            for (String key : prop.stringPropertyNames()) {
                System.out.println(key + ": " + prop.getProperty(key));
            }

        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal load file: " + exception);
        }
    }
    static void writeProperties(){
        try {
            Properties properties = new Properties();
            properties.put("name.first", "raden");
            properties.put("name.middle", "rangga");
            properties.put("name.last", "pratama");

            properties.store(new FileOutputStream("src/name.properties"), "Contoh nama");
        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal simpan ke file: " + exception);
        }
    }

    static void loadProperties() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/application.properties"));

            String host = properties.getProperty("database.host");
            String port = properties.getProperty("database.port");
            String username = properties.getProperty("database.username");
            String password = properties.getProperty("database.password");

            System.out.println(host + " " + port + " " + username + " " + password);
        } catch (FileNotFoundException exception) {
            System.out.println("File tidak ketemu: " + exception);
        } catch (IOException exception){
            System.out.println("Gagal load file: " + exception);
        }
    }
}

// Penjelasan createReadProperties()
/*
* Dalam contoh di atas, kita membuat sebuah objek Properties baru dengan menggunakan konstruktor Properties(). Kemudian, kita menambahkan beberapa properti ke objek Properties dengan menggunakan metode setProperty(). Metode ini menerima dua parameter, yaitu nama properti dan nilai properti.

* Setelah itu, kita dapat menampilkan nilai dari properti yang telah ditambahkan dengan menggunakan metode getProperty(). Metode ini juga menerima satu parameter, yaitu nama properti yang ingin ditampilkan.

* Terakhir, kita dapat menampilkan semua properti yang ada dalam objek Properties dengan menggunakan perulangan for-each dan metode stringPropertyNames(). Metode ini mengembalikan sebuah Set yang berisi nama-nama dari semua properti yang ada dalam objek Properties.
* */

// Penjelasan createReadDeleteProperties()
/*
* Pada kode di atas, pertama-tama kita membuat sebuah objek Properties dengan menggunakan konstruktor kosong. Kemudian kita menambahkan beberapa properti ke objek Properties dengan menggunakan metode setProperty(). Setelah itu, kita menyimpan objek Properties ke dalam sebuah file dengan menggunakan FileOutputStream dan metode store().

* Selanjutnya, kita membaca objek Properties dari file dengan menggunakan FileInputStream dan metode load(). Kemudian kita menampilkan semua properti yang ada di objek Properties dengan menggunakan metode stringPropertyNames() dan getProperty().

* Setelah itu, kita menghapus properti dari objek Properties dengan menggunakan metode remove(). Kemudian kita menampilkan kembali semua properti yang ada di objek Properties setelah properti "umur" dihapus.
* */

// Penjelasan Update
//  properties.setProperty("pekerjaan", "UI/UX Designer");