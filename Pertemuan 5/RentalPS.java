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

    public static void main(String[] args) {
        UnitPS unit = new UnitPS("PS5", 15000);

        System.out.println("Nama unit: " + unit.getNama());
        System.out.println("Tarif: Rp" + unit.getTarif());
        System.out.println("Status: " + unit.getStatus());
    }
}