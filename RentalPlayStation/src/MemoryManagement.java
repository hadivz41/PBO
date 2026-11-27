public class MemoryManagement {

public static void main(String[] args) {
        System.out.println("\n===== MEMORY MANAGEMENT =====");

        System.out.println("\nA. Typical Calling Sequence");
        System.out.println("main() memanggil tampilkanSimpleVariables()");
        tampilkanSimpleVariables(2, 10000);

        System.out.println("\nB. Call Frames - Simple Variables");
        System.out.println("Data durasi dan tarif digunakan dalam method.");

        System.out.println("\nC. Call Frames - Reference Variables");

        Pelanggan pelanggan = new Pelanggan("Andi", true);
        UnitPS unit = new UnitPS("PS4", "Reguler", 10000);

        Transaksi transaksi = new Transaksi(
                0,
                pelanggan,
                unit,
                2,
                "14:00",
                "16:00",
                19000,
                19000,
                "Cash"
        );

        tampilkanReferenceVariables(transaksi);
}

static void tampilkanSimpleVariables(int durasi, int tarif) {
        int total = durasi * tarif;

        System.out.println("Durasi sewa : " + durasi + " jam");
        System.out.println("Tarif       : Rp" + tarif);
        System.out.println("Total harga : Rp" + total);
}

static void tampilkanReferenceVariables(Transaksi transaksi) {
        System.out.println("Nama pelanggan : "
                + transaksi.getPelanggan().getNama());

        System.out.println("Unit PS        : "
                + transaksi.getUnit().getNama());

        System.out.println("Ruangan        : "
                + transaksi.getUnit().getRuangan());

        System.out.println("Total transaksi: Rp"
                + transaksi.getTotal());

        System.out.println("Status         : "
                + transaksi.getStatus());
}
}
