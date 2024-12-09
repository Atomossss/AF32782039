package br.edu.up.appaf32782039.Screens

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.up.appaf32782039.R
import br.edu.up.appaf32782039.ui.theme.colorBlack





@Composable
@Preview
fun SplashScreen(modifier: Modifier = Modifier) {
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
}
