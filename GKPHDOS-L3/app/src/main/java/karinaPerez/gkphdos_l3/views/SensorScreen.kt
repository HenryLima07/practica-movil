package karinaPerez.gkphdos_l3.views

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import karinaPerez.gkphdos_l3.ui.theme.Blue
import karinaPerez.gkphdos_l3.ui.theme.Pink

@Composable
fun SensorScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Blue)
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onBackClick() }, colors = ButtonDefaults.buttonColors(
                        containerColor = Blue,
                        contentColor = White
                    )
                ) {
                    Text(text = "<", fontSize = 40.sp, color = Color.Black)
                }
                Spacer(Modifier.width(25.dp))
                Text("Sensores", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LightSensor()
            Spacer(Modifier.height(20.dp))
            GyroscopeSensor()
            Spacer(Modifier.height(20.dp))
            ProximitySensor()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SensorScreenPrev() {
    SensorScreen()
}

@Composable
fun useSensor(sensorType: Int): List<Float> {
    val context = LocalContext.current
    val sensorManager =
        remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val sensor = sensorManager.getDefaultSensor(sensorType) ?: return emptyList()
    var sensorValues by remember { mutableStateOf(listOf(0f, 0f, 0f)) }

    DisposableEffect(sensorType) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.values?.let {
                    sensorValues = it.toList()
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    return sensorValues
}

@Composable
fun LightSensor() {
    val lightValues = useSensor(Sensor.TYPE_LIGHT)

        Column(
            modifier = Modifier
                .background(Pink, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth(0.7f)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sensor de Luz", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Intensidad: ${lightValues[0]} lx", fontSize = 18.sp)
        }
}

@Composable
fun GyroscopeSensor() {
    val gyroscopeValues = useSensor(Sensor.TYPE_GYROSCOPE)

        Column(
            modifier = Modifier
                .background(Pink, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth(0.7f)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Giroscopio", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "X: ${gyroscopeValues[0]}", fontSize = 18.sp)
            Text(text = "Y: ${gyroscopeValues[1]}", fontSize = 18.sp)
            Text(text = "Z: ${gyroscopeValues[2]}", fontSize = 18.sp)
        }
}

@Composable
fun ProximitySensor() {
    val proximityValues = useSensor(Sensor.TYPE_PROXIMITY)

        Column(
            modifier = Modifier
                .background(Pink, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth(0.7f)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sensor de Proximidad", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Distancia: ${proximityValues[0]} cm", fontSize = 18.sp)
    }
}