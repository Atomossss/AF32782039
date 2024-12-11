package br.edu.up.appaf32782039.Navigation

sealed class NotesNavigationItem(val route:String) {
    object SplashScreen:NotesNavigationItem(route = "splash")
    object HomeScreen:NotesNavigationItem(route = "home")
    object InsetNotesScreen:NotesNavigationItem(route = "insert")
}

