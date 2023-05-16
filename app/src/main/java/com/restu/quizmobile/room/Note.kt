package com.restu.quizmobile.room
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Skripsi(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nisn: String,
    val nama: String,
    val alamat: String,
    val asalsekolah: String,
)
