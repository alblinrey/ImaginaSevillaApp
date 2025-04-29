package com.example.imaginasevillaapp.activities;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.imaginasevillaapp.R;

public class LlegadaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llegada);

        TextView texto = findViewById(R.id.textoLlegada);
        ImageView imagen = findViewById(R.id.imagenLlegada);

        //Texto que aparece en Cómo llegar a Sevilla con formato HTML.
        String contenido =

                "<h3>✈️ Por aire</h3>" +
                "<p>El <b>Aeropuerto de Sevilla-San Pablo</b> (SVQ) se encuentra a unos 10 km del centro de la ciudad. Está conectado con numerosas ciudades españolas y europeas. Puedes llegar al centro en transporte público (Bus Tussam EA), taxi, VTC o, también puede recoger tu coche de alquiler en la misma terminal.</p>" +

                "<h3>🚆 Por tren</h3>" +
                "<p>La estación <b>Santa Justa</b> es una de las principales de España y ofrece servicios de alta velocidad (AVE) con conexiones directas desde <i>Madrid, Barcelona, Málaga</i> y otras ciudades. Desde la estación puedes moverte fácilmente en taxi, autobús o a pie si tu alojamiento está cerca.</p>" +

                "<h3>🚗 Por carretera</h3>" +
                "<p>Sevilla está bien conectada por autopistas: la <b>A-4</b> desde Madrid, la <b>A-49</b> desde Huelva y Portugal, la <b>A-66</b> desde Extremadura y la <b>A-92</b> desde Granada y Málaga. Si vienes en coche, tienes la opción de aparcar en numerosos parkings públicos repartidos por toda la ciudad.</p>" +

                "<h3>🛳️ Por barco</h3>" +
                "<p>Gracias al <b>Puerto de Sevilla</b>, la ciudad también recibe cruceros turísticos que navegan por el río Guadalquivir. Es la única terminal de cruceros de interior en España, ubicada además practicamente en el centro de la ciudad.</p>";


        texto.setText(Html.fromHtml(contenido, Html.FROM_HTML_MODE_LEGACY)); //muestra texto con formato HTML dentro de un TextView definido en al activity.
        imagen.setImageResource(R.drawable.llegada_cabecera); // Imagen que sale en la cabecera.
    }
}
