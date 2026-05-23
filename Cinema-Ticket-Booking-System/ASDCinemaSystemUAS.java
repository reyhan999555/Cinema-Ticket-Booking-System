import java.util.Scanner;

class Tiket {

    int id, kursi, harga;
    String film, kategori, hari, status;
    String[] jam;
    String[][] seat = new String[5][5];

    Tiket(int id, String film, String kategori,
           int kursi, String[] jam, String hari) {

        this.id = id;
        this.film = film;
        this.kategori = kategori;
        this.kursi = kursi;
        this.jam = jam;
        this.hari = hari;
        this.status = "Tersedia";

        harga =
            kategori.equalsIgnoreCase("Action") ? 50000 :
            kategori.equalsIgnoreCase("Drama") ? 40000 :
            kategori.equalsIgnoreCase("Animation") ? 30000 :
            kategori.equalsIgnoreCase("Sci-Fi") ? 55000 :
            35000;

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                seat[i][j] = "[ ]";
            }
        }
    }
}

public class ASDCinemaSystemUAS {

    static Scanner in = new Scanner(System.in);

    static Tiket[] data = new Tiket[100];
    static int n = 0;

    // ===== GABUNG JAM =====
    static String gabungJam(String[] jam) {

        return String.join(", ", jam);
    }

    // ===== AUTO DATA =====
    static void generateData() {

        String[][] filmData = {

            {"Avengers","Action","Senin"},
            {"Batman","Action","Selasa"},
            {"Spiderman","Action","Rabu"},
            {"Frozen","Animation","Kamis"},
            {"Joker","Drama","Jumat"},
            {"Titanic","Drama","Sabtu"},
            {"Avatar","Sci-Fi","Minggu"},
            {"Inception","Sci-Fi","Senin"},
            {"Up","Animation","Selasa"},
            {"Coco","Animation","Rabu"},
            {"Interstellar","Sci-Fi","Kamis"},
            {"Dune","Sci-Fi","Jumat"},
            {"Cars","Animation","Sabtu"},
            {"Minions","Animation","Minggu"},
            {"Aladdin","Animation","Senin"},
            {"Matrix","Sci-Fi","Selasa"},
            {"Thor","Action","Rabu"},
            {"Iron Man","Action","Kamis"},
            {"Hulk","Action","Jumat"},
            {"Doctor Strange","Sci-Fi","Sabtu"},
            {"Transformers","Sci-Fi","Minggu"},
            {"Kungfu Panda","Animation","Senin"},
            {"Moana","Animation","Selasa"},
            {"Encanto","Animation","Rabu"},
            {"Naruto","Action","Kamis"},
            {"One Piece","Action","Jumat"},
            {"Jujutsu Kaisen","Action","Sabtu"},
            {"Demon Slayer","Action","Minggu"},
            {"John Wick","Action","Senin"},
            {"Fast Furious","Action","Selasa"}
        };

        String[][] jam = {

            {"10:00","14:00","19:00"},
            {"11:00","15:00","20:00"},
            {"09:00","13:00","18:00"},
            {"12:00","16:00","21:00"}
        };

        for (int i = 0; i < filmData.length; i++) {

            data[n++] = new Tiket(

                i + 1,
                filmData[i][0],
                filmData[i][1],
                25,
                jam[i % jam.length],
                filmData[i][2]
            );
        }

        // ===== SIMULASI KURSI TERISI =====

        data[0].kursi = 20;

        data[0].seat[0][0] = "[X]";
        data[0].seat[0][1] = "[X]";
        data[0].seat[0][2] = "[X]";
        data[0].seat[0][3] = "[X]";
        data[0].seat[0][4] = "[X]";

        data[1].kursi = 10;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 5; j++) {

                data[1].seat[i][j] = "[X]";
            }
        }

        data[2].kursi = 5;
        data[3].kursi = 2;

        data[4].kursi = 0;
        data[4].status = "Habis";
    }

    // ===== READ =====
    static void tampilData() {

        System.out.println(
        "\n================================================================================================");

        System.out.printf(
        "%-3s %-18s %-12s %-8s %-25s %-10s %-10s\n",

        "ID","FILM","KATEGORI",
        "KURSI","JAM","HARI","STATUS");

        System.out.println(
        "================================================================================================");

        for (int i = 0; i < n; i++) {

            System.out.printf(
            "%-3d %-18s %-12s %-8d %-25s %-10s %-10s\n",

            data[i].id,
            data[i].film,
            data[i].kategori,
            data[i].kursi,
            gabungJam(data[i].jam),
            data[i].hari,
            data[i].status
            );
        }
    }

    // ===== CREATE =====
    static void tambahData() {

        System.out.print("ID: ");
        int id = in.nextInt();
        in.nextLine();

        System.out.print("Film: ");
        String film = in.nextLine();

        System.out.print("Kategori: ");
        String kategori = in.nextLine();

        System.out.print("Hari: ");
        String hari = in.nextLine();

        String[] jam = new String[3];

        for (int i = 0; i < 3; i++) {

            System.out.print(
            "Jam ke-" + (i + 1) + ": ");

            jam[i] = in.nextLine();
        }

        data[n++] = new Tiket(
        id, film, kategori, 25, jam, hari);

        System.out.println(
        "Data berhasil ditambah!");
    }

    // ===== UPDATE =====
    static void editData() {

        System.out.print("Masukkan ID: ");
        int id = in.nextInt();
        in.nextLine();

        for (Tiket t : data) {

            if (t != null && t.id == id) {

                System.out.print("Film baru: ");
                t.film = in.nextLine();

                System.out.print("Kategori baru: ");
                t.kategori = in.nextLine();

                System.out.print("Hari baru: ");
                t.hari = in.nextLine();

                System.out.println(
                "Data berhasil diubah!");

                return;
            }
        }

        System.out.println(
        "ID tidak ditemukan!");
    }

    // ===== DELETE =====
    static void hapusData() {

        System.out.print("Masukkan ID: ");
        int id = in.nextInt();

        for (Tiket t : data) {

            if (t != null && t.id == id) {

                t.status = "Dihapus";

                System.out.println(
                "Data berhasil dihapus!");

                return;
            }
        }

        System.out.println(
        "ID tidak ditemukan!");
    }

    // ===== SEARCH NAMA =====
    static void cariNama() {

        in.nextLine();

        System.out.print("Cari film: ");
        String cari = in.nextLine();

        boolean ketemu = false;

        for (Tiket t : data) {

            if (t != null &&
                t.film.equalsIgnoreCase(cari)) {

                System.out.println(

                t.film + " | " +
                gabungJam(t.jam) + " | " +
                t.hari
                );

                ketemu = true;
            }
        }

        if (!ketemu)
            System.out.println(
            "Film tidak ditemukan!");
    }

    // ===== SEARCH KATEGORI =====
    static void cariKategori() {

        in.nextLine();

        System.out.print("Cari kategori: ");
        String kategori = in.nextLine();

        boolean ketemu = false;

        for (Tiket t : data) {

            if (t != null &&
                t.kategori.equalsIgnoreCase(kategori)) {

                System.out.println(

                t.film + " | " +
                gabungJam(t.jam) + " | " +
                t.hari
                );

                ketemu = true;
            }
        }

        if (!ketemu)
            System.out.println(
            "Kategori tidak ditemukan!");
    }

    // ===== SORT ID =====
    static void sortID() {

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (data[j].id > data[j + 1].id) {

                    Tiket temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }

        tampilData();
    }

    // ===== SORT NAMA =====
    static void sortNama() {

        for (int i = 0; i < n - 1; i++) {

            int min = i;

            for (int j = i + 1; j < n; j++) {

                if (data[j].film.compareTo(
                    data[min].film) < 0) {

                    min = j;
                }
            }

            Tiket temp = data[i];
            data[i] = data[min];
            data[min] = temp;
        }

        tampilData();
    }

    // ===== SORT KURSI DESC =====
    static void sortKursiTerbanyak() {

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (data[j].kursi <
                    data[j + 1].kursi) {

                    Tiket temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }

        tampilData();
    }

    // ===== SEARCH ID =====
    static void cariID() {

        sortID();

        System.out.print("Cari ID: ");
        int id = in.nextInt();

        int l = 0, r = n - 1;

        while (l <= r) {

            int mid = (l + r) / 2;

            if (data[mid].id == id) {

                System.out.println(

                "\nFilm     : " +
                data[mid].film +

                "\nKategori : " +
                data[mid].kategori +

                "\nJam      : " +
                gabungJam(data[mid].jam) +

                "\nHari     : " +
                data[mid].hari +

                "\nKursi    : " +
                data[mid].kursi
                );

                return;
            }

            if (data[mid].id < id)
                l = mid + 1;
            else
                r = mid - 1;
        }

        System.out.println(
        "Data tidak ditemukan!");
    }

    // ===== TAMPIL KURSI =====
    static void tampilKursi(Tiket t) {

        System.out.println(
        "\n=== TEMPAT DUDUK ===\n");

        for (int i = 0; i < 5; i++) {

            char baris = (char) ('A' + i);

            for (int j = 0; j < 5; j++) {

                System.out.print(

                baris + "" + (j + 1) + " " +
                t.seat[i][j] + "   ");
            }

            System.out.println();
        }

        System.out.println(
        "\n[ ] = tersedia");

        System.out.println(
        "[X] = sudah dipesan");
    }

    // ===== BELI TIKET =====
    static void beliTiket() {

        in.nextLine();

        // ===== PILIH HARI =====

        System.out.println(
        "\n=========== PILIH HARI ===========");

        System.out.println("1. Senin");
        System.out.println("2. Selasa");
        System.out.println("3. Rabu");
        System.out.println("4. Kamis");
        System.out.println("5. Jumat");
        System.out.println("6. Sabtu");
        System.out.println("7. Minggu");

        System.out.print(
        "\nPilih hari: ");

        int pilihHari = in.nextInt();
        in.nextLine();

        String hari = "";

        switch (pilihHari) {

            case 1: hari = "Senin"; break;
            case 2: hari = "Selasa"; break;
            case 3: hari = "Rabu"; break;
            case 4: hari = "Kamis"; break;
            case 5: hari = "Jumat"; break;
            case 6: hari = "Sabtu"; break;
            case 7: hari = "Minggu"; break;

            default:

                System.out.println(
                "Hari tidak valid!");

                return;
        }

        // ===== TAMPIL FILM =====

        System.out.println(
        "\n=========== FILM HARI " +
        hari.toUpperCase() +
        " ===========");

        System.out.printf(
        "%-3s %-18s %-25s %-8s\n",
        "ID","FILM","JAM","KURSI");

        for (Tiket t : data) {

            if (t != null &&
                t.hari.equalsIgnoreCase(hari) &&
                t.status.equals("Tersedia")) {

                System.out.printf(
                "%-3d %-18s %-25s %-8d\n",

                t.id,
                t.film,
                gabungJam(t.jam),
                t.kursi
                );
            }
        }

        // ===== PILIH FILM =====

        System.out.print(
        "\nMasukkan ID Film: ");

        int id = in.nextInt();
        in.nextLine();

        for (Tiket t : data) {

            if (t != null &&
                t.id == id &&
                t.status.equals("Tersedia")) {

                System.out.println(
                "\nFilm : " + t.film);

                System.out.println(
                "Hari : " + t.hari);

                System.out.println(
                "Kursi tersedia : " + t.kursi);

                // ===== PILIH JAM =====

                System.out.println(
                "\n=== PILIH JAM ===");

                for (int i = 0;
                     i < t.jam.length; i++) {

                    System.out.println(
                    (i + 1) + ". " +
                    t.jam[i]);
                }

                System.out.print(
                "Pilih jam: ");

                int pilihJam = in.nextInt();
                in.nextLine();

                System.out.println(
                "\nJam dipilih : " +
                t.jam[pilihJam - 1]);

                tampilKursi(t);

                System.out.print(
                "\nJumlah tiket: ");

                int jumlah = in.nextInt();
                in.nextLine();

                if (jumlah <= t.kursi) {

                    String kursiDipilih = "";

                    for (int i = 0;
                         i < jumlah; i++) {

                        System.out.print(
                        "Pilih kursi: ");

                        String kursi =
                        in.nextLine().toUpperCase();

                        int b =
                        kursi.charAt(0) - 'A';

                        int k =
                        Integer.parseInt(
                        kursi.substring(1)) - 1;

                        if (t.seat[b][k]
                            .equals("[X]")) {

                            System.out.println(
                            "Kursi sudah dipesan!");

                            i--;

                        } else {

                            t.seat[b][k] = "[X]";

                            kursiDipilih += kursi;

                            if (i != jumlah - 1)
                                kursiDipilih += ", ";
                        }
                    }

                    t.kursi -= jumlah;

                    if (t.kursi == 0)
                        t.status = "Habis";

                    // ===== STRUK =====

                    System.out.println(
                    "\nPembelian berhasil!");

                    System.out.println(
                    "\n========================================");

                    System.out.println(
                    "             CINEMA XXI");

                    System.out.println(
                    "           ENJOY THE MOVIE!");

                    System.out.println(
                    "========================================");

                    System.out.println(
                    "ID Transaksi : TX" +
                    System.currentTimeMillis());

                    System.out.println(
                    "Tanggal      : 23 Mei 2026");

                    System.out.println(
                    "Waktu        : 12:30");

                    System.out.println(
                    "----------------------------------------");

                    System.out.println(
                    "Film         : " + t.film);

                    System.out.println(
                    "Kategori     : " + t.kategori);

                    System.out.println(
                    "Hari Tayang  : " + t.hari);

                    System.out.println(
                    "Jam Tayang   : " +
                    t.jam[pilihJam - 1]);

                    System.out.println(
                    "Studio       : Studio 1");

                    System.out.println(
                    "----------------------------------------");

                    System.out.println(
                    "Kursi        : " +
                    kursiDipilih);

                    System.out.println(
                    "Jumlah Tiket : " +
                    jumlah);

                    System.out.println(
                    "Harga/Tiket  : Rp" +
                    t.harga);

                    System.out.println(
                    "----------------------------------------");

                    System.out.println(
                    "TOTAL BAYAR  : Rp" +
                    (jumlah * t.harga));

                    System.out.println(
                    "========================================");

                    System.out.println(
                    "   TERIMA KASIH TELAH MENONTON");

                    System.out.println(
                    "     MOHON DATANG KEMBALI :)");

                    System.out.println(
                    "========================================");

                } else {

                    System.out.println(
                    "Kursi tidak cukup!");
                }

                return;
            }
        }

        System.out.println(
        "Film tidak ditemukan!");
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        generateData();

        int pilih;

        do {

            System.out.println(
            "\n=========== MENU BIOSKOP ===========");

            System.out.println("1. Create");
            System.out.println("2. Read");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Cari Nama");
            System.out.println("6. Cari Kategori");
            System.out.println("7. Sort ID");
            System.out.println("8. Cari ID");
            System.out.println("9. Sort Nama");
            System.out.println("10. Beli Tiket");
            System.out.println("11. Sort Kursi Terbanyak");
            System.out.println("0. Keluar");

            System.out.print("\nPilih: ");
            pilih = in.nextInt();

            switch (pilih) {

                case 1:
                    tambahData();
                    break;

                case 2:
                    tampilData();
                    break;

                case 3:
                    editData();
                    break;

                case 4:
                    hapusData();
                    break;

                case 5:
                    cariNama();
                    break;

                case 6:
                    cariKategori();
                    break;

                case 7:
                    sortID();
                    break;

                case 8:
                    cariID();
                    break;

                case 9:
                    sortNama();
                    break;

                case 10:
                    beliTiket();
                    break;

                case 11:
                    sortKursiTerbanyak();
                    break;
            }

        } while (pilih != 0);
    }
}