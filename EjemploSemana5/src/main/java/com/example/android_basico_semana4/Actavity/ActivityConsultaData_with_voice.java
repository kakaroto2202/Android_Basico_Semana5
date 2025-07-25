package com.example.android_basico_semana4.Actavity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.android_basico_semana4.Adapter.CarroAdapter;
import com.example.android_basico_semana4.Provider.Library;
import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivityConsultaData_with_voice extends AppCompatActivity implements RecognitionListener,TextToSpeech.OnInitListener {

    private EditText etSearchText;
    private Button btnSearch;
    private ListView lvResults;
    private ProgressBar progressBar;
    private CarroAdapter adapter;
    private List<Carro> carroList;

    private FloatingActionButton floatingActionButton;

    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private boolean isListening = false;

    private TextToSpeech tts;
    private boolean isTTSInitialized = false;

    private static final int REQUEST_RECORD_PERMISSION = 100;

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
                    Toast.makeText(ActivityConsultaData_with_voice.this, "Por favor ingresa un fabricante para buscar",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Implementar el onClick
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tu código aquí
                //Toast.makeText(ActivityConsultaData_with_voice.this, "FloatingActionButton clickeado!", Toast.LENGTH_SHORT).show();

                if (isListening) {
                    stopListening();
                } else {
                    startListening();
                    ///speakText("Por Favor decir el criterio de Busqueda ");
                }
            }
        });

        setupSpeechRecognizer();
        checkPermissions();

        // Inicializar Text-to-Speech
        tts = new TextToSpeech(this, this);

        updateSpeedText(85);
        updatePitchText(100);


    }

    private void setupSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);

        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_PERMISSION);
        }
    }

    private void startListening() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permiso de micrófono necesario", Toast.LENGTH_SHORT).show();
            return;
        }

        etSearchText.setText("Escuchando...");
       // btnSpeak.setText("Detener");
        isListening = true;
        speechRecognizer.startListening(speechRecognizerIntent);
    }

    private void stopListening() {
       // btnSpeak.setText("Hablar");
        isListening = false;
        speechRecognizer.stopListening();
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

    @Override
    public void onReadyForSpeech(Bundle params) {

        etSearchText.setText("Listo para escuchar...");

    }

    @Override
    public void onBeginningOfSpeech() {

        etSearchText.setText("Escuchando...");

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

       // btnSpeak.setText("Hablar");
        isListening = false;
        etSearchText.setText("Procesando...");
    }

    @Override
    public void onError(int error) {

       // btnSpeak.setText("Hablar");
        isListening = false;

        String errorMessage = getErrorText(error);
        etSearchText.setText("Error: " + errorMessage);
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResults(Bundle results) {

        //btnSpeak.setText("Hablar");
        isListening = false;

        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (matches != null && !matches.isEmpty()) {
            String recognizedText = matches.get(0);
            etSearchText.setText(recognizedText);

            // Aquí puedes procesar el texto reconocido
            processRecognizedText(recognizedText);
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

       /* ArrayList<String> matches = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (matches != null && !matches.isEmpty()) {
            etSearchText.setText("Parcial: " + matches.get(0));
        }*/
    }

    @Override
    public void onSegmentResults(@NonNull Bundle segmentResults) {
        RecognitionListener.super.onSegmentResults(segmentResults);
    }

    @Override
    public void onEndOfSegmentedSession() {
        RecognitionListener.super.onEndOfSegmentedSession();
    }

    @Override
    public void onLanguageDetection(@NonNull Bundle results) {
        RecognitionListener.super.onLanguageDetection(results);
    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Configurar idioma español
            int result = tts.setLanguage(new Locale("es", "ES"));

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Si español no está disponible, usar inglés
                tts.setLanguage(Locale.US);
                Toast.makeText(this, "Idioma español no disponible, usando inglés",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Text-to-Speech inicializado correctamente",
                        Toast.LENGTH_SHORT).show();
            }

            // Configurar velocidad y tono inicial
            tts.setSpeechRate(1.0f);
            tts.setPitch(1.0f);

            isTTSInitialized = true;


        } else {
            Toast.makeText(this, "Error al inicializar Text-to-Speech",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void speakText(String Texto) {
        String text = Texto;

        if (text.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese texto para leer",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isTTSInitialized) {
            Toast.makeText(this, "Text-to-Speech no está listo",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Hablar el texto
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void stopSpeaking() {
        if (tts != null && isTTSInitialized) {
            tts.stop();
        }
    }

    private void updateSpeedText(int progress) {
        float speed = progress / 100.0f;

    }

    private void updatePitchText(int progress) {
        float pitch = progress / 100.0f;
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
                adapter = new CarroAdapter(ActivityConsultaData_with_voice.this, carroList);
                lvResults.setAdapter(adapter);

                Toast.makeText(ActivityConsultaData_with_voice.this,
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
                Toast.makeText(ActivityConsultaData_with_voice.this,
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

        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }

        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isListening) {
            stopListening();
        }
    }


    private String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Error de audio";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Error del cliente";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Permisos insuficientes";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Error de red";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Tiempo de espera de red agotado";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No se encontró coincidencia";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "Reconocedor ocupado";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "Error del servidor";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "Tiempo de espera de habla agotado";
                break;
            default:
                message = "Error desconocido";
                break;
        }
        return message;
    }

    private void processRecognizedText(String text) {
        // Aquí puedes implementar la lógica para procesar el texto reconocido
        // Por ejemplo: comandos de voz, búsquedas, etc.

        // Ejemplo de comandos simples
      /*  String lowerText = text.toLowerCase();
        if (lowerText.contains("hola")) {
            Toast.makeText(this, "¡Hola! ¿Cómo estás?", Toast.LENGTH_SHORT).show();
        } else if (lowerText.contains("adiós")) {
            Toast.makeText(this, "¡Hasta luego!", Toast.LENGTH_SHORT).show();
        }*/

        String params1 = "0";
        String params2 = "0";
        String params3 = "0";

        /// modelo nombre
        ///  fabricante nombre


        String lowerText = text.toLowerCase();
        if(lowerText.contains("todos")) params1 = "1";
        if(lowerText.contains("fabricante")) params2 = "2";
        if(lowerText.contains("modelo")) params3 = "2";

        if(params1.equals("1") && params2.equals("2")){

            if(lowerText.contains("nombre"))
            {

            }
            else
            {
                carroList = Library.getAllCarrosFromDB(getApplicationContext());
                adapter = new CarroAdapter(ActivityConsultaData_with_voice.this, carroList);
                lvResults.setAdapter(adapter);

                speakText("Se Encontaron "+carroList.size()+", fabricante; de la consulta Realizada");
                //stopSpeaking();



            }
        }



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_RECORD_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso de micrófono es necesario para usar esta función",
                        Toast.LENGTH_LONG).show();
            }
        }
    }


}