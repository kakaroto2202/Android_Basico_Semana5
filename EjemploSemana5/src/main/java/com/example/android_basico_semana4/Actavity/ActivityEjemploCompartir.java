package com.example.android_basico_semana4.Actavity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_basico_semana4.R;

public class ActivityEjemploCompartir extends AppCompatActivity {

    private EditText editTextTitulo;
    private EditText editTextContenido;
    private Button btnCompartirTexto;
    private Button btnCompartirUrl;
    private Button btnCompartirImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejemplo_compartir);

        // Inicializar vistas
        initViews();

        // Configurar listeners
        setupListeners();

    }

    private void initViews() {
        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextContenido = findViewById(R.id.editTextContenido);
        btnCompartirTexto = findViewById(R.id.btnCompartirTexto);
        btnCompartirUrl = findViewById(R.id.btnCompartirUrl);
        btnCompartirImagen = findViewById(R.id.btnCompartirImagen);
    }

    private void setupListeners() {
        btnCompartirTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartirTexto();
            }
        });

        btnCompartirUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartirUrl();
            }
        });

        btnCompartirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartirImagen();
            }
        });
    }

    /**
     * Método para compartir texto simple
     */
    private void compartirTexto() {
        String titulo = editTextTitulo.getText().toString();
        String contenido = editTextContenido.getText().toString();

        if (contenido.trim().isEmpty()) {
            Toast.makeText(this, "Por favor ingresa algo para compartir", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intentCompartir = new Intent(Intent.ACTION_SEND);
        intentCompartir.setType("text/plain");
        intentCompartir.putExtra(Intent.EXTRA_SUBJECT, titulo.isEmpty() ? "Compartido desde mi App" : titulo);
        intentCompartir.putExtra(Intent.EXTRA_TEXT, contenido);

        // Crear chooser para mostrar todas las opciones disponibles
        Intent chooser = Intent.createChooser(intentCompartir, "Compartir mediante:");

        if (intentCompartir.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(this, "No hay aplicaciones disponibles para compartir", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método para compartir URL/enlace
     */
    private void compartirUrl() {
        String titulo = editTextTitulo.getText().toString();
        String url = editTextContenido.getText().toString();

        if (url.trim().isEmpty()) {
            Toast.makeText(this, "Por favor ingresa una URL para compartir", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que sea una URL válida básicamente
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }

        Intent intentCompartir = new Intent(Intent.ACTION_SEND);
        intentCompartir.setType("text/plain");
        intentCompartir.putExtra(Intent.EXTRA_SUBJECT, titulo.isEmpty() ? "Mira este enlace" : titulo);
        intentCompartir.putExtra(Intent.EXTRA_TEXT, "Echa un vistazo a esto: " + url);

        Intent chooser = Intent.createChooser(intentCompartir, "Compartir enlace mediante:");
        startActivity(chooser);
    }


    /**
     * Método para compartir con aplicaciones específicas
     * Este ejemplo muestra cómo dirigir a aplicaciones específicas
     */
    private void compartirImagen() {
        String contenido = editTextContenido.getText().toString();

        if (contenido.trim().isEmpty()) {
            contenido = "¡Compartido desde mi aplicación!";
        }

        // Intent para compartir con aplicaciones que manejan imágenes y texto
        Intent intentCompartir = new Intent(Intent.ACTION_SEND);
        intentCompartir.setType("*/*"); // Acepta cualquier tipo de contenido
        intentCompartir.putExtra(Intent.EXTRA_TEXT, contenido);

        // Puedes agregar una imagen si tienes una URI
        // Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.imagen);
        // intentCompartir.putExtra(Intent.EXTRA_STREAM, imageUri);
        // intentCompartir.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Intent chooser = Intent.createChooser(intentCompartir, "Compartir mediante:");
        startActivity(chooser);
    }


    /**
     * Método adicional para compartir con configuraciones específicas de email
     */
    private void compartirPorEmail() {
        String titulo = editTextTitulo.getText().toString();
        String contenido = editTextContenido.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822"); // Específico para email
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, titulo.isEmpty() ? "Mensaje desde mi App" : titulo);
        emailIntent.putExtra(Intent.EXTRA_TEXT, contenido);

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email mediante:"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No hay aplicaciones de email instaladas", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método para compartir en redes sociales específicas
     */
    private void compartirEnRedesSociales() {
        String contenido = editTextContenido.getText().toString();

        Intent intentCompartir = new Intent(Intent.ACTION_SEND);
        intentCompartir.setType("text/plain");
        intentCompartir.putExtra(Intent.EXTRA_TEXT, contenido);

        // Filtrar solo aplicaciones de redes sociales comunes
        Intent chooser = Intent.createChooser(intentCompartir, "Compartir en redes sociales:");
        startActivity(chooser);
    }



}