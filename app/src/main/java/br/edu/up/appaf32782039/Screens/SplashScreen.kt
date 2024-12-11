package br.edu.up.appaf32782039.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.edu.up.appaf32782039.Navigation.NotesNavigationItem
import br.edu.up.appaf32782039.R
import br.edu.up.appaf32782039.ui.theme.colorBlack
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navHostController: NavHostController) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = colorBlack) // Certifique-se de usar 'Color.Black'
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp).align(Alignment.Center)
            )
        }
    }
    LaunchedEffect(Unit) {
        delay (2500)
        navHostController.navigate(NotesNavigationItem.HomeScreen.route){
            popUpTo(NotesNavigationItem.SplashScreen.route){
                inclusive = true
            }
        }

    }

}
