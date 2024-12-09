package br.edu.up.appaf32782039.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.up.appaf32782039.Models.Notes
import br.edu.up.appaf32782039.ui.theme.colorBlack
import br.edu.up.appaf32782039.ui.theme.colorGrey
import br.edu.up.appaf32782039.ui.theme.colorLigthGray
import br.edu.up.appaf32782039.ui.theme.colorRed


@Composable
@Preview
fun NoteScreen() {

    val notesList = listOf (
        Notes(
            title = "Qualquer coisa1",
            description = "Seila blabla"

        ),
        Notes(
            title = "Qualquer coisa2",
        description = "Seila blabla"
    ),
        Notes(
            title = "Qualquer coisa3",
        description = "Seila blabla"
    )
    )

    Scaffold(floatingActionButton = {
        FloatingActionButton(contentColor = Color.White,
            containerColor = colorRed,
            shape = RoundedCornerShape(corner = CornerSize(100.dp)),
            onClick = {}) {
            Icon(imageVector = Icons.Default.Add, contentDescription ="")
            }

        }
    ) {innerPadding->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(colorBlack)

        )
        {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription ="" )

            Column(modifier = Modifier.padding(15.dp)) {
                Text(text = "Criar notas", style = TextStyle(
                    fontSize = 32.sp, color = Color.White, fontWeight = FontWeight.Bold
                    )
                )
                LazyColumn {
                    items(notesList){ notes ->
                        ListItems(notes)

                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun ListItemsPreview() {

    ListItems(notes = Notes (
            "Este é o titulo",
            "Ei titulo"
        )
    )

}


@Composable
fun ListItems(notes: Notes) {
    Box(
        modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(20.dp)))
        .background(color = colorGrey)
    ) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription ="",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)

        )

        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = notes.title, style = TextStyle(color= Color.White)
            )
            Text(text = notes.description, style = TextStyle(color = colorLigthGray))
        }


    }

}

