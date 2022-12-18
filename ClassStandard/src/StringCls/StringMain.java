package StringCls;

public class StringMain implements Texts {
    // Method Overriding
    @Override
    public String text(String value) {
        return value;
    }
    static public void show(String value) {
        System.err.println(value);
    }

    public static void main(String[] args) {
        String teks = "Raden Rangga Pratama";

        // Lambda Expression
        // LowerCase
        Texts txt = String::toLowerCase;
        show(txt.text(teks));

        // UpperCase
        txt = String::toUpperCase;
        show(txt.text(teks));

        // Length
        show(String.valueOf(teks.length()));

        // StartWith
        show(String.valueOf(teks.startsWith("Ra")));

        // EndWith
        show(String.valueOf(teks.startsWith("mi")));

        // Split words
       String[] txts = teks.split(" ");
       for(String word: txts) {
           show(word);
       }

       // isBlank. Spasi kosong tidak dihitung
       show(String.valueOf(" ".isBlank()));

       // isEmpty. Spasi kosong dihitung
       show(String.valueOf(" ".isEmpty()));
        show(String.valueOf("".isEmpty()));

        // CharAt. Ambil 1 karakter tergantung posisi
        show(String.valueOf(teks.charAt(1)));
        // Contoh lain
        char[] chars = teks.toCharArray();
        for(char value : chars){
            show(String.valueOf(value));
        }





    }
}

// String itu immutable: variabel tidak bisa diubah



