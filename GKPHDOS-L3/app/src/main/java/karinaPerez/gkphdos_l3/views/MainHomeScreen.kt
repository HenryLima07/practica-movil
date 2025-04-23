package karinaPerez.gkphdos_l3.views

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import karinaPerez.gkphdos_l3.ui.theme.Blue
import karinaPerez.gkphdos_l3.ui.theme.DarkPurple
import karinaPerez.gkphdos_l3.ui.theme.Purple

@Composable
fun MainHomeScreen(
    modifier: Modifier = Modifier,
    onSensorClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Spacer(Modifier.height(12.dp))
            Text(
                "Bienvenido al \nLaboratorio 04",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { onSensorClick() }) {
                    Text(
                        text = "Sensor",
                        fontSize = 16.sp,
                        color = Purple,
                        modifier = Modifier
                            .width(150.dp)
                            .padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(12.dp))
                Button(onClick = { onHomeClick() }) {
                    Text(
                        text = "Home",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Purple,
                        modifier = Modifier
                            .width(150.dp)
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainHomeScreenPrev() {
    MainHomeScreen()
}