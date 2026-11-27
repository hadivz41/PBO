
import java.util.ArrayList;
import java.util.Scanner;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RentalPS {

    static Scanner input = new Scanner(System.in);

    static ArrayList<UnitPS> daftarUnit = new ArrayList<>();
    static ArrayList<Transaksi> riwayat = new ArrayList<>();
    static ArrayList<String> daftarMember = new ArrayList<>();

    static int nomorTransaksi = 1;

    static DateTimeFormatter formatJam =
            DateTimeFormatter.ofPattern("HH:mm");

    // Mengisi data unit PlayStation
    static void isiUnit() {
        daftarUnit.add(new UnitPS("PS3", "Reguler", 5000));
        daftarUnit.add(new UnitPS("PS3", "VIP", 7000));

        daftarUnit.add(new UnitPS("PS4", "Reguler", 8000));
        daftarUnit.add(new UnitPS("PS4", "VIP", 10000));

        daftarUnit.add(new UnitPS("PS4 Pro", "Reguler", 10000));
        daftarUnit.add(new UnitPS("PS4 Pro", "VIP", 12000));

        daftarUnit.add(new UnitPS("PS5", "Reguler", 12000));
        daftarUnit.add(new UnitPS("PS5", "VIP", 15000));

        daftarUnit.add(new UnitPS("PS5 Slim", "Reguler", 15000));
        daftarUnit.add(new UnitPS("PS5 Slim", "VIP", 18000));
    }

    // Mencari transaksi aktif berdasarkan unit
    static Transaksi cariTransaksiAktif(UnitPS unit) {
        for (Transaksi transaksi : riwayat) {
            if (transaksi.getUnit() == unit
                    && transaksi.getStatus().equals("Berlangsung")) {
                return transaksi;
            }
        }

        return null;
    }

    // Mengubah sisa waktu menjadi format jam:menit:detik
    static String formatSisaWaktu(Duration sisa) {
        long detik = sisa.getSeconds();

        long jam = detik / 3600;
        long menit = (detik % 3600) / 60;
        long sisaDetik = detik % 60;

        return String.format("%02d:%02d:%02d",
                jam, menit, sisaDetik);
    }

    // Memperbarui status transaksi dan unit
    static void perbaruiStatusSewa() {
        for (Transaksi transaksi : riwayat) {
            if (transaksi.getStatus().equals("Berlangsung")
                    && transaksi.getSisaWaktu().isZero()) {

                transaksi.setStatus("Selesai");
                transaksi.getUnit().setStatus("Ready");

                System.out.println("\nWaktu sewa transaksi "
                        + transaksi.getId() + " telah habis.");

                System.out.println("Unit "
                        + transaksi.getUnit().getNama()
                        + " sekarang tersedia kembali.");
            }
        }
    }

    // Menampilkan status seluruh unit
    static void tampilkanUnit() {
        perbaruiStatusSewa();

        System.out.println("\n===== STATUS UNIT PLAYSTATION =====");

        for (int i = 0; i < daftarUnit.size(); i++) {
            UnitPS unit = daftarUnit.get(i);

            System.out.println("\nNomor   : " + (i + 1));
            System.out.println("Unit    : " + unit.getNama());
            System.out.println("Ruangan : " + unit.getRuangan());
            System.out.println("Tarif   : Rp" + unit.getTarif());
            System.out.println("Status  : " + unit.getStatus());

            if (unit.getStatus().equals("Lagi dipake")) {
                Transaksi transaksi = cariTransaksiAktif(unit);

                if (transaksi != null) {
                    System.out.println("Pelanggan : "
                            + transaksi.getPelanggan().getNama());

                    System.out.println("Jam Mulai : "
                            + transaksi.getJamMulai());

                    System.out.println("Jam Selesai : "
                            + transaksi.getJamSelesai());

                    System.out.println("Sisa Waktu : "
                            + formatSisaWaktu(transaksi.getSisaWaktu()));
                }
            }
        }
    }

    // Menambahkan member baru
    static void tambahMember() {
        input.nextLine();

        System.out.println("\n===== TAMBAH MEMBER =====");
        System.out.print("Nama member: ");

        String nama = input.nextLine().trim();

        if (nama.isEmpty()) {
            System.out.println("Nama tidak boleh kosong.");
            return;
        }

        for (String member : daftarMember) {
            if (member.equalsIgnoreCase(nama)) {
                System.out.println("Nama sudah terdaftar sebagai member.");
                return;
            }
        }

        daftarMember.add(nama);

        System.out.println("Member berhasil didaftarkan!");
    }

    // Menampilkan daftar member
    static void tampilkanMember() {
        System.out.println("\n===== DAFTAR MEMBER =====");

        if (daftarMember.isEmpty()) {
            System.out.println("Belum ada member terdaftar.");
            return;
        }

        for (int i = 0; i < daftarMember.size(); i++) {
            System.out.println((i + 1) + ". " + daftarMember.get(i));
        }
    }

    // Menghitung biaya sewa
    static int hitungBiaya(int durasi, int tarif, Pelanggan pelanggan) {
        int biaya = durasi * tarif;

        if (durasi > 5) {
            biaya -= biaya * 10 / 100;
        }

        biaya -= biaya * pelanggan.getDiskon() / 100;

        return biaya;
    }

    // Menyewakan unit PlayStation
    static void sewaUnit() {
        perbaruiStatusSewa();

        System.out.println("\n===== PENYEWAAN PLAYSTATION =====");

        Pelanggan pelanggan;
        String nama;

        System.out.print("Apakah pelanggan member? (y/n): ");
        String pilihanMember = input.next();

        if (pilihanMember.equalsIgnoreCase("y")) {

            if (daftarMember.isEmpty()) {
                System.out.println("Belum ada member terdaftar.");
                return;
            }

            tampilkanMember();

            System.out.print("Pilih nomor member: ");

            if (!input.hasNextInt()) {
                System.out.println("Input harus berupa angka.");
                input.next();
                return;
            }

            int nomorMember = input.nextInt();

            if (nomorMember < 1
                    || nomorMember > daftarMember.size()) {
                System.out.println("Nomor member tidak valid.");
                return;
            }

            nama = daftarMember.get(nomorMember - 1);
            pelanggan = new Member(nama);

        } else if (pilihanMember.equalsIgnoreCase("n")) {

            input.nextLine();

            System.out.print("Nama pelanggan: ");
            nama = input.nextLine().trim();

            if (nama.isEmpty()) {
                System.out.println("Nama tidak boleh kosong.");
                return;
            }

            pelanggan = new NonMember(nama);

        } else {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        tampilkanUnit();

        System.out.print("\nPilih nomor unit: ");

        if (!input.hasNextInt()) {
            System.out.println("Input harus berupa angka.");
            input.next();
            return;
        }

        int nomorUnit = input.nextInt();

        if (nomorUnit < 1 || nomorUnit > daftarUnit.size()) {
            System.out.println("Nomor unit tidak valid.");
            return;
        }

        UnitPS unit = daftarUnit.get(nomorUnit - 1);

        if (!unit.getStatus().equals("Ready")) {
            System.out.println("Unit sedang digunakan.");
            return;
        }

        System.out.print("Durasi sewa (jam): ");

        if (!input.hasNextInt()) {
            System.out.println("Durasi harus berupa angka.");
            input.next();
            return;
        }

        int durasi = input.nextInt();

        if (durasi <= 0) {
            System.out.println("Durasi harus lebih dari 0.");
            return;
        }

        // Input jam mulai manual
        String jamMulai;

        while (true) {
            System.out.print("Jam mulai (HH:mm): ");
            jamMulai = input.next();

            try {
                LocalTime waktuInput =
                        LocalTime.parse(jamMulai, formatJam);

                LocalTime sekarang =
                        LocalTime.now().withSecond(0).withNano(0);

                if (waktuInput.isBefore(sekarang)) {
                    System.out.println(
                            "Jam mulai tidak boleh lebih awal dari waktu sekarang."
                    );
                    continue;
                }

                break;

            } catch (DateTimeParseException e) {
                System.out.println(
                        "Format jam tidak valid. Contoh: 18:30"
                );
            }
        }

        LocalTime waktuSelesai =
                LocalTime.parse(jamMulai, formatJam).plusHours(durasi);

        int total = hitungBiaya(
                durasi, unit.getTarif(), pelanggan
        );

        System.out.println("\n===== RINCIAN SEWA =====");
        System.out.println("Nama Pelanggan : " + pelanggan.getNama());
        System.out.println("Unit           : " + unit.getNama());
        System.out.println("Ruangan        : " + unit.getRuangan());
        System.out.println("Tarif          : Rp" + unit.getTarif());
        System.out.println("Durasi         : " + durasi + " jam");
        System.out.println("Jam Mulai      : " + jamMulai);
        System.out.println("Jam Selesai    : "
                + waktuSelesai.format(formatJam));
        System.out.println("Total Biaya    : Rp" + total);

        if (durasi > 5) {
            System.out.println("Diskon Durasi  : 10%");
        }

        if (pelanggan.getDiskon() > 0) {
            System.out.println("Diskon Member  : "
                    + pelanggan.getDiskon() + "%");
        }

        // Metode pembayaran
        System.out.println("\nMetode Pembayaran");
        System.out.println("1. Cash");
        System.out.println("2. QRIS");
        System.out.print("Pilih metode pembayaran: ");

        if (!input.hasNextInt()) {
            System.out.println("Pilihan harus berupa angka.");
            input.next();
            return;
        }

        int pilihanMetode = input.nextInt();

        String metode;

        if (pilihanMetode == 1) {
            metode = "Cash";
        } else if (pilihanMetode == 2) {
            metode = "QRIS";
        } else {
            System.out.println("Metode pembayaran tidak valid.");
            return;
        }

        // Proses pembayaran
        int pembayaran = 0;

        while (pembayaran < total) {
            System.out.println("Total yang harus dibayar: Rp" + total);
            System.out.println("Sudah dibayar: Rp" + pembayaran);

            System.out.print("Masukkan nominal pembayaran: ");

            if (!input.hasNextInt()) {
                System.out.println("Nominal harus berupa angka.");
                input.next();
                continue;
            }

            int nominal = input.nextInt();

            if (nominal <= 0) {
                System.out.println("Nominal harus lebih dari 0.");
                continue;
            }

            pembayaran += nominal;

            if (pembayaran < total) {
                System.out.println("Pembayaran masih kurang Rp"
                        + (total - pembayaran));
            }
        }

        int kembalian = pembayaran - total;

        // Membuat transaksi setelah pembayaran berhasil
        Transaksi transaksi = new Transaksi(
                nomorTransaksi,
                pelanggan,
                unit,
                durasi,
                jamMulai,
                total,
                pembayaran,
                metode
        );

        riwayat.add(transaksi);
        unit.setStatus("Lagi dipake");

        // Memastikan status diperbarui sebelum struk ditampilkan
        perbaruiStatusSewa();

        System.out.println("\n===== STRUK PENYEWAAN =====");
        System.out.println("Nomor Transaksi : " + nomorTransaksi);
        System.out.println("Nama Pelanggan  : " + pelanggan.getNama());
        System.out.println("Unit            : " + unit.getNama());
        System.out.println("Ruangan         : " + unit.getRuangan());
        System.out.println("Durasi          : " + durasi + " jam");
        System.out.println("Jam Mulai       : " + transaksi.getJamMulai());
        System.out.println("Jam Selesai     : " + transaksi.getJamSelesai());
        System.out.println("Total           : Rp" + total);
        System.out.println("Pembayaran      : Rp" + pembayaran);
        System.out.println("Metode          : " + metode);
        System.out.println("Kembalian       : Rp" + kembalian);

        if (transaksi.getStatus().equals("Berlangsung")) {
            System.out.println("Sisa Waktu      : "
                    + formatSisaWaktu(transaksi.getSisaWaktu()));
        } else {
            System.out.println("Sisa Waktu      : 00:00:00");
        }

        System.out.println("Status          : " + transaksi.getStatus());

        nomorTransaksi++;
    }

    // Menyelesaikan penyewaan secara manual
    static void selesaiSewa() {
        perbaruiStatusSewa();

        System.out.println("\n===== SELESAIKAN PENYEWAAN =====");
        System.out.print("Masukkan nomor transaksi: ");

        if (!input.hasNextInt()) {
            System.out.println("Nomor transaksi harus berupa angka.");
            input.next();
            return;
        }

        int id = input.nextInt();

        for (Transaksi transaksi : riwayat) {
            if (transaksi.getId() == id) {

                if (transaksi.getStatus().equals("Selesai")) {
                    System.out.println("Transaksi sudah selesai.");
                    return;
                }

                transaksi.setStatus("Selesai");
                transaksi.getUnit().setStatus("Ready");

                System.out.println("Penyewaan berhasil diselesaikan.");
                System.out.println("Unit sekarang tersedia kembali.");

                return;
            }
        }

        System.out.println("Transaksi tidak ditemukan.");
    }

    // Menampilkan riwayat transaksi
    static void tampilkanRiwayat() {
        perbaruiStatusSewa();

        System.out.println("\n===== RIWAYAT TRANSAKSI =====");

        if (riwayat.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        for (Transaksi transaksi : riwayat) {
            System.out.println("\nNomor Transaksi : "
                    + transaksi.getId());

            System.out.println("Nama Pelanggan  : "
                    + transaksi.getPelanggan().getNama());

            System.out.println("Unit            : "
                    + transaksi.getUnit().getNama());

            System.out.println("Ruangan         : "
                    + transaksi.getUnit().getRuangan());

            System.out.println("Durasi          : "
                    + transaksi.getDurasi() + " jam");

            System.out.println("Jam Mulai       : "
                    + transaksi.getJamMulai());

            System.out.println("Jam Selesai     : "
                    + transaksi.getJamSelesai());

            System.out.println("Total Biaya     : Rp"
                    + transaksi.getTotal());

            System.out.println("Pembayaran      : Rp"
                    + transaksi.getPembayaran());

            System.out.println("Metode          : "
                    + transaksi.getMetode());

            System.out.println("Status          : "
                    + transaksi.getStatus());

            if (transaksi.getStatus().equals("Berlangsung")) {
                System.out.println("Sisa Waktu      : "
                        + formatSisaWaktu(transaksi.getSisaWaktu()));
            }
        }
    }

    // Menu utama
    public static void main(String[] args) {
        isiUnit();

        int pilihan = 0;

        do {
            perbaruiStatusSewa();

            System.out.println("\n===== SISTEM PENGELOLAAN RENTAL PS =====");
            System.out.println("1. Sewa PlayStation");
            System.out.println("2. Lihat Status Unit");
            System.out.println("3. Selesaikan Penyewaan");
            System.out.println("4. Riwayat Transaksi");
            System.out.println("5. Daftar Member");
            System.out.println("6. Keluar");

            System.out.print("Pilih menu: ");

            if (!input.hasNextInt()) {
                System.out.println("Pilihan harus berupa angka.");
                input.next();
                continue;
            }

            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    sewaUnit();
                    break;

                case 2:
                    tampilkanUnit();
                    break;

                case 3:
                    selesaiSewa();
                    break;

                case 4:
                    tampilkanRiwayat();
                    break;

                case 5:
                    tambahMember();
                    break;

                case 6:
                    System.out.println(
                            "Terima kasih telah menggunakan Sistem Rental PlayStation."
                    );
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 6);

        input.close();
    }
}
