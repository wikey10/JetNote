package com.example.jetnote.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetnote.data.Note
import com.example.jetnote.data.NotesDataSource

class NoteViewModel : ViewModel() {

    private  var  noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }



    fun addNote(note: Note){
        noteList.add(note)
    }

    fun  removeNote(note: Note){
        noteList.remove(note)
    }

    fun getNotes() :List<Note>{
        return  noteList
    }
}