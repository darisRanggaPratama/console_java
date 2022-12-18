package stringBuilder;

import StringCls.StringMain;

class BuilderString extends StringMain {
    static BuilderString bs = new BuilderString();

    // Membuat objek StringBuilder dengan kapasitas 16 karakter
    StringBuilder sb = new StringBuilder(16);

    public static void main(String[] args) {
        bs.gabungBiasa();
        bs.gabungBuilder();
        bs.fiturBuilder();

    }

    void gabungBiasa() {
        String name = "raden";
        name = name + " " + "rangga";
        name = name + " " + "pratama";
        show(name);
    }

    void gabungBuilder() {
        StringBuilder sbr = new StringBuilder();
        sbr.append("Rangga");
        sbr.append(" ");
        sbr.append("pratama");
        for (int x = 1; x <= 5; x++) {
            sbr.append(" " + x);
        }

        String kata = sbr.toString();
        show(kata);
    }

    void fiturBuilder() {
        // Menambahkan string ke objek StringBuilder
        sb.append(" rangga");
        sb.append(" ifa");
        showBuilder();

        for(int x = 1; x < 16; x++){
            sb.append(" " + x);
        }
        showBuilder();

        // Menambahkan string di indeks tertentu
        sb.insert(7, " meong");
        showBuilder();

        // Mengganti string di indeks tertentu
        sb.replace(10, 16, " world");
        showBuilder();

        // Menghapus string dari indeks tertentu
        sb.delete(7, 17);
        showBuilder();

        // Membalikkan string dalam objek StringBuilder
        sb.reverse();
        showBuilder();

        // Mengambil panjang string dalam objek StringBuilder
        int length = sb.length();
        show(String.valueOf(length));
    }

    void showBuilder(){
        String kata = sb.toString();
        show(kata);
    }
}
/*
 * StringBuilder adalah kelas di Java yang digunakan untuk membangun string. Ini mirip dengan kelas String, tetapi memiliki beberapa keuntungan dalam kinerja. StringBuilder menyimpan string sebagai array mutable karakter, yang berarti bahwa Anda dapat secara dinamis menambah atau menghapus karakter dari string tanpa harus membuat string baru setiap kali perubahan dilakukan. Ini berguna ketika Anda membutuhkan untuk secara terus-menerus memodifikasi string dalam aplikasi Anda, karena dapat mengurangi jumlah penciptaan objek yang dibutuhkan dan meningkatkan kinerja secara keseluruhan.
 * */

/*
* Kelas StringBuilder memiliki beberapa metode yang berguna untuk mengubah string, seperti append(), insert(), replace(), dan delete(). Selain itu, kelas ini juga memiliki metode reverse() untuk membalikkan urutan karakter dalam string, dan metode length() untuk mengetahui panjang string.

* Untuk mengambil string yang disimpan dalam objek StringBuilder, Anda dapat menggunakan metode toString().
* */
