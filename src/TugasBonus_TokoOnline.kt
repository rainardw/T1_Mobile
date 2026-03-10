import java.time.LocalDateTime
import kotlin.random.Random
sealed class OrderStatus {
    object Pending : OrderStatus()
    object Processing : OrderStatus()
    object Shipped : OrderStatus()
    object Delivered : OrderStatus()
    object Cancelled : OrderStatus()

    override fun toString(): String {
        return when (this) {
            is Pending -> "Pending"
            is Processing -> "Processing"
            is Shipped -> "Shipped"
            is Delivered -> "Delivered"
            is Cancelled -> "Dibatalkan"
        }
    }
}

sealed class PaymentMethod {
    object Cash : PaymentMethod()
    object Transfer : PaymentMethod()
    object EWallet : PaymentMethod()

    override fun toString(): String {
        return when (this) {
            is Cash -> "Tunai"
            is Transfer -> "Transfer Bank"
            is EWallet -> "E-Wallet"
        }
    }
}

interface Discountable {
    fun calculateDiscount(price: Double): Double
}

data class Produk(
    val id: String,
    val nama: String,
    val harga: Double,
    val kategori: String,
    val stok: Int,
    val bisaDiskon: Boolean = false,
    val deskripsi: String = ""
) : Discountable {
    override fun calculateDiscount(price: Double): Double {
        return if (bisaDiskon) price * 0.1 else 0.0
    }
}

data class CartItem(
    val produk: Produk,
    val jumlah: Int
) {
    val subtotal: Double
        get() = (produk.harga * jumlah) - produk.calculateDiscount(produk.harga * jumlah)
}

data class Customer(
    val id: String,
    val nama: String,
    val email: String,
    val alamat: String? = null
)

data class Order(
    val id: String,
    val customer: Customer,
    val items: List<CartItem>,
    var status: OrderStatus,
    val paymentMethod: PaymentMethod,
    val totalHarga: Double,
    val tanggal: String = try {
        LocalDateTime.now().toString().take(10)
    } catch (e: Exception) {
        "2024-01-01"
    },
    val virtualAccount: String? = null
)

class ShoppingCart {
    private val items = mutableListOf<CartItem>()

    fun tambahProduk(produk: Produk, jumlah: Int): Boolean {
        if (jumlah > produk.stok) {
            println("Stok tidak cukup! Tersedia: ${produk.stok}")
            return false
        }
        val existingItem = items.find { it.produk.id == produk.id }
        if (existingItem != null) {
            val index = items.indexOf(existingItem)
            items[index] = existingItem.copy(jumlah = existingItem.jumlah + jumlah)
        } else {
            items.add(CartItem(produk, jumlah))
        }
        println("${produk.nama} berhasil ditambahkan ke keranjang.")
        return true
    }

    fun hapusProdukByNomor(nomor: Int): Boolean {
        if (nomor < 1 || nomor > items.size) {
            println("Nomor tidak valid!")
            return false
        }
        val removedItem = items[nomor - 1]
        items.removeAt(nomor - 1)
        println("${removedItem.produk.nama} dihapus dari keranjang.")
        return true
    }

    fun hapusProduk(produkId: String): Boolean {
        val removed = items.removeIf { it.produk.id.equals(produkId, ignoreCase = true) }
        if (removed) {
            println("Produk dihapus dari keranjang.")
        } else {
            println("Produk tidak ditemukan di keranjang.")
        }
        return removed
    }

    fun hitungTotal(): Double {
        return items.sumOf { it.subtotal }
    }

    fun tampilkanKeranjang() {
        if (items.isEmpty()) {
            println("Keranjang kosong.")
            return
        }
        println("\nKERANJANG BELANJA:")
        println("-".repeat(60))
        items.forEachIndexed { index, item ->
            println("${index + 1}. ${item.produk.nama} x${item.jumlah}")
            println("   Harga: Rp ${item.produk.harga.toLong()} | Subtotal: Rp ${item.subtotal.toLong()}")
        }
        println("-".repeat(60))
        println("Total: Rp ${hitungTotal().toLong()}")
    }

    fun kosongkan() {
        items.clear()
    }

    fun getItems(): List<CartItem> = items.toList()
    fun isEmpty(): Boolean = items.isEmpty()
    fun getSize(): Int = items.size
}

class TokoOnline(val namaToko: String) {
    private val produkList = mutableListOf<Produk>()
    private val orderList = mutableListOf<Order>()
    private var orderCounter = 1

    fun tambahProduk(produk: Produk) {
        produkList.add(produk)
    }

    fun getProdukList(): List<Produk> = produkList.toList()
    fun getOrderList(): List<Order> = orderList.toList()

    fun tampilkanProduk() {
        println("\nKATALOG PRODUK - $namaToko")
        println("=".repeat(100))
        println("%-3s %-8s %-28s %-15s %-12s %-8s".format("No", "ID", "Nama", "Kategori", "Harga", "Stok"))
        println("-".repeat(100))
        produkList.forEachIndexed { index, produk ->
            println("%-3s %-8s %-28s %-15s Rp %-10s %-8s".format(
                index + 1,
                produk.id,
                produk.nama.take(27),
                produk.kategori,
                produk.harga.toLong(),
                produk.stok
            ))
        }
    }

    fun tampilkanDetailProduk(id: String) {
        val produk = getProdukById(id)
        if (produk != null) {
            println("\n" + "=".repeat(50))
            println("DETAIL PRODUK")
            println("=".repeat(50))
            println("ID          : ${produk.id}")
            println("Nama        : ${produk.nama}")
            println("Kategori    : ${produk.kategori}")
            println("Harga       : Rp ${produk.harga.toLong()}")
            println("Stok        : ${produk.stok}")
            println("Deskripsi   : ${produk.deskripsi}")
            println("=".repeat(50))
        } else {
            println("Produk tidak ditemukan!")
        }
    }

    fun getProdukById(id: String): Produk? {
        return produkList.find { it.id.equals(id, ignoreCase = true) }
    }

    fun hitungTotalDenganDiskon(
        items: List<CartItem>,
        discountCalculator: (Double) -> Double
    ): Double {
        val subtotal = items.sumOf { it.subtotal }
        return discountCalculator(subtotal)
    }

    fun generateVANumber(): String {
        val bankCodes = mapOf(
            "BCA" to "0123",
            "MANDIRI" to "0088",
            "BNI" to "0099",
            "BRI" to "0022"
        )
        val banks = bankCodes.keys.toList()
        val randomBank = banks[Random.nextInt(banks.size)]
        val bankCode = bankCodes[randomBank]
        val randomNum = Random.nextInt(
            1000000000,
            9999999999.toInt()
        )
        return "$bankCode$randomNum"
    }

    fun checkout(customer: Customer, cart: ShoppingCart, paymentMethod: PaymentMethod): Order? {
        val items = cart.getItems()
        if (items.isEmpty()) {
            println("Keranjang kosong, tidak bisa checkout.")
            return null
        }

        val discountFunc: (Double) -> Double = { total ->
            if (total > 500000) {
                println("Anda mendapat diskon 5%!")
                total * 0.95
            } else total
        }

        val totalAkhir = hitungTotalDenganDiskon(items, discountFunc)

        var vaNumber: String? = null
        if (paymentMethod is PaymentMethod.Transfer) {
            vaNumber = generateVANumber()
            println("\n" + "=".repeat(50))
            println("INFORMASI PEMBAYARAN TRANSFER")
            println("=".repeat(50))
            println("Virtual Account Number: $vaNumber")
            println("Bank: BCA / Mandiri / BNI / BRI (Random)")
            println("Nominal Transfer: Rp ${totalAkhir.toLong()}")
            println("Batas Waktu: 24 jam")
            println("=".repeat(50))
            println("Silakan transfer ke nomor VA di atas.")
            println("Setelah transfer, status order akan menjadi Processing.")
        }

        val order = Order(
            id = "ORD-${orderCounter++}".padStart(10, '0'),
            customer = customer,
            items = items,
            status = OrderStatus.Pending,
            paymentMethod = paymentMethod,
            totalHarga = totalAkhir,
            virtualAccount = vaNumber
        )
        orderList.add(order)
        cart.kosongkan()
        return order
    }

    fun updateStatus(orderId: String, status: OrderStatus): Boolean {
        val order = orderList.find { it.id.equals(orderId, ignoreCase = true) }
        if (order != null) {
            order.status = status
            println("Status order ${order.id} diubah menjadi: ${status}")
            return true
        } else {
            println("Order tidak ditemukan.")
            return false
        }
    }

    fun tampilkanRiwayat(customer: Customer) {
        println("\nRIWAYAT PESANAN - ${customer.nama}")
        println("=".repeat(70))
        val customerOrders = orderList.filter { it.customer.id == customer.id }
        if (customerOrders.isEmpty()) {
            println("Belum ada pesanan.")
            return
        }
        val groupedByStatus = customerOrders.groupBy { it.status }
        groupedByStatus.forEach { (status, orders) ->
            println("\nStatus: ${status}")
            orders.forEach { order ->
                var orderInfo = "   ${order.id} - Total: Rp ${order.totalHarga.toLong()} - ${order.tanggal}"
                if (order.virtualAccount != null) {
                    orderInfo += " - VA: ${order.virtualAccount}"
                }
                println(orderInfo)
            }
        }
    }

    fun tampilkanStatistik() {
        println("\nSTATISTIK TOKO")
        println("=".repeat(70))
        println("Total Produk: ${produkList.size}")
        println("Total Order: ${orderList.size}")
        val byKategori = produkList.groupBy { it.kategori }
        println("\nProduk per Kategori:")
        byKategori.forEach { (kat, list) ->
            println("  - $kat: ${list.size} produk")
        }
        val byStatus = orderList.groupBy { it.status }
        println("\nOrder per Status:")
        byStatus.forEach { (status, list) ->
            println("  - $status: ${list.size} order")
        }
        if (orderList.isNotEmpty()) {
            val avgOrder = orderList.map { it.totalHarga }.average()
            println("\nRata-rata Nilai Order: Rp ${avgOrder.toLong()}")
        }
    }
}

fun main() {
    val toko = TokoOnline("DARENDLE Fantasy Emporium")
    val cart = ShoppingCart()
    var currentCustomer: Customer? = null
    var currentOrder: Order? = null

    toko.tambahProduk(Produk("F001", "Tongkat Sihir Elderwood", 15000000.0, "Senjata", 5, true, "Tongkat dari kayu elder kuno dengan inti kristal phoenix"))
    toko.tambahProduk(Produk("F002", "Ramuan Pemulihan Jiwa", 250000.0, "Ramuan", 20, true, "Mengembalikan 500 MP secara instan"))
    toko.tambahProduk(Produk("F003", "Grimoire Sihir Kuno", 750000.0, "Buku Sihir", 15, true, "Buku mantra peninggalan penyihir agung Merlin"))
    toko.tambahProduk(Produk("F004", "Telur Naga Merah", 50000000.0, "Jimat", 2, true, "Telur naga api yang siap menetas"))
    toko.tambahProduk(Produk("F005", "Jubah Bayangan", 1200000.0, "Armor", 10, false, "Memberikan stealth +50 selama 1 jam"))
    toko.tambahProduk(Produk("F006", "Kristal Mystic", 350000.0, "Jimat", 30, true, "Kristal dengan energi magis murni"))
    toko.tambahProduk(Produk("F007", "Pedang Excalibur Mini", 2500000.0, "Senjata", 8, true, "Replika pedang legendaris Raja Arthur"))
    toko.tambahProduk(Produk("F008", "Scroll Teleportasi", 800000.0, "Buku Sihir", 25, true, "Teleportasi ke lokasi yang pernah dikunjungi"))
    toko.tambahProduk(Produk("F009", "Potion Invisibility", 1800000.0, "Ramuan", 12, false, "Menghilang selama 10 menit"))
    toko.tambahProduk(Produk("F010", "Perisai Dragon Scale", 3200000.0, "Armor", 6, true, "Perisai dari sisik naga asli"))
    toko.tambahProduk(Produk("F011", "Cincin Kekuatan", 8000000.0, "Jimat", 3, true, "Meningkatkan strength +100"))
    toko.tambahProduk(Produk("F012", "Sayap Malaikat", 12000000.0, "Aksesoris", 4, true, "Memungkinkan terbang selama 30 menit"))
    toko.tambahProduk(Produk("F013", "Buku Necromancy", 2500000.0, "Buku Sihir", 7, false, "Mantra untuk membangkitkan undead"))
    toko.tambahProduk(Produk("F014", "Amulet Protection", 1500000.0, "Jimat", 15, true, "Defense +50 terhadap sihir hitam"))
    toko.tambahProduk(Produk("F015", "Botol Fairy Dust", 450000.0, "Ramuan", 25, true, "Debu peri untuk enchantment"))
    toko.tambahProduk(Produk("F016", "Helm Valkyrie", 4500000.0, "Armor", 5, true, "Helm perang dewi Valkyrie"))
    toko.tambahProduk(Produk("F017", "Tombak Odin", 18000000.0, "Senjata", 2, true, "Tombak suci dewa Odin"))
    toko.tambahProduk(Produk("F018", "Crystal Ball", 950000.0, "Jimat", 10, false, "Bola kristal untuk ramalan"))
    toko.tambahProduk(Produk("F019", "Elixir Immortality", 99000000.0, "Ramuan", 1, true, "Ramuan keabadian (edisi terbatas)"))
    toko.tambahProduk(Produk("F020", "Peta Dunia Fantasi", 300000.0, "Buku Sihir", 50, false, "Peta lengkap seluruh realm"))

    while (true) {
        println("\n" + "=".repeat(70))
        println("TOKO FANTASI DARENDLE")
        println("=".repeat(70))
        println("1. Lihat Katalog Produk")
        println("2. Lihat Detail Produk")
        println("3. Tambah Produk ke Keranjang")
        println("4. Lihat Keranjang")
        println("5. Hapus Produk dari Keranjang")
        println("6. Checkout")
        println("7. Update Status Pesanan")
        println("8. Lihat Riwayat Pesanan")
        println("9. Statistik Toko")
        println("0. Keluar")
        print("\nPilih menu (0-9): ")

        val pilihan = readLine()?.trim()

        when (pilihan) {
            "1" -> {
                toko.tampilkanProduk()
            }
            "2" -> {
                toko.tampilkanProduk()
                print("\nMasukkan ID Produk untuk melihat detail: ")
                val idProduk = readLine()?.trim()
                toko.tampilkanDetailProduk(idProduk ?: "")
            }
            "3" -> {
                toko.tampilkanProduk()
                print("\nMasukkan ID Produk (contoh: F001): ")
                val idProduk = readLine()?.trim()
                val produk = toko.getProdukById(idProduk ?: "")

                if (produk != null) {
                    print("Masukkan Jumlah: ")
                    val jumlahInput = readLine()?.trim()
                    val jumlah = jumlahInput?.toIntOrNull() ?: 0
                    if (jumlah > 0) {
                        cart.tambahProduk(produk, jumlah)
                    } else {
                        println("Jumlah harus angka lebih dari 0!")
                    }
                } else {
                    println("Produk tidak ditemukan!")
                }
            }
            "4" -> {
                cart.tampilkanKeranjang()
            }
            "5" -> {
                cart.tampilkanKeranjang()
                if (!cart.isEmpty()) {
                    print("\nMasukkan NOMOR URUT produk yang akan dihapus (1-${cart.getSize()}): ")
                    val nomorInput = readLine()?.trim()?.toIntOrNull()
                    if (nomorInput != null) {
                        cart.hapusProdukByNomor(nomorInput)
                    } else {
                        println("Input harus angka!")
                    }
                }
            }
            "6" -> {
                if (cart.isEmpty()) {
                    println("Keranjang kosong!")
                    continue
                }

                if (currentCustomer == null) {
                    print("\nMasukkan ID Customer: ")
                    val idCust = readLine()?.trim() ?: "C001"
                    print("Masukkan Nama: ")
                    val nama = readLine()?.trim() ?: "Petualang"
                    print("Masukkan Email: ")
                    val email = readLine()?.trim() ?: "adventurer@realm.com"
                    print("Masukkan Alamat (opsional): ")
                    val alamat = readLine()?.trim()

                    currentCustomer = Customer(idCust, nama, email, alamat)
                }

                println("\nMetode Pembayaran:")
                println("1. Tunai")
                println("2. Transfer Bank")
                println("3. E-Wallet")
                print("Pilih (1-3): ")
                val paymentChoice = readLine()?.trim()

                val paymentMethod = when (paymentChoice) {
                    "2" -> PaymentMethod.Transfer
                    "3" -> PaymentMethod.EWallet
                    else -> PaymentMethod.Cash
                }

                val customer = currentCustomer
                if (customer != null) {
                    currentOrder = toko.checkout(customer, cart, paymentMethod)

                    if (currentOrder != null) {
                        println("\nCHECKOUT BERHASIL!")
                        println("Order ID: ${currentOrder!!.id}")
                        println("Total: Rp ${currentOrder!!.totalHarga.toLong()}")
                        if (currentOrder!!.virtualAccount != null) {
                            println("Virtual Account: ${currentOrder!!.virtualAccount}")
                        }
                    }
                }
            }
            "7" -> {
                print("\nMasukkan Order ID: ")
                val orderId = readLine()?.trim() ?: ""

                println("\nStatus:")
                println("1. Pending")
                println("2. Processing")
                println("3. Shipped")
                println("4. Delivered")
                println("5. Cancelled")
                print("Pilih status baru (1-5): ")

                val statusChoice = readLine()?.trim()
                val newStatus = when (statusChoice) {
                    "2" -> OrderStatus.Processing
                    "3" -> OrderStatus.Shipped
                    "4" -> OrderStatus.Delivered
                    "5" -> OrderStatus.Cancelled
                    else -> OrderStatus.Pending
                }

                toko.updateStatus(orderId, newStatus)
            }
            "8" -> {
                if (currentCustomer != null) {
                    toko.tampilkanRiwayat(currentCustomer!!)
                } else {
                    println("Belum ada customer yang login!")
                }
            }
            "9" -> {
                toko.tampilkanStatistik()
            }
            "0" -> {
                println("\nTerima kasih telah berbelanja di ${toko.namaToko}!")
                break
            }
            else -> {
                println("Pilihan tidak valid!")
            }
        }
        println("\nTekan Enter untuk melanjutkan...")
        readLine()
    }
}