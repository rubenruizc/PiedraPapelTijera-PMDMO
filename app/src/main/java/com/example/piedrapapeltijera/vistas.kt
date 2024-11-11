import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.piedrapapeltijera.R
import kotlin.random.Random

var puntosJugador = 0
var puntosMaquina = 0

@Composable
fun Eleccion(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 16.dp, end = 16.dp),
    ) {
        Column(
            Modifier.padding(5.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(BorderStroke(2.dp, Color.Blue), shape = RoundedCornerShape(24.dp))
                    .padding(10.dp)
            ) {
                Text(
                    text = "Resultado: $puntosMaquina - $puntosJugador"
                )
            }
        }
        Column(
            Modifier
                .weight(1f)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.robot),
                contentDescription = "Imagen del robot",
                Modifier.size(150.dp,150.dp),
            )
            Text(
                modifier = Modifier.padding(top = 40.dp),
                fontSize = 20.sp,
                text = "La máquina está eligiendo...",
            )
        }
        HorizontalDivider(
            color = Color.Blue,
            thickness = 2.dp
        )
        Column(
            Modifier
                .weight(1f)
                .padding(20.dp)
                .fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                text = "Elige tu opción",
            )
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row() {
                    Button(
                        onClick = {
                            navController.navigate("elegido/1")
                        },
                        Modifier
                            .weight(1f)
                            .padding(5.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.piedra),
                            contentDescription = "Imagen de la piedra",
                            Modifier.size(50.dp,50.dp)
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate("elegido/2")
                        },
                        Modifier
                            .weight(1f)
                            .padding(5.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.papel),
                            contentDescription = "Imagen del papel",
                            Modifier.size(50.dp,50.dp)
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate("elegido/3")
                        },
                        Modifier
                            .weight(1f)
                            .padding(5.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.tijera),
                            contentDescription = "Imagen de las tijeras",
                            Modifier.size(50.dp,50.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Elegido(navController: NavController, opcion: String) {
    val context = LocalContext.current
    var maquina by remember { mutableStateOf(Random.nextInt(1, 4)) }
    var opcionInt = opcion.toInt()

    var eleccionMaquina = R.drawable.piedra
    var eleccionJugador = R.drawable.piedra

    if (maquina == 2) {
        eleccionMaquina = R.drawable.papel
    } else if (maquina == 3) {
        eleccionMaquina = R.drawable.tijera
    }

    if (opcionInt == 2) {
        eleccionJugador = R.drawable.papel
    } else if (opcionInt == 3) {
        eleccionJugador = R.drawable.tijera
    }

    // Variables auxiliares para evitar que sume más de uno
    var pMaquina = puntosMaquina
    var pJugador = puntosJugador

    // Variable para controlar la visualización del Toast
    var showToast by remember { mutableStateOf(false) }

    if ((maquina == 1 && opcionInt == 3) || (maquina == 2 && opcionInt == 1) || (maquina == 3 && opcionInt == 2)) {
        if (!showToast) {
            Toast.makeText(context, "Oh vaya... Has perdido!", Toast.LENGTH_SHORT).show()
            showToast = true
        }
        pMaquina += 1
    } else if ((maquina == 3 && opcionInt == 1) || (maquina == 1 && opcionInt == 2) || (maquina == 2 && opcionInt == 3)) {
        if (!showToast) {
            Toast.makeText(context, "Enhorabuena! Has ganado", Toast.LENGTH_SHORT).show()
            showToast = true
        }
        pJugador += 1
    } else {
        if (!showToast) {
            Toast.makeText(context, "Empate", Toast.LENGTH_SHORT).show()
            showToast = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Resultados",
            fontSize = 24.sp,
            color = Color.Blue,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Elección de la máquina
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = eleccionMaquina),
                contentDescription = "Eleccion de la máquina",
                modifier = Modifier.size(100.dp)
            )
            Text(text = "Máquina", fontSize = 18.sp, color = Color.Gray)
        }

        HorizontalDivider(
            color = Color.Blue,
            thickness = 2.dp,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Elección del jugador
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = eleccionJugador),
                contentDescription = "Eleccion del jugador",
                modifier = Modifier.size(100.dp)
            )
            Text(text = "Tú", fontSize = 18.sp, color = Color.Gray)
        }

        Button(
            onClick = {
                puntosJugador = pJugador
                puntosMaquina = pMaquina

                if (puntosJugador == 3 || puntosMaquina == 3) {
                    if (puntosJugador > puntosMaquina) {
                        navController.navigate("ganador/0")
                    } else {
                        navController.navigate("ganador/1")
                    }
                } else {
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Volver a jugar...",
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun Ganador(navController: NavController, ganador: String) {
    var ganadorInt = ganador.toInt()
    var image = if (ganadorInt == 0) R.drawable.win else R.drawable.lose

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Resultado",
            modifier = Modifier.size(150.dp)
        )
        Button(
            onClick = {
                // Reiniciamos las puntuaciones
                puntosJugador = 0
                puntosMaquina = 0
                navController.navigate("eleccion")
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Volver a jugar")
        }
    }
}