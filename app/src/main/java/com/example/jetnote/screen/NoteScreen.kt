package com.example.jetnote.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.data.Note
import com.example.jetnote.data.NotesDataSource
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes:List<Note>,
    onAddNote: (Note) ->Unit,
    onRemoveNote: (Note) ->Unit
){
    var tile by remember {
        mutableStateOf(value = "")
    }
    
    var description by remember{
        mutableStateOf(value = "")
    }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
colors = TopAppBarDefaults.topAppBarColors(
    Color(0XFFDADFE3)
),
            title = {
            Text(stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "title" )
        })

        //Content


        Column(modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            NoteInputText(text = tile,
                label = "Title",
                onTextChange = {
                    if(it.all {
                        char-> char.isLetter() || char.isWhitespace()
                        })
                        tile= it
                }
                )
            Spacer(modifier = Modifier.height(10.dp))
            NoteInputText(text = description,
                label = "Description",
                onTextChange = {
                    if(it.all {
                                char-> char.isLetter() || char.isWhitespace()
                        })
                        description= it
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            NoteButton(
                onClick = {
                    if(tile.isNotEmpty() && description.isNotEmpty()){
                        onAddNote(Note(
                            title = tile,
                            description = description
                        ))
                        tile=""
                        description=""
                        Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
                    }
                },
                text = "Save"
            )
        }
        HorizontalDivider(color = Color.DarkGray)
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(notes){
                    note->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(note)
                })
            }
        }
    }

}


@Composable
fun NoteRow(modifier: Modifier =Modifier,
            note: Note,
            onNoteClicked:(Note) ->Unit
            ){
    Surface(modifier = modifier.padding(4.dp).clip(
        RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)
    ).fillMaxWidth(), color = Color(0xFFDFE6EB),
        tonalElevation = 6.dp
        ) {
        Column(modifier.clickable {
            onNoteClicked(note)
        }.padding(horizontal = 14.dp,
            vertical = 6.dp,
            ), horizontalAlignment = Alignment.Start) {
            Text(text = note.title, style = MaterialTheme.typography.labelMedium)
            Text(text = note.description, style = MaterialTheme.typography.titleMedium)
            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")), style = MaterialTheme.typography.titleMedium)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}