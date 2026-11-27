public class Member extends Pelanggan {

    public Member(String nama) {
        super(nama, true);
    }

    @Override
    public int getDiskon() {
        return 5;
    }
}