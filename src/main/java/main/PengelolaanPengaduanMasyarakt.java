/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import controller.PengelolaDataPengaduan;
import view.PengaduanView;
/**
 * Kelas Utama (Main Class)
 * Berfungsi sebagai titik awal (entry point) untuk menjalankan alur program 
 * sistem Pengelolaan Pengaduan Masyarakat.
 */

public class PengelolaanPengaduanMasyarakt {

    public static void main(String[] args) {
        //Membuat objek View untuk menangani tampilan sistem
       PengaduanView view = new PengaduanView();
       //Membuat objek Controller dan menghubungkannya dengan View
        PengelolaDataPengaduan controller = new PengelolaDataPengaduan(view);
        //Memanggil method mulai() pada Controller untuk menjalankan alur menu utama
        controller.mulai();
    }
}