public class UnitPS {
    private String nama;
    private String ruangan;
    private int tarif;
    private String status;

    public UnitPS(String nama, String ruangan, int tarif) {
        this.nama = nama;
        this.ruangan = ruangan;
        this.tarif = tarif;
        this.status = "Ready";
    }

    public String getNama() {
        return nama;
    }

    public String getRuangan() {
        return ruangan;
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
