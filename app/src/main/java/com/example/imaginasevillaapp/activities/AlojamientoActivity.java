package com.example.imaginasevillaapp.activities;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.imaginasevillaapp.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class AlojamientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargar configuración de osmdroid para poder ver las zonas en el mapa.
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        setContentView(R.layout.activity_alojamiento);

        //Texto que aparece con el formato dado en el activity_alojamiento.xml.
        TextView texto = findViewById(R.id.textoAlojamiento);

        //Tengo en HTML que es el que aparecerá en el apartado de alojamiento.
        String contenido =
                "<h3>🏨 Mejores zonas para alojarse en Sevilla</h3>" +
                        "<p>Existe una amplia oferta hotelera repartida por la ciudad que se ajusta a todo tipo de bolsillos, habiendo desde hoteles de lujo, hasta hostales de habitaciones compartidas. Solo tienes que elegir lo que mejor se ajuste a tus preferencias.</p>" +

                        "<h5>📍 Las áreas más recomendables para alojarse son:</h5>" +

                        "<h4>1. Casco Antiguo</h4>" +
                        "<p>Ideal para quienes quieren estar cerca de todos los monumentos históricos. Estás a pasos de la Catedral, la Giralda y el Alcázar.</p>" +

                        "<h4>2. Triana</h4>" +
                        "<p>Barrio tradicional al 'otro lado del río', famoso por el flamenco y la cerámica.</p>" +

                        "<h4>3. Nervión</h4>" +
                        "<p>Zona moderna y tranquila con buenos hoteles y centros comerciales.</p>" +

                        "<h4>4. La Alameda</h4>" +
                        "<p>Perfecto si buscas vida nocturna y un ambiente alternativo.</p>" +

                        "<p>No importa dónde te alojes, Sevilla es una ciudad acogedora y bien conectada. ¡Disfruta tu estancia!</p>";

        texto.setText(Html.fromHtml(contenido, Html.FROM_HTML_MODE_LEGACY));

        // Configurar el mapa
        MapView map = findViewById(R.id.map);
        map.setMultiTouchControls(true);
        map.getController().setZoom(13.0);
        map.getController().setCenter(new GeoPoint(37.3891, -5.9845)); // Centro de Sevilla

        // Marcadores con las coordenadas de zonas descritas en el texto.
        addMarker(map, 37.3861, -5.9924, "Casco Antiguo");
        addMarker(map, 37.3826, -6.0038, "Triana");
        addMarker(map, 37.3833, -5.9715, "Nervión");
        addMarker(map, 37.4011, -5.9939, "La Alameda");
    }

    private void addMarker(MapView map, double lat, double lon, String title) {
        Marker marker = new Marker(map);
        marker.setPosition(new GeoPoint(lat, lon));
        marker.setTitle(title);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(marker);
    }
}
