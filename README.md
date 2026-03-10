# 📊 Sistem Penilaian Mahasiswa - Kotlin

Sistem penilaian mahasiswa sederhana yang dibangun menggunakan bahasa pemrograman **Kotlin**. Program ini menghitung nilai akhir berdasarkan bobot UTS, UAS, dan Tugas, kemudian menentukan grade dan status kelulusan mahasiswa.

---

## 📋 Deskripsi

Program ini memungkinkan pengguna untuk:
- ✅ Memasukkan nama mahasiswa
- ✅ Memasukkan nilai UTS, UAS, dan Tugas dengan validasi input (0-100)
- ✅ Menghitung nilai akhir dengan bobot yang telah ditentukan
- ✅ Mengkonversi nilai akhir ke dalam bentuk huruf (Grade A-E)
- ✅ Menentukan status kelulusan mahasiswa

---

## ⚙️ Bobot Penilaian

| Komponen | Bobot |
|----------|-------|
| UTS      | 30%   |
| UAS      | 40%   |
| Tugas    | 30%   |

### 🧮 Rumus Perhitungan
Nilai Akhir = (UTS × 0.3) + (UAS × 0.4) + (Tugas × 0.3)

---

## 📈 Konversi Grade

| Range Nilai | Grade | Keterangan      |
|-------------|-------|-----------------|
| 85 - 100    | A     | Sangat Baik     |
| 70 - 84     | B     | Baik            |
| 60 - 69     | C     | Cukup           |
| 50 - 59     | D     | Kurang          |
| 0 - 49      | E     | Sangat Kurang   |

### 🎓 Status Kelulusan

| Kondisi              | Status      |
|----------------------|-------------|
| Nilai Akhir ≥ 60     | LULUS       |
| Nilai Akhir < 60     | TIDAK LULUS |

---

## 🚀 Cara Menjalankan

### 📦 Prasyarat

- [Kotlin](https://kotlinlang.org/) terinstall di sistem Anda
- Atau gunakan [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### 📝 Langkah-langkah

#### 1. Clone Repository

```bash
git clone <repository-url>
cd <folder-proyek>
```

#### 2. Compile File Kotlin
kotlinc Tugas3_Penilaian.kt -include-runtime -d Tugas3_Penilaian.jar

#### 3. Jalankan Program
java -jar Tugas3_Penilaian.jar

#### 4. 💻 Contoh Output
#### Skenario 1. Mahasiswa Lulus
```
===== SISTEM PENILAIAN =====

Masukkan Nama Mahasiswa: Budi Santoso
Masukkan Nilai UTS (0-100): 75
Masukkan Nilai UAS (0-100): 80
Masukkan Nilai Tugas (0-100): 85

===== HASIL PENILAIAN =====
Nama             : Budi Santoso
Nilai UTS        : 75 (Bobot 30%)
Nilai UAS        : 80 (Bobot 40%)
Nilai Tugas      : 85 (Bobot 30%)
-----------------------------------
Nilai Akhir      : 80.0
Grade            : B
Keterangan       : Baik
Status           : LULUS
Selamat! Anda dinyatakan LULUS.
```
#### Skenario 2. Mahasiswa Tidak Lulus
```
===== SISTEM PENILAIAN =====

Masukkan Nama Mahasiswa: Ani Wijaya
Masukkan Nilai UTS (0-100): 50
Masukkan Nilai UAS (0-100): 55
Masukkan Nilai Tugas (0-100): 45

===== HASIL PENILAIAN =====
Nama             : Ani Wijaya
Nilai UTS        : 50 (Bobot 30%)
Nilai UAS        : 55 (Bobot 40%)
Nilai Tugas      : 45 (Bobot 30%)
-----------------------------------
Nilai Akhir      : 50.5
Grade            : E
Keterangan       : Sangat Kurang
Status           : TIDAK LULUS

Maaf, Anda dinyatakan TIDAK LULUS. Jangan menyerah!
```
#### 5.  Alur Kode - Sistem Penilaian Mahasiswa Kotlin
```
┌─────────────────────────────────────────────────────────────────────────┐
│                                 MULAI                                   │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       TAMPILKAN HEADER                                  │
│                    "===== SISTEM PENILAIAN ====="                       │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       INPUT NAMA MAHASISWA                              │
│                    val nama = readLine()?.ifEmpty { null } ?: "..."     │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       INPUT NILAI UTS                                   │
│                    uts = mintaNilai("UTS")                              │
│                    ┌─────────────────────────────────┐                  │
│                    │ while(true) {                   │                  │
│                    │   input = readLine()            │                  │
│                    │   if valid (0-100) → return     │                  │
│                    │   else → error + loop           │                  │
│                    │ }                               │                  │
│                    └─────────────────────────────────┘                  │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       INPUT NILAI UAS                                   │
│                    uas = mintaNilai("UAS")                              │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       INPUT NILAI TUGAS                                 │
│                    tugas = mintaNilai("TUGAS")                          │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       HITUNG NILAI AKHIR                                │
│                    nilaiAkhir = (uts×0.3)+(uas×0.4)+(tugas×0.3)         │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       KONVERSI KE GRADE                                 │
│                    when {                                               │
│                      85-100 → A, 70-84 → B, ...                        │
│                    }                                                    │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       TENTUKAN KETERANGAN                               │
│                    when (grade) { A→Sangat Baik, B→Baik, ... }          │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       TENTUKAN STATUS                                   │
│                    if (nilaiAkhir >= 60) → LULUS                        │
│                    else → TIDAK LULUS                                   │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       TAMPILKAN HASIL                                   │
│                    Nama, Nilai, Grade, Keterangan, Status               │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                       TAMPILKAN PESAN AKHIR                             │
│                    if LULUS → "Selamat!"                                │
│                    else → "Jangan menyerah!"                            │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                                 SELESAI                                 │
└─────────────────────────────────────────────────────────────────────────┘
```
#### 6. Alur Input Data
```bash
print("Masukkan Nama Mahasiswa: ")
val nama = readLine()?.ifEmpty { null } ?: "Tanpa Nama"
```
####  flowchart
```
┌─────────────────┐
│   readLine()    │
└─────────────────┘
        │
        ▼
┌─────────────────┐
│  ifEmpty(null)  │
└─────────────────┘
        │
   ┌────┴────┐
   │         │
  Ada      Kosong
   │         │
   ▼         ▼
"Nama"   "Tanpa Nama"
```
#### Input Nilai (UTS, UAS, Tugas)
```bash
fun mintaNilai(namaKomponen: String): Double {
    while (true) {
        print("Masukkan Nilai $namaKomponen (0-100): ")
        val input = readLine()?.toDoubleOrNull()
        if (input != null && input in 0.0..100.0) {
            return input
        } else {
            println("input tidak valid! Angka harus antara 0 sampai 100.")
        }
    }
}
```
####
                    ┌───────────────┐
                    │  while(true)  │
                    │   (Loop)      │
                    └───────────────┘
                           │
                           ▼
                    ┌───────────────┐
                    │  readLine()   │
                    │  input user   │
                    └───────────────┘
                           │
                           ▼
                    ┌───────────────┐
                    │ toDoubleOrNull│
                    └───────────────┘
                           │
                    ┌──────┴──────┐
                    │             │
                   Angka       Bukan
                    │             │
                    ▼             ▼
              ┌──────────┐  ┌──────────┐
              │ 0-100?   │  │  Error   │
              └──────────  └──────────┘
                   │
            ┌──────┴──────┐
            │             │
           Valid       Invalid
            │             │
            ▼             │
      ┌──────────┐        │
      │  return  │        │
      │  input   │        │
      └──────────        │
            │             │
            └──────┬──────┘
                   │
                   ▼
            (Loop Ulang)

#### When Expression
```bash
val grade = when {
    nilaiAkhir in 85.0..100.0 -> "A"
    nilaiAkhir in 70.0..84.9 -> "B"
    nilaiAkhir in 60.0..69.9 -> "C"
    nilaiAkhir in 50.0..59.9 -> "D"
    else -> "E"
}
```
#### Decision Tree:

                         ┌──────────────┐
                         │ nilaiAkhir   │
                         │    80.0      │
                         └──────────────┘
                                │
                ┌───────────────┼───────────────┐
                │               │               │
                ▼               ▼               ▼
         ┌────────────┐  ┌────────────┐  ┌────────────┐
         │ 85-100?    │  │ 70-84.9?   │  │ 60-69.9?   │
         │    (A)     │  │    (B)     │  │    (C)     │
         └────────────┘  └────────────  └────────────┘
               │               │               │
              ❌ NO           ✅ YES          SKIP
               │               │
               │               ▼
               │        grade = "B"
               │
               ▼
         (Skip ke bawah)


# 📊 Sistem Manajemen Nilai Mahasiswa - Kotlin Collections

Sistem pengelolaan data nilai mahasiswa yang dibangun menggunakan **Kotlin Collections**. Program ini mengimplementasikan berbagai operasi collection seperti `filter`, `map`, `sortedBy`, `groupBy`, dan lainnya untuk mengelola data mahasiswa secara efisien.

---

## 📋 Deskripsi

Program ini memungkinkan pengguna untuk:
- ✅ Menampilkan semua data mahasiswa
- ✅ Menghitung statistik nilai (rata-rata, tertinggi, terendah)
- ✅ Filter mahasiswa lulus dan tidak lulus
- ✅ Mengurutkan nilai (ascending/descending)
- ✅ Mengelompokkan mahasiswa berdasarkan grade
- ✅ Menghitung jumlah mahasiswa per grade
- ✅ Mencari mahasiswa berdasarkan nama

---

## ⚙️ Spesifikasi Program

### Data Class

| Field | Tipe Data | Keterangan |
|-------|-----------|------------|
| `nim` | String | Nomor Induk Mahasiswa |
| `nama` | String | Nama lengkap mahasiswa |
| `mataKuliah` | String | Nama mata kuliah |
| `nilai` | Double | Nilai akhir (0-100) |

### Konversi Grade

| Range Nilai | Grade | Keterangan |
|-------------|-------|------------|
| 85 - 100 | A | Sangat Baik |
| 70 - 84 | B | Baik |
| 60 - 69 | C | Cukup |
| 50 - 59 | D | Kurang |
| 0 - 49 | E | Sangat Kurang |

### Status Kelulusan

| Kondisi | Status |
|---------|--------|
| Nilai ≥ 70 | LULUS |
| Nilai < 70 | TIDAK LULUS |

---

## 🚀 Cara Menjalankan

### Prasyarat

- [Kotlin](https://kotlinlang.org/) versi 1.6+
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (direkomendasikan)
- Atau [JDK](https://openjdk.org/) untuk compile manual

### Langkah-langkah

#### 1. Clone Repository

```bash
git clone <repository-url>
cd <folder-proyek>
```
#### 2. 💻 Contoh Output
#### Data Nilai Mahasiswa
```
===== DATA NILAI MAHASISWA =====
No  NIM       Nama              MataKuliah       Nilai
1   2024001   Budi Santoso      Pemrograman      85
2   2024002   Ani Wijaya        Pemrograman      92
3   2024003   Citra Dewi        Pemrograman      68
4   2024004   Jesica Jung       Pemrograman      45
5   2004005   Bentari Agung     Pemrograman      78
6   2024006   Michael Haryono   Pemrograman      88
7   2004007   Rusli Rushle      Pemrograman      72
8   2024008   Rosalia Muhammad  Pemrograman      55
9   2024009   Haric Wang        Pemrograman      90
10  2024010   Ryan lee          Pemrograman      63
```
#### Statistik
```
===== STATISTIK =====
Total Mahasiswa : 10
Rata-rata Nilai : 73.6
Nilai Tertinggi : 92 (Ani Wijaya)
Nilai Terendah  : 45 (Jesica Jung)
```
#### Mahasiswa Lulus
```
===== MAHASISWA LULUS =====
1. Budi Santoso - 85 (A)
2. Ani Wijaya - 92 (A)
3. Bentari Agung - 78 (B)
4. Michael Haryono - 88 (A)
5. Rusli Rushle - 72 (B)
6. Haric Wang - 90 (A)
```
#### Mahasiswa Lulus
```
===== MAHASISWA LULUS =====
1. Budi Santoso - 85 (A)
2. Ani Wijaya - 92 (A)
3. Bentari Agung - 78 (B)
4. Michael Haryono - 88 (A)
5. Rusli Rushle - 72 (B)
6. Haric Wang - 90 (A)
```
#### Urutan Nilai (Descending)
```
===== URUTAN NILAI (TERTINGGI - TERENDAH) =====
1. Ani Wijaya - 92
2. Haric Wang - 90
3. Michael Haryono - 88
4. Budi Santoso - 85
5. Bentari Agung - 78
6. Rusli Rushle - 72
7. Citra Dewi - 68
8. Ryan lee - 63
9. Rosalia Muhammad - 55
10. Jesica Jung - 45
```
#### Kelompok Berdasarkan Grade
```
===== KELOMPOK BERDASARKAN GRADE =====

Grade A:
  - Budi Santoso (85)
  - Ani Wijaya (92)
  - Michael Haryono (88)
  - Haric Wang (90)

Grade B:
  - Bentari Agung (78)
  - Rusli Rushle (72)

Grade C:
  - Citra Dewi (68)
  - Ryan lee (63)

Grade D:
  - Rosalia Muhammad (55)

Grade E:
  - Jesica Jung (45)
```
#### Jumlah Per Grade
```
===== JUMLAH PER GRADE =====
Grade A: 4 mahasiswa
Grade B: 2 mahasiswa
Grade C: 2 mahasiswa
Grade D: 1 mahasiswa
Grade E: 1 mahasiswa
```
#### Pencarian Mahasiswa
```
===== PENCARIAN MAHASISWA =====
Mencari nama mengandung: 'a'
- Budi Santoso (2024001)
- Ani Wijaya (2024002)
- Bentari Agung (2004005)
- Michael Haryono (2024006)
- Rosalia Muhammad (2024008)
```
#### Fungsi & Operasi Collection
#### Lambda Function (getGrade)
```
val getGrade: (Double) -> String = { nilai ->
    when {
        nilai >= 85 -> "A"
        nilai >= 70 -> "B"
        nilai >= 60 -> "C"
        nilai >= 50 -> "D"
        else -> "E"
    }
}
```
#### Operation Collection Summary
Operation              | Kode                              | Fungsi
-----------------------|-----------------------------------|---------------------------
map                    | map { it.nilai }                  | Mengambil nilai dari list
average                | .average()                        | Hitung rata-rata
maxByOrNull            | maxByOrNull { it.nilai }          | Cari nilai tertinggi
minByOrNull            | minByOrNull { it.nilai }          | Cari nilai terendah
filter                 | filter { it.nilai >= 70 }         | Filter berdasarkan kondisi
sortedByDescending     | sortedByDescending { it.nilai }   | Urutkan descending
groupBy                | groupBy { getGrade(it.nilai) }    | Kelompokkan by grade
forEachIndexed         | forEachIndexed { index, mhs -> }  | Loop dengan index
isNotEmpty             | lulus.isNotEmpty()                | Cek list tidak kosong

#### Flowchart
```
┌─────────────────────────────────────────────────────────────┐
│                            MULAI                            │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                 Buat List Mahasiswa (10 data)               │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                 Tampilkan Semua Data Mahasiswa              │
│                    (forEachIndexed)                         │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                       Hitung Statistik                      │
│         (size, average, maxByOrNull, minByOrNull)           │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│               Filter Mahasiswa Lulus (≥70)                  │
│                      (filter)                               │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│              Urutkan Nilai (Tertinggi - Terendah)           │
│                  (sortedByDescending)                       │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│              Kelompokkan Berdasarkan Grade (A-E)            │
│                      (groupBy)                              │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                 Hitung Jumlah Per Grade                     │
│                   (groupBy + size)                          │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                 Cari Mahasiswa Berdasarkan Nama             │
│                  (filter + contains)                        │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                            SELESAI                          │
└─────────────────────────────────────────────────────────────┘
```



# 🏪 DARENDLE Fantasy Emporium - Kotlin E-Commerce System

Sistem toko online bertema fantasi yang dibangun menggunakan **Kotlin** dengan implementasi konsep OOP modern: **Sealed Class**, **Data Class**, **Interface**, **Higher-Order Function**, **Collections Operations**, dan **Null Safety**.
---

# DARENDLE Fantasy Emporium** adalah aplikasi konsol interaktif untuk simulasi toko online dengan tema fantasi. Program ini memungkinkan pengguna untuk:

- 🛍️ Menjelajahi katalog produk fantasi (tongkat sihir, ramuan, jimat, dll.)
- 🛒 Menambah/menghapus produk ke keranjang belanja
- 💰 Menghitung total belanja dengan sistem diskon
- 💳 Melakukan checkout dengan berbagai metode pembayaran
- 🔢 Mendapatkan Virtual Account untuk pembayaran transfer
- 📦 Melacak status pesanan (Pending → Processing → Shipped → Delivered)
- 📊 Melihat statistik toko dan riwayat pesanan

---

## ✨ Fitur Utama

| Fitur | Deskripsi | Implementasi Kotlin |
|-------|-----------|-------------------|
| 🎭 **Sealed Class** | Status order & metode pembayaran yang type-safe | `OrderStatus`, `PaymentMethod` |
| 📦 **Data Class** | Model data otomatis dengan `equals()`, `hashCode()`, `toString()` | `Produk`, `CartItem`, `Customer`, `Order` |
| 🔌 **Interface** | Kontrak diskon yang dapat diimplementasikan produk | `Discountable` |
| 🧮 **Higher-Order Function** | Kalkulasi diskon fleksibel dengan lambda | `hitungTotalDenganDiskon()` |
| 📚 **Collections Operations** | Filter, map, groupBy, sortedBy untuk manipulasi data | `filter`, `map`, `groupBy`, `sortedByDescending` |
| 🔒 **Null Safety** | Penanganan nilai null yang aman | `String?`, `?.`, `?:`, `ifEmpty` |
| 🎲 **Random Generator** | Generate nomor Virtual Account acak | `kotlin.random.Random` |
| 🕐 **Date Handling** | Timestamp otomatis untuk order | `java.time.LocalDateTime` |
| 🎮 **Interactive Menu** | Menu-driven CLI dengan input user | `while(true) + when + readLine()` |

---

#### Menu Utama
```
======================================================================
TOKO FANTASI DARENDLE
======================================================================
1. Lihat Katalog Produk
2. Lihat Detail Produk
3. Tambah Produk ke Keranjang
4. Lihat Keranjang
5. Hapus Produk dari Keranjang
6. Checkout
7. Update Status Pesanan
8. Lihat Riwayat Pesanan
9. Statistik Toko
0. Keluar

Pilih menu (0-9): 
```
#### Katalog Produk
```
KATALOG PRODUK - DARENDLE Fantasy Emporium
====================================================================================================
No  ID       Nama                         Kategori        Harga        Stok    
----------------------------------------------------------------------------------------------------
1   F001     Tongkat Sihir Elderwood      Senjata         Rp 15000000   5       
2   F002     Ramuan Pemulihan Jiwa        Ramuan          Rp 250000     20      
3   F003     Grimoire Sihir Kuno          Buku Sihir      Rp 750000     15      
...```
#### Proses Checkout dengan Transfer
```
Metode Pembayaran:
1. Tunai
2. Transfer Bank
3. E-Wallet
Pilih (1-3): 2

==================================================
INFORMASI PEMBAYARAN TRANSFER
==================================================
Virtual Account Number: 01238765432109
Bank: BCA / Mandiri / BNI / BRI (Random)
Nominal Transfer: Rp 15750000
Batas Waktu: 24 jam
==================================================
Silakan transfer ke nomor VA di atas.
Setelah transfer, status order akan menjadi Processing.

CHECKOUT BERHASIL!
Order ID: ORD-00000001
Total: Rp 15750000
Virtual Account: 01238765432109
```
#### Statistik Toko
```
STATISTIK TOKO
======================================================================
Total Produk: 20
Total Order: 3

Produk per Kategori:
  - Senjata: 3 produk
  - Ramuan: 4 produk
  - Buku Sihir: 4 produk
  - Jimat: 5 produk
  - Armor: 3 produk
  - Aksesoris: 1 produk

Order per Status:
  - Pending: 1 order
  - Processing: 1 order
  - Shipped: 1 order

Rata-rata Nilai Order: Rp 12500000
```
#### KElas Diagram
```
┌─────────────────────────────────────────────────────┐
│                  <<sealed>>                          │
│                 OrderStatus                          │
├─────────────────────────────────────────────────────┤
│  + Pending : OrderStatus                             │
│  + Processing : OrderStatus                          │
│  + Shipped : OrderStatus                             │
│  + Delivered : OrderStatus                           │
│  + Cancelled : OrderStatus                           │
│  + toString() : String                               │
└─────────────────────────────────────────────────────┘
                          ▲
                          │
┌─────────────────────────────────────────────────────┐
│                      Order                           │
├─────────────────────────────────────────────────────┤
│  - id : String                                       │
│  - customer : Customer                               │
│  - items : List<CartItem>                            │
│  - status : OrderStatus  ◄────────────────────┐      │
│  - paymentMethod : PaymentMethod               │      │
│  - totalHarga : Double                         │      │
│  - tanggal : String                            │      │
│  - virtualAccount : String?                    │      │
└─────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────┐
│                  <<sealed>>                          │
│                PaymentMethod                         │
├─────────────────────────────────────────────────────┤
│  + Cash : PaymentMethod                              │
│  + Transfer : PaymentMethod                          │
│  + EWallet : PaymentMethod                           │
│  + toString() : String                               │
└─────────────────────────────────────────────────────┘
                          ▲
                          │
                          │ (referensi di Order)
                          │

┌─────────────────────────────────────────────────────┐
│                 <<interface>>                        │
│                 Discountable                         │
├─────────────────────────────────────────────────────┤
│  + calculateDiscount(price: Double): Double          │
└─────────────────────────────────────────────────────┘
                          ▲
                          │ implements
                          │
┌─────────────────────────────────────────────────────┐
│                    Produk                            │
├─────────────────────────────────────────────────────┤
│  - id : String                                       │
│  - nama : String                                     │
│  - harga : Double                                    │
│  - kategori : String                                 │
│  - stok : Int                                        │
│  - bisaDiskon : Boolean                              │
│  - deskripsi : String                                │
│  + calculateDiscount(price: Double): Double          │
└─────────────────────────────────────────────────────┘
```

