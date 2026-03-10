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

#### 2. Compile File Kotlin
kotlinc Tugas3_Penilaian.kt -include-runtime -d Tugas3_Penilaian.jar

#### 3. Jalankan Program
java -jar Tugas3_Penilaian.jar

#### 4. 💻 Contoh Output
#### Skenario 1. Mahasiswa Lulus
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

#### Skenario 2. Mahasiswa Tidak Lulus
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

#### 5.  Alur Kode - Sistem Penilaian Mahasiswa Kotlin
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

#### 6. Alur Input Data
```bash
print("Masukkan Nama Mahasiswa: ")
val nama = readLine()?.ifEmpty { null } ?: "Tanpa Nama"

####  flowchart
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


