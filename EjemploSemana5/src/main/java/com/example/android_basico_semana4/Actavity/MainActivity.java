package com.example.android_basico_semana4.Actavity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_basico_semana4.ActivityEjemploApi;
import com.example.android_basico_semana4.Provider.Library;
import com.example.android_basico_semana4.R;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button btnCargar;
    String html;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        webView = findViewById(R.id.webView);
        btnCargar = findViewById(R.id.btnCargarHtml);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        /*btnCargar.setOnClickListener(v -> {

        });*/

       /* Intent intent2 = new Intent(this, MainActivity2.class);
        //startActivity(intent2);

        Intent MainActivityGridView = new Intent(this, com.example.android_basico_semana4.Actavity.MainActivityGridView.class);
        startActivity(MainActivityGridView);

        Intent MainActivityRecycleView = new Intent(this, com.example.android_basico_semana4.Actavity.MainActivityRecycleView.class);
        //startActivity(MainActivityRecycleView);

        Intent MainActivityEjemploFragment = new Intent(this, com.example.android_basico_semana4.Actavity.MainActivityEjemploFragment.class);
        //startActivity(MainActivityEjemploFragment);

        Intent MainActivity3 = new Intent(this, com.example.android_basico_semana4.Actavity.MainActivity3.class);
       //startActivity(MainActivity3);


        Intent ActivityEjemplosDialogos = new Intent(this, com.example.android_basico_semana4.Actavity.ActivityEjemplosDialogos.class);
        //startActivity(ActivityEjemplosDialogos);


        Intent ActivityEjemploSpinner = new Intent(this, com.example.android_basico_semana4.Actavity.ActivityEjemploSpinner.class);
        //startActivity(ActivityEjemploSpinner);

       //LoadHTMl();*/

        Intent MainActivityGridView = new Intent(this, com.example.android_basico_semana4.Actavity.MainActivityGridView.class);
        //startActivity(MainActivityGridView);

        Intent ActivityEjemplosharedPreferences = new Intent(this, ActivityEjemplosharedPreferences.class);
       // startActivity(ActivityEjemplosharedPreferences);

        Intent ActivityConsultaData = new Intent(this, ActivityConsultaData.class);
        //startActivity(ActivityConsultaData);

        Intent ActivityConsultaData_with_voice = new Intent(this, ActivityConsultaData_with_voice.class);
       // startActivity(ActivityConsultaData_with_voice);

        Intent activityEjemploApi = new Intent(this, ActivityEjemploApi.class);
        //startActivity(activityEjemploApi);

        Intent ActivityEjemploCompartir = new Intent(this, ActivityEjemploCompartir.class);
        //startActivity(ActivityEjemploCompartir);


        Intent ActivityEjemploNotificacion = new Intent(this, ActivityEjemploNotificacion.class);
       // startActivity(ActivityEjemploNotificacion);



    }

    public void LoadHTMl(){
        html = Library.LoadData(getApplicationContext(),"DataHtml.html");

                    /*"<p style=\"text-align: center;\"><strong>Grupo 1</strong></p>\n" +
                    "<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td style=\"width: 100%;\">Maria</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td style=\"width: 100%;\">pedro</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<p style=\"text-align: center;\"><strong>Grupo 2</strong></p>\n" +
                    "<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td style=\"width: 100%;\">jose&nbsp;</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td style=\"width: 100%;\">cristian</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<p>";*/
        //webView.loadData(html, "text/html", "UTF-8");
        webView.loadDataWithBaseURL(null,html, "text/html", "UTF-8", "");
    }
}