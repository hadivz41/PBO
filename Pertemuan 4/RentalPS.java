import java.util.Scanner;

public class RentalPS {
    static int hitungBiaya(int durasi, int tarif) {
        int total = durasi * tarif;

        if (durasi > 5) {
            total -= total * 10 / 100;
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== RENTAL PLAYSTATION ===");
        System.out.print("Nama pelanggan: ");
        String nama = input.nextLine();

        System.out.print("Tarif per jam: ");
        int tarif = input.nextInt();

        System.out.print("Durasi sewa: ");
        int durasi = input.nextInt();

        int total = hitungBiaya(durasi, tarif);

        System.out.println("\n=== STRUK RENTAL ===");
        System.out.println("Nama pelanggan: " + nama);
        System.out.println("Durasi: " + durasi + " jam");
        System.out.println("Total biaya: Rp" + total);

        input.close();
    }
}