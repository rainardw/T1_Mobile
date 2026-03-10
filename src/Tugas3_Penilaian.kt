fun main (){
    println("===== SISTEM PENILAIAN =====")
    println()

    print("Masukkan Nama Mahasiswa: ")
    val nama = readLine()?.ifEmpty { null } ?: "Tanpa Nama"

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
val uts = mintaNilai("UTS")
val uas = mintaNilai(" UAS")
val tugas = mintaNilai ("TUGAS")

val nilaiAkhir = (uts * 0.3) + (uas * 0.4) + (tugas * 0.3)
    val grade = when{
        nilaiAkhir in 85.0..100.0 -> "A"
        nilaiAkhir in 70.0..84.9 -> "B"
        nilaiAkhir in 60.0..69.9 -> "C"
        nilaiAkhir in 50.0..59.9 -> "D"
        else -> "E"
    }
val keterangan = when (grade){
    "A" -> "Sangat Baik"
    "B" -> "Baik"
    "C" -> "cukup"
    "D" -> "Kurang"
    else -> "Sangat Kurang"
}
val status = if (nilaiAkhir >=60.0) "LULUS" else "TIDAK LULUS"

    println()
    println("===== HASIL PENILAIAN =====")
    println("Nama             : $nama")
    println("Nilai UTS        : %.0f (Bobot 30%%)".format(uts))
    println("Nilai UAS        : %.0f (Bobot 40%%)".format(uas))
    println("Nilai Tugas      : %.0f (Bobot 30%%)".format(tugas))
    println("-----------------------------------")

    println("Nilai Akhir      : %.1f".format(nilaiAkhir))
    println("Grade            : $grade")
    println("Keterangan       : $keterangan")
    println("Status           : $status")
    println()

    if (status == "LULUS"){
        println("Selamat! Anda dinyatakan LULUS.")
    } else{
        println("Maaf, Anda dinyatakan TIDAK LULUS. Jangan menyerah!")
    }
}
