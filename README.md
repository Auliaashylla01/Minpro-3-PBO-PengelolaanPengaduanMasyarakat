# Sistem Pengelolaan Pengaduan Masyarakat

**Oleh Aulia Ashylla Ananda Putri Hariawan (2509116076)**

## 1. Deskripsi Singkat Program

Sistem Pengelolaan Pengaduan Masyarakat merupakan program yang digunakan untuk mencatat dan mengelola data pengaduan yang disampaikan oleh masyarakat. Program ini dapat digunakan untuk menangani berbagai jenis laporan, seperti fasilitas umum, kebersihan, keamanan, jalan, dan pelayanan. Setiap data pengaduan memiliki informasi berupa ID Pengaduan, Nama Pelapor, Jenis Pengaduan, Isi Pengaduan, Tanggal Pengaduan, Tingkat Urgensi, dan Status Pengaduan. ID pengaduan dibuat secara otomatis oleh sistem dengan format `P001`, `P002`, dan seterusnya. Jenis pengaduan dipilih melalui kategori yang telah disediakan sehingga data yang dimasukkan lebih terstruktur.

Program membedakan pengaduan menjadi dua jenis berdasarkan tingkat urgensinya, yaitu **pengaduan biasa** dan **pengaduan darurat**. Pengaduan biasa memiliki target penyelesaian standar 7 hari, sedangkan pengaduan darurat memiliki target respons awal standar 24 jam dan menyimpan kontak darurat pelapor. Pengaduan darurat juga memiliki penanganan khusus berupa pengiriman notifikasi darurat kepada kontak yang diberikan pelapor.

Pengguna dapat menjalankan beberapa fitur utama melalui menu interaktif, yaitu Tambah Pengaduan, Lihat Pengaduan, Ubah Status Pengaduan, Hapus Pengaduan, dan Keluar. Pada fitur Lihat Pengaduan, pengguna dapat memilih lima cara untuk melihat data, yaitu melihat seluruh pengaduan, berdasarkan ID, berdasarkan status, berdasarkan jenis, atau berdasarkan jenis dan status. Data selama program berjalan disimpan menggunakan `ArrayList`.

---

## 2. Tujuan Program

Program ini dibuat sebagai penerapan konsep Pemrograman Berorientasi Objek melalui sebuah sistem pengelolaan pengaduan sederhana.

Tujuan program adalah:

* Mencatat data pengaduan masyarakat secara terstruktur.
* Mengelompokkan pengaduan berdasarkan jenis dan tingkat urgensi.
* Menampilkan data pengaduan yang tersimpan.
* Mencari data pengaduan berdasarkan beberapa kriteria.
* Mengubah status pengaduan berdasarkan tahapan penanganan.
* Menghapus data pengaduan berdasarkan ID.
* Menerapkan konsep OOP seperti encapsulation, inheritance, abstraction, polymorphism, dan interface.

---

## 3. Struktur Program

Program terdiri dari beberapa class yang dikelompokkan berdasarkan fungsinya.

| Package      | Class                           | Peran                                                                                                                                 |
| ------------ | ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `main`       | `PengelolaanPengaduanMasyarakt` | Menjadi *entry point* program dan memulai proses pengelolaan sistem melalui controller.                                               |
| `controller` | `PengelolaDataPengaduan`        | Mengelola data pengaduan dalam `ArrayList`, menjalankan proses tambah, lihat, cari, ubah status, hapus, dan menghasilkan ID otomatis. |
| `controller` | `ValidasiInput`                 | Menangani validasi berbagai input pengguna agar data sesuai dengan aturan yang ditentukan.                                            |
| `model`      | `Pengaduan`                     | Menjadi abstract superclass yang menyimpan atribut, method umum, serta method abstract untuk tingkat urgensi pengaduan.               |
| `model`      | `PengaduanBiasa`                | Subclass dari `Pengaduan` untuk data pengaduan dengan tingkat urgensi biasa dan target penyelesaian standar 7 hari.                   |
| `model`      | `PengaduanDarurat`              | Subclass dari `Pengaduan` untuk data pengaduan dengan tingkat urgensi darurat, kontak darurat, dan target respons awal 24 jam.        |
| `model`      | `PenangananKhusus`              | Interface yang menjadi kontrak bagi pengaduan yang membutuhkan penanganan khusus, seperti pengaduan darurat.                          |
| `view`       | `PengaduanView`                 | Mengatur tampilan program pada terminal, seperti menu, judul, pilihan, informasi, pesan sukses, dan pesan error.                      |

### Struktur package

```text
src/
└── main/
    └── java/
        ├── main/
        │   └── PengelolaanPengaduanMasyarakt.java
        │
        ├── controller/
        │   ├── PengelolaDataPengaduan.java
        │   └── ValidasiInput.java
        │
        ├── model/
        │   ├── Pengaduan.java
        │   ├── PengaduanBiasa.java
        │   ├── PengaduanDarurat.java
        │   └── PenangananKhusus.java
        │
        └── view/
            └── PengaduanView.java
```

---

## 4. Menu Program

Menu utama yang tersedia pada sistem adalah:

<img width="280" height="119" alt="image" src="https://github.com/user-attachments/assets/f8c819d5-7c75-4229-99bd-28b323aa2432" />

*Gambar 1: Tampilan menu utama Sistem Pengelolaan Pengaduan Masyarakat sebagai pusat navigasi seluruh fitur program.*

Menu tersebut digunakan sebagai pusat navigasi program. Setelah pengguna menyelesaikan suatu proses, program akan kembali ke menu utama selama pengguna belum memilih menu Keluar.

---

# 5. Alur Program

## 5.1 Alur Sistem

Secara umum, sistem dimulai dengan menyediakan data awal pada `ArrayList`, kemudian pengguna dapat memilih fitur yang tersedia melalui menu utama. Alur sistem tersebut menunjukkan bahwa setiap fitur bekerja melalui menu utama dan setelah proses selesai pengguna kembali ke menu. Program akan berhenti ketika pilihan menu bernilai `5`.

```text
                          ┌──────────────┐
                          │    MULAI     │
                          └──────┬───────┘
                                 │
                                 ▼
                     Inisialisasi Controller
                                 │
                                 ▼
                      Muat Dummy Data Awal
                                 │
           ┌─────────────────────┴─────────────────────┐
           │                                           │
           ▼                                           │
┌────────────────────┐                                 │
│   Tampilkan Menu   │◄────────────────────────────┐   │
└─────────┬──────────┘                             │   │
          │                                        │   │
          ▼                                        │   │
┌────────────────────┐                             │   │
│   Input Pilihan    │                             │   │
│        Menu        │                             │   │
└─────────┬──────────┘                             │   │
          │                                        │   │
          ▼                                        │   │
  ┌───────────────┐                                │   │
  │ Pilihan Menu? │                                │   │
  └───────┬───────┘                                │   │
          │                                        │   │
  ┌───────┼─────────────┬──────────────┬───────────┤   │
  │       │             │              │           │   │
  ▼       ▼             ▼              ▼           ▼   │
 [1]     [2]           [3]            [4]         [5]  │
Tambah  Lihat          Ubah          Hapus       Keluar│
  │       │             │              │           │   │
  ▼       ▼             ▼              ▼           ▼   │
Proses  Proses        Proses        Proses      Selesai│
  │       │             │              │               │
  └───────┴──────┬──────┴──────────────┘               │
                 │                                     │
                 ▼                                     │
          Kembali ke Menu ─────────────────────────────┘
```

---

## 5.2 Alur Tambah Pengaduan

Fitur **Tambah Pengaduan** digunakan untuk membuat data pengaduan baru.

Urutan prosesnya adalah:

```text
Pilih Menu Tambah Pengaduan
                         │
                         ▼
                Generate ID Otomatis
                         │
                         ▼
                Input Nama Pelapor
                         │
                         ▼
              Pilih Jenis Pengaduan
                         │
                         ▼
                Input Isi Pengaduan
                         │
                         ▼
              Input Tanggal Pengaduan
                         │
                         ▼
              Pilih Tingkat Urgensi?
                  ┌──────┴──────┐
                  │             │
                  ▼             ▼
                Biasa        Darurat
                  │             │
                  ▼             ▼
             Buat Object   Input Kontak
            PengaduanBiasa   Darurat
                  │             │
                  │             ▼
                  │        Buat Object
                  │      PengaduanDarurat
                  │             │
                  └──────┬──────┘
                         │
                         ▼
                Simpan ke ArrayList
                         │
                         ▼
                Tampilkan Berhasil
                         │
                         ▼
                  Kembali ke Menu
```

ID tidak dimasukkan secara manual oleh pengguna. Sistem menghasilkan ID berdasarkan ID yang belum digunakan, misalnya `P001`, `P002`, dan seterusnya.

Jenis pengaduan juga tidak dimasukkan sebagai teks bebas. Pengguna memilih salah satu dari lima kategori yang disediakan, yaitu:

```text
1. Fasilitas Umum
2. Kebersihan
3. Keamanan
4. Jalan
5. Pelayanan
```

Pada tahap berikutnya pengguna memilih tingkat urgensi:

```text
1. Biasa
2. Darurat
```

Pilihan tersebut menentukan object subclass yang dibuat. Pengaduan biasa akan menghasilkan object `PengaduanBiasa`, sedangkan pengaduan darurat akan menghasilkan object `PengaduanDarurat`.

Pada pengaduan darurat, pengguna juga diminta memasukkan kontak darurat. Setelah data berhasil ditambahkan, sistem akan memeriksa apakah object tersebut menerapkan interface `PenangananKhusus`. Jika memenuhi interface tersebut, sistem akan menjalankan method `kirimNotifikasiDarurat()` untuk memberikan notifikasi bahwa pengaduan membutuhkan respons khusus.

### Bukti output proses tambah pengaduan biasa

<img width="334" height="298" alt="image" src="https://github.com/user-attachments/assets/dfbd73e3-e3bb-42e5-a5b6-16b4828c6dbb" />

*Gambar 2: Proses penambahan pengaduan biasa, mulai dari ID otomatis, pengisian data pengaduan, pemilihan jenis dan urgensi, hingga data berhasil disimpan.*

### Bukti output proses tambah pengaduan darurat

<img width="350" height="380" alt="image" src="https://github.com/user-attachments/assets/f90ce3f7-ff67-4651-9faf-64b95233e9c9" />


*Gambar 3: Proses penambahan pengaduan darurat yang menghasilkan object `PengaduanDarurat`, meminta input kontak darurat pelapor, serta menjalankan notifikasi penanganan khusus.*

---

## 5.3 Alur Lihat Pengaduan

Fitur **Lihat Pengaduan** digunakan untuk menampilkan atau mencari data pengaduan yang tersimpan pada `ArrayList`.

<img width="267" height="135" alt="image" src="https://github.com/user-attachments/assets/d28321b4-959c-4002-b9d6-8d52f9a59487" />            

*Gambar 4: Tampilan fitur lima cara Lihat Pengaduan*          

Pada versi program ini, pengguna dapat memilih **lima cara** untuk melihat data pengaduan.            

<img width="464" height="283" alt="image" src="https://github.com/user-attachments/assets/05dbb5a0-5465-4e98-bd92-143d5d8dfaa0" />

*Gambar 5: Tampilan data pengaduan pada fitur Lihat Pengaduan, termasuk data dummy yang telah tersedia sejak program dijalankan.*

Data dummy telah dimasukkan sejak `PengelolaDataPengaduan` dibuat. Oleh karena itu, ketika program pertama kali menjalankan menu Lihat Pengaduan, data sudah langsung tersedia tanpa harus melakukan proses tambah terlebih dahulu.

```text
Pilih Menu Lihat Pengaduan
          ↓
Tampilkan 5 pilihan cara melihat data
          ↓
Pengguna memilih metode pencarian
          │
          ├── 1 → Tampilkan seluruh pengaduan
          │
          ├── 2 → Cari berdasarkan ID
          │
          ├── 3 → Cari berdasarkan Status
          │
          ├── 4 → Cari berdasarkan Jenis
          │
          └── 5 → Cari berdasarkan Jenis dan Status
                         ↓
                  Tampilkan hasil
                         ↓
                  Kembali ke Menu
```

Untuk pencarian berdasarkan ID, sistem menghasilkan satu object `Pengaduan` apabila data ditemukan. Sedangkan pencarian berdasarkan status, jenis, atau jenis dan status dapat menghasilkan lebih dari satu data sehingga hasilnya disimpan dalam `ArrayList<Pengaduan>`.

Pencarian berdasarkan jenis dan status menggunakan method `cariPengaduan()` dengan parameter yang berbeda dari pencarian berdasarkan ID. Penerapan tersebut menjadi salah satu bentuk **method overloading** pada program.

---

## 5.4 Alur Ubah Status Pengaduan

Fitur **Ubah Status Pengaduan** menggunakan alur status bertahap agar perubahan status tidak dapat dilakukan secara acak.

<img width="377" height="242" alt="image" src="https://github.com/user-attachments/assets/bac2cd7f-d218-4dde-9450-e6c6bf2493e8" />

*Gambar 6: Proses perubahan status pengaduan berdasarkan ID dengan konfirmasi pengguna sebelum status diperbarui.*

Urutan status adalah:

```text
Menunggu Konfirmasi Petugas
             ↓
       Sedang Diproses
             ↓
   Selesai Ditindaklanjuti
```

Pengguna memasukkan ID pengaduan yang akan diubah. Sistem kemudian mencari data berdasarkan ID tersebut. Setelah data ditemukan, sistem menentukan status berikutnya berdasarkan status saat ini.

Apabila pengaduan sudah berada pada status `Selesai Ditindaklanjuti`, status tidak dapat diubah lagi.

```text
Pilih Ubah Status
                      │
                      ▼
           ┌► Input ID Pengaduan ◄────────────────┐
           │          │                           │
           │          ▼                           │
           │ Cari Data berdasarkan ID             │
           │          │                           │
           │          ▼                           │
           │   Data ditemukan?                    │
           │     ┌────┴────┐                      │
           │   Tidak       Ya                     │
           │     │          │                     │
           │     ▼          ▼                     │
           └── Error  Cek Status Saat Ini         │
                           │                      │
                           ▼                      │
                Tentukan Status Berikutnya        │
                           │                      │
                           ▼                      │
                Konfirmasi perubahan?             │
                    ┌──────┴──────┐               │
                    │             │               │
                    ▼             ▼               │
                   'y'           'n'              │
                    │             │               │
                    ▼             ▼               │
                Ubah Status      Batal            │
                    │             │               │
                    └──────┬──────┘               │
                           │                      │
                           ▼                      │
                    Kembali ke Menu ─────────────┘
```

---

## 5.5 Alur Hapus Pengaduan

Fitur **Hapus Pengaduan** digunakan untuk menghapus data berdasarkan ID.

<img width="328" height="239" alt="image" src="https://github.com/user-attachments/assets/11717486-9d2c-4af5-b98a-645641ac31d8" />

*Gambar 7: Proses penghapusan data pengaduan melalui pencarian ID dan konfirmasi pengguna sebelum data dihapus.*

Sebelum data benar-benar dihapus, sistem menampilkan ringkasan data dan meminta konfirmasi. Hal tersebut mencegah penghapusan dilakukan secara langsung tanpa persetujuan pengguna.

```text
Pilih Hapus Pengaduan
                         │
                         ▼
           ┌► Input ID Pengaduan
           │             │
           │             ▼
           │       Cari Pengaduan
           │             │
           │             ▼
           │      Data ditemukan?
           │        ┌────┴────┐
           │      Tidak      Ya
           │        │         │
           │        ▼         ▼
           └─── Error    Tampilkan Ringkasan Data
                              │
                              ▼
                       Konfirmasi (y/n)?
                          ┌─────┴─────┐
                          │           │
                          ▼           ▼
                         'y'         'n'
                          │           │
                          ▼           ▼
                        Hapus       Batal
                          │           │
                          └─────┬─────┘
                                │
                                ▼
                         Kembali ke Menu
```

---

## 5.6 Alur Keluar Program

Ketika pengguna memilih menu `5`, program menampilkan pesan penutup dan mengakhiri perulangan menu.

<img width="380" height="170" alt="image" src="https://github.com/user-attachments/assets/e3a15630-7d23-4331-92f8-e794194d1c5a" />

*Gambar 8: Tampilan ketika pengguna memilih menu Keluar dan program mengakhiri proses.*

---

# 6. Penerapan Encapsulation dan Inheritance

## 6.1 Encapsulation

**Encapsulation** diterapkan dengan menyembunyikan data internal object melalui atribut `private` dan menyediakan method `getter` serta `setter` sesuai kebutuhan.

Pada class `Pengaduan`, atribut utama menggunakan `private`:

```java
private String idPengaduan;
private String namaPelapor;
private String jenisPengaduan;
private String isiPengaduan;
private String tanggalPengaduan;
private String status;
```

Atribut tersebut tidak dapat diakses secara langsung dari class lain. Akses terhadap data dilakukan melalui method yang telah disediakan.

Contoh penerapannya:

```java
public String getNamaPelapor() {
    return namaPelapor;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
```

<img width="526" height="143" alt="image" src="https://github.com/user-attachments/assets/c8f08e32-85c0-4df3-a6bc-b0eb24199b00" />

*Gambar 8: Penerapan encapsulation melalui method `getter` dan `setter` pada class `Pengaduan`.*

Atribut `idPengaduan` tidak memiliki `setter`. Hal tersebut dilakukan karena ID dibuat secara otomatis oleh sistem dan digunakan sebagai identitas pengaduan sehingga tidak diubah melalui setter.

Encapsulation juga diterapkan pada atribut tambahan di subclass, seperti `targetSelesaiHari` pada `PengaduanBiasa` serta `kontakDarurat` dan `batasResponJam` pada `PengaduanDarurat`.

---

## 6.2 Inheritance

**Inheritance** digunakan dengan membuat class `Pengaduan` sebagai superclass yang memiliki atribut dan method umum untuk seluruh jenis pengaduan.

Dua subclass mewarisi class tersebut:

```text
                    Pengaduan
                   (superclass)
                   /          \
                  /            \
                 ▼              ▼
       PengaduanBiasa     PengaduanDarurat
        (subclass)          (subclass)
```

Penerapan inheritance terlihat pada deklarasi:

```java
public class PengaduanBiasa extends Pengaduan
```

dan:

```java
public class PengaduanDarurat extends Pengaduan
```

<img width="420" height="50" alt="image" src="https://github.com/user-attachments/assets/7867d53a-aab1-4c7e-b240-38ac9667ed98" />

*Gambar 9: Penerapan inheritance pada class `PengaduanBiasa` melalui keyword `extends`.*

Pada constructor subclass digunakan `super()` untuk menginisialisasi atribut yang berasal dari superclass.

Contoh:

```java
super(idPengaduan, namaPelapor, jenisPengaduan,
      isiPengaduan, tanggalPengaduan);
```

<img width="556" height="115" alt="image" src="https://github.com/user-attachments/assets/cbf53c77-af77-4ed7-bdb3-b739e0842dae" />

*Gambar 10: Penggunaan `super()` pada constructor subclass untuk memanggil constructor superclass `Pengaduan`.*

Dengan inheritance, atribut dan perilaku umum tidak perlu ditulis kembali pada masing-masing subclass. Setiap subclass hanya menambahkan atribut dan perilaku yang sesuai dengan jenis pengaduannya.

---

# 7. Penerapan Polymorphism dan Abstraction

## 7.1 Polymorphism

Pada program ini, polymorphism yang digunakan dalam pengembangan fitur adalah **method overloading**. Method yang memiliki nama sama dapat digunakan untuk kebutuhan pencarian yang berbeda berdasarkan parameter yang diberikan.

Penerapannya terdapat pada class `PengelolaDataPengaduan`.

### Overloading 1 — Mencari berdasarkan ID

```java
public Pengaduan cariPengaduan(String idPengaduan)
```

Method tersebut digunakan untuk mencari satu pengaduan berdasarkan ID dan menghasilkan satu object `Pengaduan`.

### Overloading 2 — Mencari berdasarkan jenis dan status

```java
public ArrayList<Pengaduan> cariPengaduan(
        String jenisPengaduan, String status)
```

Method tersebut memiliki nama yang sama, tetapi parameter yang digunakan berbeda. Method ini digunakan untuk mencari beberapa pengaduan berdasarkan kombinasi jenis dan status.

Perbedaan parameter tersebut membuat Java dapat menentukan method `cariPengaduan()` yang akan digunakan sesuai dengan argumen yang diberikan.

Penerapan overloading digunakan pada fitur **Lihat Pengaduan**, khususnya ketika pengguna memilih pencarian berdasarkan ID atau berdasarkan jenis dan status.

---

## 7.2 Abstraction

**Abstraction** diterapkan dengan menjadikan class `Pengaduan` sebagai `abstract class`.

Penerapannya terlihat pada kode:

```java
public abstract class Pengaduan {
```

Class `Pengaduan` tidak dibuat sebagai object secara langsung karena hanya berisi struktur dan perilaku umum yang akan digunakan oleh subclass.

Selain itu, terdapat method abstract:

```java
public abstract String getTingkatUrgensi();
```

Method tersebut tidak memiliki implementasi di dalam superclass. Setiap subclass wajib memberikan implementasi sesuai dengan tingkat urgensinya.

Pada `PengaduanBiasa`:

```java
@Override
public String getTingkatUrgensi() {
    return "BIASA";
}
```

Sedangkan pada `PengaduanDarurat`:

```java
@Override
public String getTingkatUrgensi() {
    return "DARURAT";
}
```

Dengan abstraction, class `Pengaduan` hanya menentukan bahwa setiap jenis pengaduan harus memiliki informasi tingkat urgensi, sedangkan detail nilai urgensinya ditentukan oleh masing-masing subclass.

---

# 8. Penerapan Nilai Tambah

Nilai tambah yang diterapkan dalam program adalah **struktur MVC dan interface**.

## 8.1 Struktur MVC

Program menggunakan konsep **Model-View-Controller (MVC)** untuk memisahkan tanggung jawab setiap bagian program.

* **Model** berisi representasi data dan perilaku objek pengaduan.
* **View** menangani tampilan yang ditampilkan kepada pengguna pada terminal.
* **Controller** menangani pengelolaan data dan proses CRUD.
* **Main** menjadi titik awal program dan menghubungkan proses awal dengan controller.
* **ValidasiInput** dipisahkan dalam package `controller` untuk menangani validasi input pengguna.

Pembagian tersebut dapat dilihat pada struktur package:

```text
Model
 ├── Pengaduan
 ├── PengaduanBiasa
 ├── PengaduanDarurat
 └── PenangananKhusus

View
 └── PengaduanView

Controller
 ├── PengelolaDataPengaduan
 └── ValidasiInput

Main
 └── PengelolaanPengaduanMasyarakt
```

Pemisahan ini digunakan untuk membedakan pengelolaan data, tampilan, validasi input, dan proses utama sistem sehingga struktur program lebih terorganisir.

---

## 8.2 Interface `PenangananKhusus`

Nilai tambah lainnya adalah penggunaan **interface `PenangananKhusus`** untuk memberikan kontrak khusus pada pengaduan yang membutuhkan penanganan darurat.

Interface terdapat pada:

```java
public interface PenangananKhusus {

    int getTargetWaktuResponJam();

    void kirimNotifikasiDarurat();
}
```

Interface tersebut kemudian diimplementasikan oleh class `PengaduanDarurat`:

```java
public class PengaduanDarurat
        extends Pengaduan
        implements PenangananKhusus
```

`PengaduanDarurat` wajib mengimplementasikan method yang terdapat pada interface, yaitu:

```java
@Override
public int getTargetWaktuResponJam() {
    return batasResponJam;
}
```

dan:

```java
@Override
public void kirimNotifikasiDarurat() {
    System.out.println(
        "\n[NOTIFIKASI URGENT] Mengirim pesan darurat ke: "
        + kontakDarurat
    );
    System.out.println(
        "Target respons awal petugas: "
        + getTargetWaktuResponJam() + " jam"
    );
}
```

Interface digunakan karena tidak semua pengaduan membutuhkan penanganan khusus. Pada sistem ini, `PengaduanDarurat` memiliki kebutuhan tambahan berupa kontak darurat, target respons awal, dan notifikasi urgent.

Saat pengaduan darurat berhasil dibuat, sistem memeriksa apakah object tersebut menerapkan interface `PenangananKhusus` menggunakan `instanceof`.

```java
if (pengaduanBaru instanceof PenangananKhusus) {
    ((PenangananKhusus) pengaduanBaru)
            .kirimNotifikasiDarurat();
}
```

Dengan penerapan tersebut, sistem dapat memberikan perlakuan khusus kepada pengaduan darurat tanpa membuat seluruh jenis pengaduan memiliki fitur notifikasi darurat.

---

# 9. Ringkasan Konsep OOP yang Diterapkan

| Konsep            | Penerapan dalam Program                                                                                |
| ----------------- | ------------------------------------------------------------------------------------------------------ |
| **Encapsulation** | Atribut pada class menggunakan `private` dan diakses melalui getter/setter.                            |
| **Inheritance**   | `PengaduanBiasa` dan `PengaduanDarurat` mewarisi `Pengaduan` menggunakan `extends`.                    |
| **Polymorphism**  | Method overloading pada `cariPengaduan()` dengan parameter yang berbeda.                               |
| **Abstraction**   | `Pengaduan` dibuat sebagai `abstract class` dan memiliki abstract method `getTingkatUrgensi()`.        |
| **Interface**     | `PenangananKhusus` diterapkan oleh `PengaduanDarurat` untuk kebutuhan penanganan khusus.               |
| **MVC**           | Program memisahkan bagian Model, View, Controller, dan Main.                                           |
| **Validation**    | `ValidasiInput` digunakan untuk memvalidasi input menu, nama, tanggal, ID, status, dan kontak darurat. |

Program dengan demikian tidak hanya digunakan untuk menjalankan proses CRUD pengaduan, tetapi juga menerapkan beberapa konsep Pemrograman Berorientasi Objek melalui struktur class, pewarisan, abstraction, overloading, interface, serta pemisahan tanggung jawab menggunakan MVC.
