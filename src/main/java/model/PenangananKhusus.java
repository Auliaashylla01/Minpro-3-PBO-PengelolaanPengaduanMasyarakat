/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * Interface (kontrak) untuk pengaduan yang membutuhkan penanganan khusus.
 * Class yang memakai interface ini WAJIB mengisi semua method di bawah.
 */
public interface PenangananKhusus {
    //Mengambil target waktu respons awal (dalam jam)
    int getTargetWaktuResponJam();

    //Digunakan untuk pengiriman notifikasi peringatan darurat
    void kirimNotifikasiDarurat();
}
 
