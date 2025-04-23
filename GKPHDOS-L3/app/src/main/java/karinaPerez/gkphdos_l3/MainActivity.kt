package karinaPerez.gkphdos_l3

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import karinaPerez.gkphdos_l3.ui.theme.GKPHDOSL3Theme
import karinaPerez.gkphdos_l3.views.HomeScreen
import karinaPerez.gkphdos_l3.views.MainHomeScreen
import karinaPerez.gkphdos_l3.views.SensorScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GKPHDOSL3Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") {
                        MainHomeScreen(
                            onSensorClick = { navController.navigate("sensor") },
                            onHomeClick = { navController.navigate("home") },
                        )
                    }
                    composable("sensor") {
                        SensorScreen() {
                            navController.popBackStack()
                        }
                    }
                    composable("home") {
                        HomeScreen() {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}