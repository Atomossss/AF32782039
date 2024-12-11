package br.edu.up.appaf32782039.Screens
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.edu.up.appaf32782039.Models.Notes
import br.edu.up.appaf32782039.Navigation.NotesNavigationItem
import br.edu.up.appaf32782039.ui.theme.colorBlack
import br.edu.up.appaf32782039.ui.theme.colorGrey
import br.edu.up.appaf32782039.ui.theme.colorLigthGray
import br.edu.up.appaf32782039.ui.theme.colorRed
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun InsertNotesScreen(navHostController: NavHostController){

    val context = LocalContext.current
    val db=FirebaseFirestore.getInstance()
    val notesDBRef=db.collection("notes")

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    if(title.value.isEmpty() && description.value.isEmpty()) {
                        Toast.makeText(context,"Enter valid date", Toast.LENGTH_SHORT).show()
                    }else {

                        val id = notesDBRef.document().id
                        val notes= Notes(
                            id=id,
                            title=title.value,
                            description=description.value
                        )

                        notesDBRef.document(id).set(notes).addOnCompleteListener {
                            if (it.isSuccessful) {

                                Toast.makeText(context,"Registro feito com sucesso",
                                    Toast.LENGTH_SHORT)
                                    .show()
                                    navHostController.popBackStack()

                            }



                            else {
                                Toast.makeText(context,"Algo deu errado",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                }, containerColor = colorRed,
                contentColor = Color.White,
                shape = RoundedCornerShape(corner = CornerSize(100.dp))
            ) {

                Icon( imageVector= Icons.Default.Done, contentDescription = "")

            }
        }
    ) { innerPadding->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(colorBlack)
        ) {
            Column(modifier = Modifier.padding(15.dp)) {

                Text(text = "Criar notas", style = TextStyle(
                    fontSize = 32.sp, color = Color.White, fontWeight = FontWeight.Bold
                )
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(colors= TextFieldDefaults.colors(
                    focusedContainerColor = colorGrey,
                    unfocusedContainerColor = colorGrey,
                    focusedIndicatorColor =  Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                    label = {
                        Text(text = "Digite seu título",
                            style = TextStyle
                                (fontSize = 18.sp,
                                color= colorLigthGray)
                        )
                }, value = title.value, onValueChange = {
                    title.value=it
                }, modifier = Modifier
                    .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(15.dp))
                TextField(colors= TextFieldDefaults.colors(
                    focusedContainerColor = colorGrey,
                    unfocusedContainerColor = colorGrey,
                    focusedIndicatorColor =  Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                    shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                    label = {
                        Text(text = "Digite a descrição",
                            style = TextStyle
                                (fontSize = 18.sp,
                                color= colorLigthGray)
                        )
                    }, value = description.value, onValueChange = {
                        description.value=it
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                )

            }

        }


    }

}