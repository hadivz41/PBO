import java.util.Scanner;

public class RentalPS {
    static class UnitPS {
        private String nama;
        private int tarif;
        private String status;

        public UnitPS(String nama, int tarif) {
            this.nama = nama;
            this.tarif = tarif;
            this.status = "Ready";
        }

        public String getNama() {
            return nama;
        }

        public int getTarif() {
            return tarif;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    static class Pelanggan {
        private String nama;

        public Pelanggan(String nama) {
            this.nama = nama;
        }

        public String getNama() {
            return nama;
        }
    }

    static int hitungBiaya(int durasi, int tarif) {
        int total = durasi * tarif;

        if (durasi > 5) {
            total -= total * 10 / 100;
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        UnitPS unit = new UnitPS("PS5", 15000);

        System.out.print("Nama pelanggan: ");
        Pelanggan pelanggan = new Pelanggan(input.nextLine());

        System.out.print("Durasi sewa: ");
        int durasi = input.nextInt();

        if (!unit.getStatus().equals("Ready")) {
            System.out.println("Unit sedang digunakan.");
        } else {
            int total = hitungBiaya(durasi, unit.getTarif());

            unit.setStatus("Berlangsung");

            System.out.println("\n=== STRUK RENTAL ===");
            System.out.println("Pelanggan: " + pelanggan.getNama());
            System.out.println("Unit: " + unit.getNama());
            System.out.println("Durasi: " + durasi + " jam");
            System.out.println("Total: Rp" + total);
            System.out.println("Status: " + unit.getStatus());
        }

        input.close();
    }
}