package BscJava;

public class Main {

    public static void main(String[] args) {
        // Method. Void Return
        luasPersegiPanjang();

        // Method. Value Return
        System.out.println(volumeKubus());

        // Function (Method has parameter and return)
        System.out.println(VolumeBalok(7.0, 8.0, 9.0));

        // Function (Method has parameter and void return)
        luasPersegi(7.0);

        // Overloading
        System.out.println(persegiPanjang(2, 5));
        System.out.println(persegiPanjang(2, 5, 10));

    }

    private static void luasPersegiPanjang() {
        double panjang = 9.0;
        double lebar = 8.0;
        double luas = panjang * lebar;
        System.out.println("\nLuas Persegi Panjang:\nPanjang: " + panjang + " x Lebar: " + lebar + " = " + luas);
    }

    private static String volumeKubus() {
        double sisi = 9.0;
        double volume = sisi * sisi * sisi;
        return "\nVolume Kubus. " + sisi + "^3 = " + volume;
    }

    private static Double VolumeBalok(Double panjang, Double lebar, Double tinggi) {
        double volume = panjang * lebar * tinggi;
        System.out.print("\nVolume Balok. \nPanjang: " + panjang + " x Lebar: " + lebar + " x Tinggi: " + tinggi + " = ");
        return volume;
    }

    private static void luasPersegi(Double sisi) {
        double luas = sisi * sisi;
        System.out.println("\nLuas Persegi:\n Sisi: " + sisi + "^2 = " + luas);
    }

    private static String persegiPanjang(int panjang, int lebar)
    {
        int persegiPanjang = panjang * lebar;

        return  "\nLuas Persegi Panjang.\nPanjang: " + panjang + " Lebar: " + lebar + " itu " + persegiPanjang;
    }

    private static String persegiPanjang(double panjang, double lebar, double tinggi)
    {
        double balok = panjang * lebar * tinggi;

        return"\nVolume Balok.\nPanjang: " + panjang + " Lebar: " + lebar + " Tinggi: " + tinggi + " itu " + balok;
    }
}