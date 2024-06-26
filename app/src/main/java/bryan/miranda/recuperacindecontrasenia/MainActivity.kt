package bryan.miranda.recuperacindecontrasenia

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEnviar = findViewById<Button>(R.id.btnEnviar)


        btnEnviar.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val codigoRecuperacion = (1000..9999).random()

                enviarCorreo("Ricardo.profe1999@gmail.com", "Código de recuperación", "No olvide su contraseña, este es su código de recuperación: $codigoRecuperacion")

                Toast.makeText(this@MainActivity, "El código se envió correctamente :D", Toast.LENGTH_SHORT).show()
            }
        }

    }
}