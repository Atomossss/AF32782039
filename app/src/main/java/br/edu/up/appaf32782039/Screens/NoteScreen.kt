package br.edu.up.appaf32782039.Screens

import android.content.DialogInterface
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import br.edu.up.appaf32782039.Models.Notes
import br.edu.up.appaf32782039.ui.theme.colorBlack
import br.edu.up.appaf32782039.ui.theme.colorGrey
import br.edu.up.appaf32782039.ui.theme.colorLigthGray
import br.edu.up.appaf32782039.ui.theme.colorRed
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import android.app.AlertDialog
import androidx.navigation.NavHostController
import br.edu.up.appaf32782039.Navigation.NotesNavigationItem


@Composable
fun NoteScreen(navHostController: NavHostController) {


    val db=FirebaseFirestore.getInstance()
    val notesDBRef=db.collection("notes")
    val notesList= remember { mutableListOf<Notes>() }
    val dataValue= remember { mutableStateOf(false) }

    LaunchedEffect(Unit){

        notesDBRef.addSnapshotListener { value, error ->
            if (error == null) {
                val data = value?.toObjects(Notes::class.java)
                notesList.clear()
                notesList.addAll(data!!)
                dataValue.value=true
            }else
            {
                dataValue.value=false
            }
        }
    }

        Scaffold(floatingActionButton = {
            FloatingActionButton(contentColor = Color.White,
                containerColor = colorRed,
                shape = RoundedCornerShape(corner = CornerSize(100.dp)),
                onClick = {
                    navHostController.navigate(NotesNavigationItem.InsetNotesScreen.route)
                }) {
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

                    if(dataValue.value){
                        LazyColumn {
                            items(notesList){notes->
                                ListItems(notes,notesDBRef)
                            }
                        }
                    }else
                    {
                        Box(modifier=Modifier.fillMaxSize())
                        {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(25.dp)
                                    .align(Alignment.Center))
                        }
                    }
                }
            }
        }
    }





    @Composable
    fun ListItems(notes: Notes, notesDBRef: CollectionReference) {

        val context = LocalContext.current

        var expanded by remember {mutableStateOf(false)}

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(corner = CornerSize(20.dp)))
                .background(color = colorGrey)
        ) {

            DropdownMenu(modifier = Modifier.background(Color.White),
                properties = PopupProperties(clippingEnabled=true),
                offset= DpOffset(x=(-40).dp,y=(0).dp),
                expanded = expanded,
                onDismissRequest = {
                expanded=false
            }) {

                DropdownMenuItem(
                    text={ Text(text="Update", style = TextStyle(colorGrey)) },
                    onClick = {

                })
                DropdownMenuItem(
                    text ={Text(text="Delete", style = TextStyle(colorGrey))},
                    onClick = {


                        val alertDialog= AlertDialog.Builder(context)
                        alertDialog.setPositiveButton("yes",
                            object:DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    notesDBRef.document(notes.id).delete()
                                    dialog?.dismiss()
                                }
                            })

                        alertDialog.setNegativeButton("No",
                            object : DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    dialog?.dismiss()
                                }
                            })
                        alertDialog.show()
                })


            }

            Icon(imageVector = Icons.Default.MoreVert, contentDescription ="",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
                    .clickable{
                        expanded = true
                    }

            )

            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = notes.title, style = TextStyle(color= Color.White)
                )
                Text(text = notes.description, style = TextStyle(color = colorLigthGray)
                )
            }
            }


        }


