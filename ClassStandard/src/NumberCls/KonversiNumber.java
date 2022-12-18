package NumberCls;

import static StringCls.StringMain.show;

public class KonversiNumber {
    public static void main(String[] args) {
        ubahTipeNumber();
        ubahStringNumber();

    }

    static void ubahTipeNumber(){
        // Harus tipe data objek
        Integer intValue = 10;
        show(String.valueOf(intValue));
        Long longValue = intValue.longValue();
        show(String.valueOf(longValue));
        Double dblValue = longValue.doubleValue();
        show(String.valueOf(dblValue));
        Short shortValue = dblValue.shortValue();
        show(String.valueOf(shortValue));
    }

    static void ubahStringNumber(){
        String txtAngka = "12345";
        show(txtAngka);
        Integer txtInt = Integer.valueOf(txtAngka);
        show(String.valueOf(txtInt));
        int intAngka = Integer.parseInt(txtAngka);
        show(String.valueOf(intAngka));

        Double txtDbl = Double.valueOf(txtAngka);
        show(String.valueOf(txtDbl));
        double dblAngka = Double.parseDouble(txtAngka);
        show(String.valueOf(dblAngka));
    }
}
