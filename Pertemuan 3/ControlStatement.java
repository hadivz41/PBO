import java.util.Scanner;

public class ControlStatement {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] unitPS = {"PS3", "PS4", "PS5"};

        System.out.println("=== PILIH UNIT PLAYSTATION ===");

        for (int i = 0; i < unitPS.length; i++) {
            System.out.println((i + 1) + ". " + unitPS[i]);
        }

        System.out.print("Pilih unit (1-3): ");
        int pilihan = input.nextInt();

        if (pilihan >= 1 && pilihan <= 3) {
            System.out.println("Unit dipilih: " + unitPS[pilihan - 1]);

            switch (pilihan) {
                case 1:
                    System.out.println("Tarif: Rp10000/jam");
                    break;

                case 2:
                    System.out.println("Tarif: Rp12000/jam");
                    break;

                case 3:
                    System.out.println("Tarif: Rp15000/jam");
                    break;
            }
        } else {
            System.out.println("Pilihan unit tidak tersedia.");
        }

        input.close();
    }
}