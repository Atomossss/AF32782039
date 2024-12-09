package br.edu.up.appaf32782039.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.edu.up.appaf32782039.Screens.SplashScreen

@Composable
fun NotesNavigation(navHostController: NavHostController) {

    NavHost(navController = navHostController , startDestination = "splash" )
    {
        composable("splash"){
            SplashScreen()
        }
    }


}