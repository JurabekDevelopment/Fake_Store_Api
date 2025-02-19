package uz.turgunboyevjurabek.fakestoreapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.navigation.DetailRout
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.navigation.MyNavigation
import uz.turgunboyevjurabek.fakestoreapi.feature.presentation.screens.MainScreen
import uz.turgunboyevjurabek.fakestoreapi.ui.theme.FakeStoreApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FakeStoreApiTheme {
                val navHostController = rememberNavController()
                val currentScreen = navHostController.currentBackStackEntryAsState()
                Log.d("current_rout",currentScreen.value?.destination?.route.toString())
                val current_destination = navHostController.currentDestination?.route
                val rout=current_destination?.substringAfterLast(".",)
                Log.d("current_rout","->"+rout.toString())

                if (currentScreen.value?.destination?.route.toString() == DetailRout.toString()){
                    Log.d("current_rout","Detailga teng bulid")
                }else{
                    Log.d("current_rout","Detailga teng emas")
                }
                MyNavigation(navController = navHostController)
            }
        }
    }
}
