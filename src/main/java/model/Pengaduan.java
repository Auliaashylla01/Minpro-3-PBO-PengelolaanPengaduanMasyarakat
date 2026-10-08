/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Class ini digunakan untuk menyimpan atribut umum dari pengaduan masyarakat.
 * Class ini merupakan superclass dari pengaduanBiasa dan pengaduanDarurat.
 * Penerapan abstract sebagai dasar model pengaduan
 */
public abstract class Pengaduan {
    //Data private yang hanya bisa diakses secara langsung dari dalam class ini
    private String idPengaduan;
    private String namaPelapor;
    private String jenisPengaduan;
    private String isiPengaduan;
    private String tanggalPengaduan;
    private String status;
    
    //Constructor dipanggil saat objek Pengaduan baru dibuat. 
    //Digunakan untuk mengisi nilai awal dari atribut pengaduan.
    public Pengaduan(String idPengaduan, String namaPelapor,
                     String jenisPengaduan, String isiPengaduan,
                     String tanggalPengaduan) {

        this.idPengaduan = idPengaduan;
        this.namaPelapor = namaPelapor;
        this.jenisPengaduan = jenisPengaduan;
        this.isiPengaduan = isiPengaduan;
        this.tanggalPengaduan = tanggalPengaduan;
        //Agar status awal selalu "Menunggi Konfirmasi Petugas" , baik dalam pengaduan darurat ataupun biasa
        this.status = "Menunggu Konfirmasi Petugas";
    }
    //Penerapan Getter Pada Sistem digunakan untuk mengambil atau membaca nilai atribut
    public String getIdPengaduan() {
        return idPengaduan;
    }
    public String getNamaPelapor() {
        return namaPelapor;
    }
    public String getJenisPengaduan() {
        return jenisPengaduan;
    }
    public String getIsiPengaduan() {
        return isiPengaduan;
    }
    public String getTanggalPengaduan() {
        return tanggalPengaduan;
    }
    public String getStatus() {
        return status;
    }
    // Penerapan Setter Pada Sistem digunakan untuk mengubah nilai atribut status
    public void setStatus(String status) {
        this.status = status;
    }
    //Digunakan untuk penerapan tingkat urgensi, 
    //sebagai bentuk penerapan abstract dan tingkat urgensi setiap subclass
    public abstract String getTingkatUrgensi();
            
    //Method yang digunakan untuk menampilkan informasi pengaduan atau method dasar di superclass
    public String getDetailPengaduan(){
        return "ID Pengaduan : " + idPengaduan + "\n"+
                "Pelapor : " + namaPelapor + "\n" +
                "Jenis Pengaduan : " + jenisPengaduan + "\n" +
                "Tingkat Urgensi : " + getTingkatUrgensi() + "\n" +
                "Isi Pengaduan : " + isiPengaduan + "\n" +
                "Tanggal : " + tanggalPengaduan + "\n" +
                "Status : " + status;
    }
}

