package com.example.android_basico_semana4.Actavity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_basico_semana4.Adapter.CarroAdapter;
import com.example.android_basico_semana4.Provider.Library;
import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ActivityConsultaData extends AppCompatActivity {

    private EditText etSearchText;
    private Button btnSearch;
    private ListView lvResults;
    private ProgressBar progressBar;
    private CarroAdapter adapter;
    private List<Carro> carroList;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consulta_data);

        initViews();

        // Configurar listener del botón
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = etSearchText.getText().toString().trim();
                if (!searchText.isEmpty()) {
                    new SearchCarrosTask().execute(searchText);
                } else {
                    Toast.makeText(ActivityConsultaData.this, "Por favor ingresa un fabricante para buscar",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Implementar el onClick
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tu código aquí
                Toast.makeText(ActivityConsultaData.this, "FloatingActionButton clickeado!", Toast.LENGTH_SHORT).show();

                // Ejemplo: abrir nueva activity
                // Intent intent = new Intent(MainActivity.this, NuevaActivity.class);
                // startActivity(intent);

                // Ejemplo: mostrar diálogo
                // showDialog();
            }
        });


    }

    private void initViews() {

        // Referenciar el FloatingActionButton
        floatingActionButton = findViewById(R.id.floatingActionButton);

        etSearchText = findViewById(R.id.et_search_text);
        btnSearch = findViewById(R.id.btn_search);
        lvResults = findViewById(R.id.lv_results);
        progressBar = findViewById(R.id.progress_bar);

        // Ocultar progress bar inicialmente
        progressBar.setVisibility(View.GONE);
    }

    // AsyncTask para realizar la búsqueda en segundo plano
    private class SearchCarrosTask extends AsyncTask<String, Void, List<Carro>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Mostrar progress bar y deshabilitar botón
            progressBar.setVisibility(View.VISIBLE);
            btnSearch.setEnabled(false);
            btnSearch.setText("Buscando...");
        }

        @Override
        protected List<Carro> doInBackground(String... params) {
            String searchTerm = params[0];

            // Simular un poco de delay para mostrar el progress bar
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Realizar búsqueda en la base de datos
            return Library.searchCarros(getApplicationContext(), searchTerm);
        }

        @Override
        protected void onPostExecute(List<Carro> results) {
            super.onPostExecute(results);

            // Ocultar progress bar y rehabilitar botón
            progressBar.setVisibility(View.GONE);
            btnSearch.setEnabled(true);
            btnSearch.setText("Buscar");

            // Actualizar la lista y ListView con los resultados
            if (results != null && !results.isEmpty()) {
                carroList = results;

                // Convertir List<Carro> a List<String> para el ArrayAdapter
              /*  List<Carro> displayList = new ArrayList<>();
                for (Carro carro : carroList) {
                    displayList.add(carro);
                }*/

               // adapter = new ArrayAdapter<>(ActivityConsultaData.this, android.R.layout.simple_list_item_1, displayList);
                adapter = new CarroAdapter(ActivityConsultaData.this, carroList);
                lvResults.setAdapter(adapter);

                Toast.makeText(ActivityConsultaData.this,
                        "Se encontraron " + results.size() + " carros",
                        Toast.LENGTH_SHORT).show();

                // Opcional: Imprimir información detallada en log
                for (Carro carro : carroList) {
                    System.out.println("Carro encontrado: " + carro.getFabricante() + " " +
                            carro.getModelo() + " (" + carro.getAnio() + ") - " +
                            carro.getLtGasolina() + " litros");
                }
            } else {
                // Limpiar ListView y lista si no hay resultados
                carroList.clear();
                lvResults.setAdapter(null);
                Toast.makeText(ActivityConsultaData.this,
                        "No se encontraron carros para el fabricante especificado",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Método para obtener la lista actual de carros (útil para otras operaciones)
    public List<Carro> getCarroList() {
        return carroList;
    }

    // Método para obtener un carro específico por posición en la lista
    public Carro getCarroAt(int position) {
        if (position >= 0 && position < carroList.size()) {
            return carroList.get(position);
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}