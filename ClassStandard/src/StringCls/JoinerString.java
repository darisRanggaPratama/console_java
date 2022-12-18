package StringCls;

import java.util.StringJoiner;

public class JoinerString extends StringMain {
    StringJoiner sijon = new StringJoiner(";", "{", "}");
    static JoinerString jons = new JoinerString();
    public static void main(String[] args) {
        jons.showStrJoin();


    }
    void showStrJoin(){
        sijon.add("ifa");
        sijon.add("izam");
        for (int x = 0; x < 10; x++){
            sijon.add(String.valueOf(x));
        }
        showJoin();
    }

    void showJoin(){
        String value = sijon.toString();
        show(value);
    }
}

/*
* StringJoiner adalah kelas yang terdapat dalam paket java.util yang digunakan untuk menggabungkan beberapa string menjadi satu string dengan pemisah yang ditentukan. StringJoiner menyediakan metode yang mudah digunakan untuk menggabungkan string dengan pemisah tertentu dan menambahkan prefix dan suffix ke string yang digabungkan. StringJoiner juga dapat digunakan untuk menghitung jumlah string yang digabungkan dan mengambil string yang digabungkan dalam bentuk array atau sebagai sebuah string.
* */

/*
* Pada contoh di atas, kita membuat instance dari kelas StringJoiner dengan pemisah ";". Kemudian, kita menambahkan beberapa string ke dalam instance StringJoiner tersebut menggunakan metode add(). Terakhir, kita menggabungkan semua string menjadi satu string dengan menggunakan metode toString(). Setelah dijalankan, program akan mencetak string "{ifa;izam;0;1;2;3;4;5;6;7;8;9}" ke layar.
* */
