public class RentalPS {
    static class Pelanggan {
        private String nama;
        private boolean member;

        public Pelanggan(String nama, boolean member) {
            this.nama = nama;
            this.member = member;
        }

        public String getNama() {
            return nama;
        }

        public boolean isMember() {
            return member;
        }

        public int getDiskon() {
            return 0;
        }
    }

    static class Member extends Pelanggan {
        public Member(String nama) {
            super(nama, true);
        }

        @Override
        public int getDiskon() {
            return 5;
        }
    }

    static class NonMember extends Pelanggan {
        public NonMember(String nama) {
            super(nama, false);
        }

        @Override
        public int getDiskon() {
            return 0;
        }
    }

    static int hitungBiaya(int durasi, int tarif, Pelanggan pelanggan) {
        int total = durasi * tarif;

        if (durasi > 5) {
            total -= total * 10 / 100;
        }

        total -= total * pelanggan.getDiskon() / 100;

        return total;
    }

    public static void main(String[] args) {
        Pelanggan pelanggan1 = new Member("Andi");
        Pelanggan pelanggan2 = new NonMember("Budi");

        int tarif = 15000;
        int durasi = 6;

        System.out.println("=== RENTAL PLAYSTATION ===");

        tampilkanStruk(pelanggan1, durasi, tarif);
        tampilkanStruk(pelanggan2, durasi, tarif);
    }

    static void tampilkanStruk(Pelanggan pelanggan, int durasi, int tarif) {
        int total = hitungBiaya(durasi, tarif, pelanggan);

        System.out.println("\n=== STRUK RENTAL ===");
        System.out.println("Nama: " + pelanggan.getNama());
        System.out.println("Member: " + (pelanggan.isMember() ? "Ya" : "Tidak"));
        System.out.println("Durasi: " + durasi + " jam");
        System.out.println("Diskon member: " + pelanggan.getDiskon() + "%");
        System.out.println("Total biaya: Rp" + total);
    }
}