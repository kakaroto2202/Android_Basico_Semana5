package com.example.android_basico_semana4;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Utils {

    public static void GuardarConfiguracion(Context context,String Contenido,String NombreArchivo){

        try
        {
            OutputStreamWriter fout=new OutputStreamWriter(context.openFileOutput(NombreArchivo, Context.MODE_PRIVATE));

            fout.write(Contenido);
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }

    }

    public static String GetConfiguracion(Context context,String NombreArchivo){
        String texto="";
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    context.openFileInput(NombreArchivo)));

            texto = fin.readLine();
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }
        String Final = "";
        try{
            Final = texto.toString();
        }
        catch (Exception e)
        {
            Final = "";
        }

        return Final;
    }
}
