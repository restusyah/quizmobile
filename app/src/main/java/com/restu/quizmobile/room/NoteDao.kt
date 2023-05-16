package com.restu.quizmobile.room

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(skripsi: Skripsi)

    @Update
    suspend fun updateNote(skripsi: Skripsi)

    @Delete
    suspend fun deleteNote(skripsi: Skripsi)

    @Query("SELECT * FROM skripsi")
    suspend fun getNotes(): List<Skripsi>

    @Query("SELECT * FROM skripsi WHERE id=:note_id")
    suspend fun getNote(note_id: Int): List<Skripsi>

}