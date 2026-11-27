public class NonMember extends Pelanggan {

    public NonMember(String nama) {
        super(nama, false);
    }

    @Override
    public int getDiskon() {
        return 0;
    }
}