data class NilaiMahasiswa(
    val nim: String,
    val nama: String,
    val mataKuliah: String,
    val nilai: Double
)

fun main(){
    val mahasiswaList = listOf(
        NilaiMahasiswa("2024001", "Budi Santoso", "Pemrograman", 85.0),
        NilaiMahasiswa("2024002", "Ani Wijaya", "Pemrograman", 92.0),
        NilaiMahasiswa("2024003", "Citra Dewi", "Pemrograman", 68.0),
        NilaiMahasiswa("2024004", "Jesica Jung", "Pemrograman", 45.0),
        NilaiMahasiswa("2004005", "Bentari Agung", "Pemrograman", 78.0),
        NilaiMahasiswa("2024006", "Michael Haryono", "Pemrograman", 88.0),
        NilaiMahasiswa("2004007", "Rusli Rushle", "Pemrograman",72.0),
        NilaiMahasiswa("2024008", "Rosalia Muhammad", "Pemrograman", 55.0),
        NilaiMahasiswa("2024009", "Haric Wang","Pemrograman", 90.0),
        NilaiMahasiswa("2024010", "Ryan lee", "Pemrograman", 63.0),
    )

    println("===== DATA NILAI MAHASISWA =====")
    println("No  NIM       Nama              MataKuliah       Nilai")
    mahasiswaList.forEachIndexed {  index, mhs ->
        println("${index + 1} ${mhs.nim} ${mhs.nama.padEnd(16)} ${mhs.mataKuliah.padEnd(15)} ${mhs.nilai.toInt()}")
}
    println("===== STATISTIK =====")
    println("Total Mahasiswa : ${mahasiswaList.size}")
    val rataRata = mahasiswaList.map { it.nilai }.average()
    println("Rata-rata Nilai : %.1f".format(rataRata))

    val tertinggi = mahasiswaList.maxByOrNull { it.nilai }
    println("Nilai Tertinggi : ${tertinggi?.nilai?.toInt()} (${tertinggi?.nama})")

    val terendah = mahasiswaList.minByOrNull { it.nilai }
    println("Nilai Terendah : ${terendah?.nilai?.toInt()} (${terendah?.nama})")

    println("===== MAHASISWA LULUS =====")
    val lulus = mahasiswaList.filter { it.nilai >=70 }
    if (lulus.isNotEmpty()) lulus.forEachIndexed { index, mhs ->
        println("${index+1}. ${mhs.nama} - ${mhs.nilai.toInt()} (${getGrade(mhs.nilai)})")
    }
    println("Semua Mahasiswa Lulus!")

    println("===== URUTAN NILAI (TERTINGGI - TERENDAH) =====")
    val sortedDesc = mahasiswaList.sortedByDescending { it.nilai }
    sortedDesc.forEachIndexed { index, mhs ->
        println("${index + 1}. ${mhs.nama} - ${mhs.nilai.toInt()}")
    }

    println("===== KELOMPOK BERDASARKAN GRADE =====")
    val groupByGrade = mahasiswaList.groupBy { getGrade(it.nilai) }
    groupByGrade.forEach { (grade, listMhs) ->
        println("\nGrade $grade:")
        listMhs.forEach { mhs ->
            println("  - ${mhs.nama} (${mhs.nilai.toInt()})")
        }
    }

    println("===== JUMLAH PER GRADE =====")
    val gradeList = listOf("A", "B", "C", "D", "E")
    gradeList.forEach { grade ->
        val jumlah = groupByGrade[grade]?.size ?: 0
        println("Grade $grade: $jumlah mahasiswa")
    }

    println("===== PENCARIAN MAHASISWA =====")
    val kataKunci = readlnOrNull()?.ifEmpty { null } ?: ""
    println("Mencari nama mengandung: '$kataKunci'")
    val hasilPencarian = mahasiswaList.filter { it.nama.contains(kataKunci, ignoreCase = true) }
    if (hasilPencarian.isNotEmpty()) {
        hasilPencarian.forEach { mhs ->
            println("- ${mhs.nama} (${mhs.nim})")
        }
    } else {
        println("Tidak ditemukan mahasiswa dengan nama mengandung '$kataKunci'")
    }
}
val getGrade: (Double) -> String = { nilai ->
    when {
        nilai >= 85 -> "A"
        nilai >= 70 -> "B"
        nilai >= 60 -> "C"
        nilai >= 50 -> "D"
        else -> "E"
    }
}