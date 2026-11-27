
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Transaksi {

    private int id;
    private Pelanggan pelanggan;
    private UnitPS unit;
    private int durasi;

    private LocalDateTime waktuMulai;
    private LocalDateTime waktuSelesai;

    private int total;
    private int pembayaran;
    private String metode;
    private String status;

    private static final DateTimeFormatter formatJam =
            DateTimeFormatter.ofPattern("HH:mm");

    public Transaksi(int id, Pelanggan pelanggan, UnitPS unit,
                     int durasi, String jamMulai, int total,
                     int pembayaran, String metode) {

        this.id = id;
        this.pelanggan = pelanggan;
        this.unit = unit;
        this.durasi = durasi;

        LocalTime jam = LocalTime.parse(jamMulai, formatJam);

        this.waktuMulai = LocalDateTime.of(
                LocalDateTime.now().toLocalDate(), jam
        );

        this.waktuSelesai = waktuMulai.plusHours(durasi);

        this.total = total;
        this.pembayaran = pembayaran;
        this.metode = metode;
        this.status = "Berlangsung";
    }

    public int getId() {
        return id;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public UnitPS getUnit() {
        return unit;
    }

    public int getDurasi() {
        return durasi;
    }

    public String getJamMulai() {
        return waktuMulai.format(formatJam);
    }

    public String getJamSelesai() {
        return waktuSelesai.format(formatJam);
    }

    public int getTotal() {
        return total;
    }

    public int getPembayaran() {
        return pembayaran;
    }

    public String getMetode() {
        return metode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Menghitung sisa waktu penyewaan
    public Duration getSisaWaktu() {

        LocalDateTime sekarang = LocalDateTime.now();

        if (sekarang.isBefore(waktuMulai)) {
            return Duration.between(sekarang, waktuSelesai);
        }

        Duration sisa = Duration.between(
                sekarang, waktuSelesai
        );

        if (sisa.isNegative()) {
            return Duration.ZERO;
        }

        return sisa;
    }
}
