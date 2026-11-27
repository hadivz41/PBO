public class DataVariableMethod {

    static int hitungBiaya(int tarif, int durasi) {
        return tarif * durasi;
    }

    public static void main(String[] args) {
        String namaPelanggan = "Andi";
        String namaUnit = "PS5";

        int tarif = 15000;
        int durasi = 3;

        int total = hitungBiaya(tarif, durasi);

        System.out.println("=== DATA RENTAL ===");
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        System.out.println("Unit: " + namaUnit);
        System.out.println("Tarif: Rp" + tarif);
        System.out.println("Durasi: " + durasi + " jam");
        System.out.println("Total Biaya: Rp" + total);
    }
}