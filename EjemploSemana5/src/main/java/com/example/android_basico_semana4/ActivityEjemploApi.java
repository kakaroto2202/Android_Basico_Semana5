package com.example.android_basico_semana4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_basico_semana4.network.ApiManager;

public class ActivityEjemploApi extends AppCompatActivity {

    private ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejemplo_api);

        ///  Apis Free
        /// https://github.com/public-apis/public-apis?tab=readme-ov-file


        // Inicializar el ApiManager
        apiManager = new ApiManager(this);

        // Ejemplo de uso - llamar una API pública
        String urlEjemplo = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
                //"https://jsonplaceholder.typicode.com/posts/1";
        apiManager.hacerPeticionJSON(urlEjemplo);

        // O para obtener respuesta como String
        apiManager.hacerPeticionString(urlEjemplo);

    }
}