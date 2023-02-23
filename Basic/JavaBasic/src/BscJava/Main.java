package BscJava;

public class Main {
    private static Double panjang, lebar, sisi, luas, volume;

    public static void main(String[] args) {
        // Method. Void Return
        luasPersegiPanjang();
        // Method. Value Return
        System.out.println(volumeKubus());
        // Function (Method has parameter and return)
        System.out.println(VolumeBalok(7.0, 8.0, 9.0));
        // Function (Method has parameter and void return)
        luasPersegi(7.0);
    }

    private static void luasPersegiPanjang() {
        panjang = 9.0;
        lebar = 8.0;
        luas = panjang * lebar;
        System.out.println("\nLuas Persegi Panjang:\nPanjang: " + panjang + " x Lebar: " + lebar + " = " + luas);
    }

    private static String volumeKubus() {
        sisi = 9.0;
        volume = sisi * sisi * sisi;
        return "\nVolume Kubus. " + sisi + "^3 = " + volume;
    }

    private static Double VolumeBalok(Double panjang, Double lebar, Double tinggi) {
        volume = panjang * lebar * tinggi;
        System.out.print("\nVolume Balok. \nPanjang: " + panjang + " x Lebar: " + lebar + " x Tinggi: " + tinggi + " = ");
        return volume;
    }

    private static void luasPersegi(Double sisi) {
        luas = sisi * sisi;
        System.out.println("\nLuas Persegi:\n Sisi: " + sisi + "^2 = " + luas);
    }
}