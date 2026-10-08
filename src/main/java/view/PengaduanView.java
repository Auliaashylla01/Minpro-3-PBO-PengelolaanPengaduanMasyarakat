/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import model.Pengaduan;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
//Class PengaduanView adalah View dalam arsitektur MVC.
//Digunakan untuk tampilan di terminal, seperti mencetak menu, header serta daftar data pengaduan.
public class PengaduanView {
    
    //Menampilkan header utama sistem di bagian paling atas menu.
    public void tampilkanHeader() {
        System.out.println("\n==================================================");
        System.out.println("      SISTEM PENGELOLAAN PENGADUAN MASYARAKAT");
        System.out.println("==================================================");
    }
    
    //Menampilkan pilihan menu utama yang dapat dipilih oleh pengguna.
    public void tampilkanMenu() {
        System.out.println("1. Tambah Pengaduan");
        System.out.println("2. Lihat Pengaduan");
        System.out.println("3. Ubah Status Pengaduan");
        System.out.println("4. Hapus Pengaduan");
        System.out.println("5. Keluar");
        System.out.println("==================================================");
    }

    //Menampilkan judul sub-menu atau halaman di menu
    public void tampilkanJudul(String judul) {
        System.out.println("\n==================================================");
        System.out.println("              " + judul);
        System.out.println("==================================================");
    }
    
    //Menampilkan ID Pengaduan yang baru saja dibuat 
    public void tampilkanIdOtomatis(String id) {
        System.out.println("ID Pengaduan : " + id);
    }

    //Menampilkan daftar pilihan jenis pengaduan.
    public void tampilkanPilihanJenisPengaduan() {
        System.out.println("\nJenis Pengaduan:");
        System.out.println("1. Fasilitas Umum");
        System.out.println("2. Kebersihan");
        System.out.println("3. Keamanan");
        System.out.println("4. Jalan");
        System.out.println("5. Pelayanan");
    }

    //Menampilkan pilihan tingkat urgensi pengaduan 
    public void tampilkanPilihanTingkatUrgensi() {
        System.out.println("\nPilih Tingkat Urgensi:");
        System.out.println("1. Biasa   (target penyelesaian standar 7 hari)");
        System.out.println("2. Darurat (target respons awal standar 24 jam)");
    }
    
    //Menampilkan pilihan status pengaduan
    public void tampilkanPilihanStatus() {
        System.out.println("\nStatus Pengaduan:");
        System.out.println("1. Menunggu Konfirmasi Petugas");
        System.out.println("2. Sedang Diproses");
        System.out.println("3. Selesai Ditindaklanjuti");
    }

    //Menampilkan pilihan cara melihat data pengaduan
    public void tampilkanMenuLihat() {
        System.out.println("1. Lihat semua pengaduan");
        System.out.println("2. Lihat berdasarkan ID");
        System.out.println("3. Lihat berdasarkan Status");
        System.out.println("4. Lihat berdasarkan Jenis");
        System.out.println("5. Lihat berdasarkan Jenis dan Status");
    }
    
    //Menampilkan pesan biasa yang ingin ditampilkan
    public void tampilkanPesan(String pesan) { 
        System.out.println(pesan);
    } 
    
    //Menampilkan pesan keterangan sukses 
    public void tampilkanSukses(String pesan) { 
        System.out.println("\n[Sukses] " + pesan);
    } 
    
    //Menampilkan keteranagan error sesuai dengan kondisi
    public void tampilkanError(String pesan) { 
        System.out.println("\n[Error] " + pesan);
    } 
    
    //Menampilkan informasi umum 
    public void tampilkanInfo(String pesan) { 
        System.out.println("\n[Informasi] " + pesan);
    } 
    
    // Menampilkan detail satu pengaduan
    public void tampilkanDetailPengaduan(Pengaduan p) {
        // Polymorphism: method yang dijalankan menyesuaikan objek aslinya
        System.out.println(p.getDetailPengaduan());
        System.out.println("--------------------------------------------------");
    }
    
    //Menampilkan seluruh data pengaduan yang ada dalam daftar.
    public void tampilkanDaftarPengaduan(ArrayList<Pengaduan> daftar) {
        //Digunakan untuk mengecek apakah ada data pengaduan nya 
        if (daftar.isEmpty()) {
            tampilkanInfo("Belum ada data pengaduan yang tersimpan.");
            return;
        }
        System.out.println("\n==================================================");
        System.out.println("               DATA PENGADUAN");
        System.out.println("==================================================");

        for (Pengaduan p : daftar) {
            System.out.println(p.getDetailPengaduan());
            System.out.println("--------------------------------------------------");
        }
    }
    
    ////Menampilkan ringkasan data sebelum melakukan penghapusan data.
    public void tampilkanKonfirmasiHapus(Pengaduan p) {
        System.out.println("\nData ditemukan:");
        System.out.println("ID      : " + p.getIdPengaduan());
        System.out.println("Pelapor : " + p.getNamaPelapor());
        System.out.println("Jenis   : " + p.getJenisPengaduan());
        System.out.println("Tingkat : " + p.getTingkatUrgensi());
        System.out.println("Status  : " + p.getStatus());
        System.out.print("\nApakah Kamu yakin ingin menghapus pengaduan ini? (y/n): ");
    }
    
    //Menampilkan data status pengaduan saat ini serta opsi status tahap berikutnya.
     public void tampilkanStatusUbah(Pengaduan p, String statusBerikutnya) {
        System.out.println("\n[Informasi Data]");
        System.out.println("Pelapor         : " + p.getNamaPelapor());
        System.out.println("Tingkat Urgensi : " + p.getTingkatUrgensi());
        System.out.println("Status saat ini : " + p.getStatus());

        // Pengecekan jika status sudah di alur paling akhir
        if (statusBerikutnya == null) {
            tampilkanInfo("Pengaduan ini sudah \"Selesai Ditindaklanjuti\", status tidak dapat diubah lagi.");
        } else {
            System.out.println("\nStatus berikutnya yang tersedia:");
            System.out.println("1. " + statusBerikutnya);
            System.out.print("\nLanjutkan mengubah status ke tahap tersebut? (y/n): ");
        }
    }
     
     //Digunakan untuk menunggu pengguna menekan tombol ENTER sebelum kembali ke menu utama.
     public void tampilkanPesanLanjut() {
        System.out.println("\nTekan [ENTER] untuk kembali ke menu...");
    }

     //Menampilkan pesan penutup saat pengguna memilih menu keluar dari aplikasi.
    public void tampilkanSalam() {
        System.out.println("\nTerima kasih telah menggunakan Sistem Pengelolaan Pengaduan Masyarakat.");
    }
}
    

