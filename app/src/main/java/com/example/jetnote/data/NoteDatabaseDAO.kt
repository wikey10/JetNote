package com.example.jetnote.data

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDAO {

    @Query(value = "SELECT * from notes_tbl")
    fun getNotes() : Flow<List<Note>>

    @Query(value = "SELECT * from notes_tbl where id=:id")
    suspend fun getNoteByID(id:String):Note

    @Insert(onConflict = OnConflictStrategy. REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy. REPLACE)
    suspend fun update(note: Note)

    @Query(value = "DELETE from notes_tbl")
    suspend fun deleteAll()

    @Delete
   suspend fun deleteNote(note: Note)

}
