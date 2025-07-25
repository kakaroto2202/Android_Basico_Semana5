package com.example.android_basico_semana4.Actavity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_basico_semana4.R;

public class MainActivity3 extends AppCompatActivity {

    private EditText etImageName;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        etImageName = findViewById(R.id.etImageName);
        ivImage     = findViewById(R.id.ivImage);
        Button btnLoad = findViewById(R.id.btnLoad);

        btnLoad.setOnClickListener(v -> {
            String nombre = etImageName.getText()
                    .toString()
                    .trim()
                    .toLowerCase();
            cargarImagenPorNombre(nombre);
        });


    }

    private void cargarImagenPorNombre(String imageName) {
        if (imageName.isEmpty()) {
            Toast.makeText(this, "Introduce un nombre de imagen", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtiene el identificador: R.drawable.imageName
        int resId = getResources().getIdentifier(
                imageName,
                "drawable",
                getPackageName()
        );

        if (resId != 0) {
            ivImage.setImageResource(resId);
        } else {
            Toast.makeText(this,
                    "Imagen \"" + imageName + "\" no encontrada",
                    Toast.LENGTH_SHORT).show();
        }
    }
}