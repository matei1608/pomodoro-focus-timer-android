package com.example.pomodorofocustimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodorofocustimer.ui.theme.PomodoroFocusTimerTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PomodoroFocusTimerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PomodoroTimer()
                }
            }
        }
    }
}

@Composable
fun PomodoroTimer() {
    // 25 de minute transformate în secunde (25 * 60 = 1500)
    var timeLeft by remember { mutableStateOf(1500) }
    var isRunning by remember { mutableStateOf(false) }
    var sessions by remember { mutableStateOf(0) }

    // Coroutină care rulează cronometrul în timp ce isRunning este true
    LaunchedEffect(key1 = isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000L) // Pauză de o secundă
            timeLeft--
        }
        // Când ajunge la 0, se adaugă o sesiune și se resetează timerul
        if (timeLeft == 0 && isRunning) {
            isRunning = false
            sessions++
            timeLeft = 1500
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "🍅 Focus Vibe", fontSize = 28.sp, fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = String.format("%02d:%02d", minutes, seconds),
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Sesiuni finalizate azi: $sessions", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(48.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { isRunning = !isRunning },
                modifier = Modifier.height(56.dp)
            ) {
                Text(if (isRunning) "Pauză ⏸️" else "Start ▶️", fontSize = 18.sp)
            }

            OutlinedButton(
                onClick = {
                    isRunning = false
                    timeLeft = 1500
                },
                modifier = Modifier.height(56.dp)
            ) {
                Text("Reset 🔄", fontSize = 18.sp)
            }
        }
    }
}