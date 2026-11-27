public class Pelanggan {
    private String nama;
    private boolean member;

    public Pelanggan(String nama, boolean member) {
        this.nama = nama;
        this.member = member;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public int getDiskon() {
        return 0;
    }
}
