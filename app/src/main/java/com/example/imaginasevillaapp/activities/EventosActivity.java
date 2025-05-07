package com.example.imaginasevillaapp.activities;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imaginasevillaapp.R;

public class EventosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        TextView texto = findViewById(R.id.textoEventos);
        ImageView imagen = findViewById(R.id.imagenEventos);

        // Contenido con los principales eventos de Sevilla.
        String contenido =

                "<h3>🌿 Semana Santa (marzo/abril)</h3>" +
                        "<p>Una de las celebraciones más importantes de España. Más de 60 hermandades recorren las calles con pasos procesionales teniendo que recorres desde sus templos hasta la Catedral, " +
                        "haciendo estación de penitendia de ida y vuelta. Procesionan imágenes de un enorme valor artístico e histórico, como son El Gran Poder (Juan de Mesa,1620), o el Cristo de la Veracruz,siendo " +
                        "este el más antiguo que procesiona actualmente (Anónimo, 1550).</p>" +

                "<h3>🎡 Feria de Abril (abril/mayo)</h3>" +
                        "<p>Durante una semana, Sevilla se transforma en un escenario de luz, color y alegría. El recinto ferial se llena de casetas, farolillos y sevillanas. Mujeres con trajes de flamenca, " +
                        "caballos engalanados y espectáculos improvisados hacen de la feria una experiencia única donde tradición y fiesta se dan la mano. " +
                        "El Real de la Feria se encuentra en el barrio de los Remedios, estando la portada en el final de la calle Asunción </p>" +


                "<h3>✝️ Corpus Christi (junio)</h3>" +
                        "<p>Una de las celebraciones más solemnes de Sevilla. La ciudad se engalana con altares, alfombras florales y balcones decorados para acompañar la procesión del Santísimo por las calles del centro. " +
                        "Es una fiesta donde arte, fe y tradición se mezclan en un ambiente sereno y majestuoso.</p>" +

                "<h3>🎶 Icónica Sevilla Fest (junio/septiembre)</h3>" +
                        "<p>Festival de música en la Plaza de España que reúne artistas nacionales e internacionales de primer nivel en un entorno único. Se celebra entre finales de primavera y verano." +
                        "Se puede escuchar todo tipo de música, desde electrónica, rock o música latina.</p>";

        texto.setText(Html.fromHtml(contenido, Html.FROM_HTML_MODE_LEGACY));
        imagen.setImageResource(R.drawable.collage_eventos); // Imagen destacada de los eventos
    }
}
