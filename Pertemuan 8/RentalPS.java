public class RentalPS {
    static class Pelanggan {
        private String nama;

        public Pelanggan(String nama) {
            this.nama = nama;
        }

        public String getNama() {
            return nama;
        }
    }

    static class Member extends Pelanggan {
        public Member(String nama) {
            super(nama);
        }

        public void tampilkanStatus() {
            System.out.println("Status: Member");
        }
    }

    public static void main(String[] args) {
        Member pelanggan = new Member("Andi");

        System.out.println("=== RENTAL PLAYSTATION ===");
        System.out.println("Nama pelanggan: " + pelanggan.getNama());

        pelanggan.tampilkanStatus();
    }
}