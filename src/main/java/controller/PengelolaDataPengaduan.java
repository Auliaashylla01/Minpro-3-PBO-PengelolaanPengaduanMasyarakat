/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.ArrayList;
import java.util.Scanner;
//import model pengaduan untuk mengelola data dari superclass dan sub class
import model.Pengaduan;
import model.PengaduanBiasa;
import model.PengaduanDarurat;
import model.PenangananKhusus;
import view.PengaduanView;
/**
 * Class PengelolaDataPengaduan adalah sebagai Controller.
 * Class PengelolaPengaduan berfungsi untuk mengelola daftar atau data objek Pengaduan.
 * @author ASUS
 */
public class PengelolaDataPengaduan {
    //Kumpulan data pengaduan disimpan dalam bentuk ArrayList
    private ArrayList<Pengaduan> daftarPengaduan;
    private PengaduanView view;
    private Scanner scanner;


    //Constructor untuk mengatur list pengaduan agar dapat digunakan (tidak null)
    public PengelolaDataPengaduan(PengaduanView view) {
        this.view = view;
        this.scanner = new Scanner(System.in);
        daftarPengaduan = new ArrayList<>();
        muatData();
    }
    // Method untuk memulai program. Menu ditampilkan berulang
    // sampai pengguna memilih menu Keluar.
    public void mulai() {
        int menu;

        do {
            view.tampilkanHeader();
            view.tampilkanMenu();

            menu = ValidasiInput.inputMenu(scanner);

            // Menjalankan fitur sesuai menu yang dipilih
            switch (menu) {
                case 1 -> prosesTambahPengaduan();
                case 2 -> prosesLihatPengaduan();
                case 3 -> prosesUbahStatus();
                case 4 -> prosesHapusPengaduan();
                case 5 -> view.tampilkanSalam();
            }

            // Jeda agar output tidak langsung hilang
            if (menu != 5) {
                view.tampilkanPesanLanjut();
                scanner.nextLine();
            }
        } while (menu != 5);

        scanner.close();
    }
    
     // Memproses menu tambah pengaduan
    private void prosesTambahPengaduan() {
        view.tampilkanJudul("TAMBAH PENGADUAN");
        String id = generateId();
        view.tampilkanIdOtomatis(id);

        String nama = ValidasiInput.inputNamaPelapor(scanner, "Masukkan Nama Pelapor: ");

        view.tampilkanPilihanJenisPengaduan();
        String jenis = ValidasiInput.inputJenisPengaduan(scanner);

        String isi = ValidasiInput.inputTidakKosong(scanner, "Masukkan Isi Pengaduan: ");
        String tanggal = ValidasiInput.inputTanggal(scanner, "Masukkan Tanggal (dd/mm/yyyy): ");

        // Tingkat urgensi menentukan objek subclass yang dibuat
        view.tampilkanPilihanTingkatUrgensi();
        int urgensi = ValidasiInput.inputPilihan(scanner, "Pilihan (1/2): ", 1, 2);

        if (urgensi == 1) {
            tambahPengaduanBiasa(id, nama, jenis, isi, tanggal);
            view.tampilkanSukses("Pengaduan (BIASA) berhasil ditambahkan ke dalam sistem!");
        } else {
            String kontak = ValidasiInput.inputKontakDarurat(scanner, "Masukkan Kontak Darurat (contoh: 081234567890) : ");
            tambahPengaduanDarurat(id, nama, jenis, isi, tanggal, kontak);
            view.tampilkanSukses("Pengaduan (DARURAT) berhasil ditambahkan ke dalam sistem!");
        }
        view.tampilkanPesan("Status awal   : Menunggu Konfirmasi Petugas");

        // Pengaduan yang menerapkan interface PenangananKhusus (hanya darurat)
        // langsung mengirim notifikasi. instanceof mengecek apakah objek
        // memenuhi kontrak interface tersebut.
        Pengaduan pengaduanBaru = cariPengaduan(id);

        if (pengaduanBaru instanceof PenangananKhusus) {
            ((PenangananKhusus) pengaduanBaru).kirimNotifikasiDarurat();
        }
    }

    // Memproses menu lihat pengaduan: semua data, atau dicari berdasarkan
    // ID, status, jenis, serta jenis dan status.
    // Memakai method cariPengaduan yang di-overload.
    private void prosesLihatPengaduan() {
        view.tampilkanJudul("LIHAT PENGADUAN");
        view.tampilkanMenuLihat();
        int pilihan = ValidasiInput.inputPilihan(scanner, "Pilih cara melihat data: ", 1, 5);

        if (pilihan == 1) {
            // Tampilkan seluruh data
            view.tampilkanDaftarPengaduan(daftarPengaduan);
        } else if (pilihan == 2) {
            // Cari berdasarkan ID: hasilnya 1 objek
            String id = ValidasiInput.inputidPengaduan(scanner, "Masukkan ID Pengaduan(contoh: P001): ");
            Pengaduan p = cariPengaduan(id);

            if (p == null) {
                view.tampilkanError("ID Pengaduan tidak ditemukan! Silakan periksa kembali ID yang dimasukkan.");
            } else {
                view.tampilkanDetailPengaduan(p);
            }
        } else {
            // Cari berdasarkan status, jenis, atau jenis dan status: hasilnya berupa list
            ArrayList<Pengaduan> hasil;

            if (pilihan == 3) {
                view.tampilkanPilihanStatus();
                String status = ValidasiInput.inputStatus(scanner);
                hasil = cariPengaduanByStatus(status);
            } else if (pilihan == 4) {
                view.tampilkanPilihanJenisPengaduan();
                String jenis = ValidasiInput.inputJenisPengaduan(scanner);
                hasil = cariPengaduanByJenis(jenis);
            } else {
                view.tampilkanPilihanJenisPengaduan();
                String jenis = ValidasiInput.inputJenisPengaduan(scanner);
                view.tampilkanPilihanStatus();
                String status = ValidasiInput.inputStatus(scanner);
                hasil = cariPengaduan(jenis, status);
            }

            if (hasil.isEmpty()) {
                view.tampilkanInfo("Tidak ada pengaduan yang sesuai dengan pencarian.");
            } else {
                view.tampilkanDaftarPengaduan(hasil);
            }
        }
    }

    // Memproses menu ubah status pengaduan
    private void prosesUbahStatus() {
        view.tampilkanJudul("UBAH STATUS PENGADUAN");
        String id = ValidasiInput.inputidPengaduan(scanner, "Masukkan ID Pengaduan(contoh: P001): ");
        Pengaduan p = cariPengaduan(id);

        if (p == null) {
            view.tampilkanError("ID Pengaduan tidak ditemukan! Silakan periksa kembali ID yang dimasukkan.");
            return;
        }

        String statusBerikutnya = statusBerikutnya(p.getStatus());
        view.tampilkanStatusUbah(p, statusBerikutnya);

        // Status sudah di tahap terakhir, tidak ada yang bisa diubah
        if (statusBerikutnya == null) {
            return;
        }

        boolean lanjut = ValidasiInput.inputKonfirmasi(scanner);

        if (lanjut) {
            ubahStatus(id, statusBerikutnya);
            view.tampilkanSukses("Status pengaduan berhasil diperbarui menjadi \"" + statusBerikutnya + "\".");
        } else {
            view.tampilkanInfo("Perubahan status dibatalkan.");
        }
    }

    // Memproses menu hapus pengaduan
    private void prosesHapusPengaduan() {
        view.tampilkanJudul("HAPUS PENGADUAN");
        String id = ValidasiInput.inputidPengaduan(scanner, "Masukkan ID Pengaduan(contoh: P001): ");
        Pengaduan p = cariPengaduan(id);

        if (p == null) {
            view.tampilkanError("ID Pengaduan tidak ditemukan! Silakan periksa kembali ID Anda.");
            return;
        }

        view.tampilkanKonfirmasiHapus(p);
        boolean konfirmasi = ValidasiInput.inputKonfirmasi(scanner);

        if (konfirmasi) {
            hapusPengaduan(id);
            view.tampilkanSukses("Data pengaduan berhasil dihapus dari sistem.");
        } else {
            view.tampilkanInfo("Proses penghapusan data pengaduan dibatalkan.");
        }
    }
    
    //Metode privat untuk menambahkan data dummy ke dalam daftar.
    private void muatData(){
        Pengaduan p1 = new PengaduanBiasa(
                generateId(),
                "Budi Santoso",
                "Jalan",
                "Jalan berlubang cukup dalam di depan gang, mengganggu pengendara motor.",
                "20/09/2026"
        );
        daftarPengaduan.add(p1);
        Pengaduan p2 = new PengaduanDarurat (
                generateId(),
                "Siti Aminah",
                "Keamanan",
                "Terjadi percikan api di panel listrik dekat pasar, berisiko kebakaran.",
                "21/09/2026",
                "081234567890"
        );
        daftarPengaduan.add(p2);
    }
    
   //Mengecek apakah suatu ID Pengaduan sudah ada di dalam list.
    public boolean idSudahAda(String idPengaduan) {

        for (Pengaduan pengaduan : daftarPengaduan) {

            if (pengaduan.getIdPengaduan().equalsIgnoreCase(idPengaduan)) {
                return true; //ID ditemukan
            }
        }

        return false; // ID belum pernah dipakai
    }
    //Membuat ID Pengaduan otomatis dengan format P001, P002, dan seterusnya.
    public String generateId() {

    int nomor = 1;
    String idBaru;

    do {
        idBaru = String.format("P%03d", nomor); // Format 3 digit angka (P001)
        nomor++;
    } while (idSudahAda(idBaru));

    return idBaru;
}

    //Menambahkan data Pengaduan biasa baru ke dalam ArrayList.
    public void tambahPengaduanBiasa(String id, String nama, String jenis,
                String isi, String tanggal) {
        Pengaduan p = new PengaduanBiasa(id, nama, jenis, isi, tanggal);
        daftarPengaduan.add(p);
    }
    //Menambahkan data Pengaduan darurat baru ke dalam ArrayList.
    public void tambahPengaduanDarurat(String id, String nama, String jenis, String isi,
                String tanggal, String kontakDarurat) {
        Pengaduan p = new PengaduanDarurat(id, nama, jenis, isi, tanggal, kontakDarurat);
        daftarPengaduan.add(p);
    }
    
    // Overloading 1: cari berdasarkan ID, hasilnya 1 objek Pengaduan
    public Pengaduan cariPengaduan(String idPengaduan) {
        for (Pengaduan pengaduan : daftarPengaduan) {
            if (pengaduan.getIdPengaduan().equalsIgnoreCase(idPengaduan)) {
                return pengaduan;
            }
        }
        // Pengaduan tidak ditemukan
        return null;
    }

    // Overloading 2: cari berdasarkan jenis dan status sekaligus,
    // hasilnya berupa ArrayList karena bisa lebih dari satu data
    public ArrayList<Pengaduan> cariPengaduan(String jenisPengaduan, String status) {
        ArrayList<Pengaduan> hasilFilter = new ArrayList<>();

        for (Pengaduan pengaduan : daftarPengaduan) {
            if (pengaduan.getJenisPengaduan().equalsIgnoreCase(jenisPengaduan)
                    && pengaduan.getStatus().equalsIgnoreCase(status)) {
                hasilFilter.add(pengaduan);
            }
        }
        return hasilFilter;
    }
    
    public ArrayList<Pengaduan> cariPengaduanByStatus(String status) {
        ArrayList<Pengaduan> hasilFilter = new ArrayList<>();

        for (Pengaduan pengaduan : daftarPengaduan) {
            if (pengaduan.getStatus().equalsIgnoreCase(status)) {
                hasilFilter.add(pengaduan);
            }
        }
        return hasilFilter;
    }
    
    public ArrayList<Pengaduan> cariPengaduanByJenis(String jenisPengaduan) {
        ArrayList<Pengaduan> hasilFilter = new ArrayList<>();

        for (Pengaduan pengaduan : daftarPengaduan) {
            if (pengaduan.getJenisPengaduan().equalsIgnoreCase(jenisPengaduan)) {
                hasilFilter.add(pengaduan);
            }
        }
        return hasilFilter;
    }
    
    public String statusBerikutnya(String statusSaatIni) {
        if (statusSaatIni.equals("Menunggu Konfirmasi Petugas")) {
            return "Sedang Diproses";
        }

        if (statusSaatIni.equals("Sedang Diproses")) {
            return "Selesai Ditindaklanjuti";
        }

        // Sudah di tahap terakhir
        return null;
    }
    // Memperbarui status pengaduan berdasarkan ID
    public boolean ubahStatus(String idPengaduan, String statusBaru) {
        Pengaduan pengaduan = cariPengaduan(idPengaduan);

        if (pengaduan != null) {
            pengaduan.setStatus(statusBaru);
            return true;
        }
        return false;
    }

    // Menghapus pengaduan dari list berdasarkan ID
    public boolean hapusPengaduan(String idPengaduan) {
        Pengaduan pengaduan = cariPengaduan(idPengaduan);

        if (pengaduan != null) {
            daftarPengaduan.remove(pengaduan);
            return true;
        }
        return false;
    }
}
