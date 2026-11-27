public class PengenalanOOP {

    static class UnitPS {
        String nama;
        int tarif;

        void tampilkanInfo() {
            System.out.println("Nama Unit: " + nama);
            System.out.println("Tarif: Rp" + tarif);
        }
    }

    public static void main(String[] args) {
        UnitPS unit = new UnitPS();

        unit.nama = "PlayStation 5";
        unit.tarif = 15000;

        System.out.println("=== PENGENALAN OOP ===");
        unit.tampilkanInfo();
    }
}