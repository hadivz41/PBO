public class MemoryManagement {
    static class Pelanggan {
        private String nama;

        public Pelanggan(String nama) {
            this.nama = nama;
        }

        public String getNama() {
            return nama;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MEMORY MANAGEMENT ===");

        Pelanggan pelanggan1 = new Pelanggan("Andi");

        System.out.println("Pelanggan 1: " + pelanggan1.getNama());

        Pelanggan pelanggan2 = pelanggan1;

        System.out.println("Pelanggan 2: " + pelanggan2.getNama());

        pelanggan1 = null;

        System.out.println("Setelah reference pertama dihapus:");
        System.out.println("Pelanggan 2: " + pelanggan2.getNama());

        pelanggan2 = null;

        System.out.println("Kedua reference sudah null.");
    }
}