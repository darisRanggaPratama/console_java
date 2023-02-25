package MVC2;

public class MainMVC {
    public static void main(String[] args) {
        Models models = new Models();
        Views views = new Views();
        Controllers controller = new Controllers(models, views);

        controller.updateData();
        controller.displayData();
    }
}

/*
* Penjelasan:

* MVC adalah pola desain perangkat lunak yang membagi aplikasi menjadi tiga bagian utama: Model, View, dan Controller.

* Model: merupakan representasi dari data dan aturan bisnis dalam aplikasi. Pada contoh kode di atas, Model memiliki satu atribut yaitu data yang berisi array dari String yang berisi informasi yang akan ditampilkan.
* View: merupakan tampilan yang menampilkan data dari Model kepada pengguna. Pada contoh kode di atas, View memiliki dua metode yaitu displayData() untuk menampilkan data ke konsol dan getUserInput() untuk meminta input dari pengguna.
* Controller: bertanggung jawab untuk menghubungkan Model dan View. Pada contoh kode di atas, Controller memiliki dua metode yaitu updateData() untuk mengambil input pengguna dan memperbarui Model dan displayData() untuk mengambil data dari Model dan menampilkan ke View.

* Dalam Main, kita membuat objek Model, View, dan Controller. Kemudian, kita memanggil updateData() untuk memperbarui Model dengan data dari pengguna dan displayData() untuk menampilkan data ke View. Dengan menggunakan pola desain MVC, aplikasi menjadi lebih mudah untuk dipelihara, dikembangkan, dan diuji karena setiap bagian berfungsi secara terpisah.
* */

