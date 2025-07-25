package com.example.android_basico_semana4.Provider;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "carros_db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CARROS = "carros";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FABRICANTE = "fabricante";
    public static final String COLUMN_MODELO = "modelo";
    public static final String COLUMN_ANIO = "anio";
    public static final String COLUMN_LT_GASOLINA = "lt_gasolina";

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    // SQL para crear la tabla
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_CARROS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FABRICANTE + " TEXT, " +
                    COLUMN_MODELO + " TEXT, " +
                    COLUMN_ANIO + " INTEGER, " +
                    COLUMN_LT_GASOLINA + " REAL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla
        db.execSQL(TABLE_CREATE);
        Log.d("DatabaseHelper", "Tabla creada");

        // Insertar los 30 registros
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Borrar tabla si existe y crearla de nuevo
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARROS);
        onCreate(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        // Array con datos de ejemplo
       /* String[][] carros = {
                {"Toyota", "Corolla", "2020", "40.5"},
                {"Honda", "Civic", "2019", "38.0"},
                {"Ford", "Focus", "2021", "42.3"},
                {"Chevrolet", "Cruze", "2018", "35.7"},
                {"Nissan", "Sentra", "2022", "37.2"},
                {"Hyundai", "Elantra", "2017", "39.5"},
                {"Kia", "Forte", "2020", "40.0"},
                {"Volkswagen", "Jetta", "2021", "41.1"},
                {"Mazda", "3", "2019", "36.8"},
                {"Subaru", "Impreza", "2018", "34.9"},
                {"BMW", "320i", "2020", "45.6"},
                {"Mercedes-Benz", "C300", "2019", "47.2"},
                {"Audi", "A4", "2021", "46.0"},
                {"Tesla", "Model 3", "2022", "50.0"},
                {"Volvo", "S60", "2017", "42.7"},
                {"Peugeot", "308", "2018", "33.8"},
                {"Renault", "Megane", "2019", "32.5"},
                {"Fiat", "Tipo", "2020", "34.0"},
                {"Opel", "Astra", "2021", "36.0"},
                {"Skoda", "Octavia", "2018", "38.0"},
                {"Mitsubishi", "Lancer", "2019", "37.5"},
                {"Suzuki", "Baleno", "2020", "35.0"},
                {"Seat", "Leon", "2021", "34.5"},
                {"Alfa Romeo", "Giulia", "2018", "43.5"},
                {"Jaguar", "XE", "2019", "44.0"},
                {"Lexus", "IS300", "2020", "46.5"},
                {"Infiniti", "Q50", "2021", "45.2"},
                {"Acura", "ILX", "2017", "39.9"},
                {"Cadillac", "ATS", "2018", "42.3"},
                {"Genesis", "G70", "2019", "43.8"}
        };*/

        String[][] carros = {
                // Toyota - múltiples modelos
                {"Toyota", "Corolla", "2020", "40.5"},
                {"Toyota", "Camry", "2021", "43.2"},
                {"Toyota", "RAV4", "2022", "41.8"},
                {"Toyota", "Prius", "2019", "52.0"},
                {"Toyota", "Highlander", "2020", "38.5"},
                {"Toyota", "Yaris", "2018", "36.0"},

                // Honda - múltiples modelos
                {"Honda", "Civic", "2019", "38.0"},
                {"Honda", "Accord", "2021", "42.7"},
                {"Honda", "CR-V", "2020", "40.2"},
                {"Honda", "Pilot", "2022", "37.5"},
                {"Honda", "Fit", "2018", "39.8"},
                {"Honda", "HR-V", "2019", "35.4"},

                // Ford - múltiples modelos
                {"Ford", "Focus", "2021", "42.3"},
                {"Ford", "Fusion", "2019", "41.5"},
                {"Ford", "Escape", "2020", "39.7"},
                {"Ford", "Explorer", "2021", "36.8"},
                {"Ford", "Mustang", "2022", "28.5"},
                {"Ford", "F-150", "2020", "25.3"},
                {"Ford", "Edge", "2018", "33.2"},

                // Chevrolet - múltiples modelos
                {"Chevrolet", "Cruze", "2018", "35.7"},
                {"Chevrolet", "Malibu", "2020", "38.9"},
                {"Chevrolet", "Equinox", "2021", "36.4"},
                {"Chevrolet", "Tahoe", "2019", "22.1"},
                {"Chevrolet", "Camaro", "2020", "24.8"},
                {"Chevrolet", "Silverado", "2021", "23.5"},
                {"Chevrolet", "Trax", "2018", "32.0"},

                // Nissan - múltiples modelos
                {"Nissan", "Sentra", "2022", "37.2"},
                {"Nissan", "Altima", "2020", "40.1"},
                {"Nissan", "Rogue", "2021", "35.8"},
                {"Nissan", "Pathfinder", "2019", "32.6"},
                {"Nissan", "370Z", "2018", "26.4"},
                {"Nissan", "Maxima", "2020", "34.7"},
                {"Nissan", "Kicks", "2021", "36.9"},

                // Hyundai - múltiples modelos
                {"Hyundai", "Elantra", "2017", "39.5"},
                {"Hyundai", "Sonata", "2021", "41.3"},
                {"Hyundai", "Tucson", "2020", "37.8"},
                {"Hyundai", "Santa Fe", "2019", "34.2"},
                {"Hyundai", "Veloster", "2018", "30.5"},
                {"Hyundai", "Genesis G90", "2022", "28.9"},

                // Kia - múltiples modelos
                {"Kia", "Forte", "2020", "40.0"},
                {"Kia", "Optima", "2019", "39.1"},
                {"Kia", "Sportage", "2021", "35.6"},
                {"Kia", "Sorento", "2020", "33.4"},
                {"Kia", "Stinger", "2018", "27.8"},
                {"Kia", "Soul", "2019", "33.9"},

                // Volkswagen - múltiples modelos
                {"Volkswagen", "Jetta", "2021", "41.1"},
                {"Volkswagen", "Passat", "2020", "38.7"},
                {"Volkswagen", "Tiguan", "2019", "35.2"},
                {"Volkswagen", "Atlas", "2021", "29.8"},
                {"Volkswagen", "Golf", "2018", "37.4"},
                {"Volkswagen", "Beetle", "2017", "31.2"},

                // Mazda - múltiples modelos
                {"Mazda", "3", "2019", "36.8"},
                {"Mazda", "6", "2020", "38.4"},
                {"Mazda", "CX-5", "2021", "34.9"},
                {"Mazda", "CX-9", "2018", "28.7"},
                {"Mazda", "MX-5 Miata", "2019", "32.1"},
                {"Mazda", "CX-30", "2022", "36.2"},

                // Subaru - múltiples modelos
                {"Subaru", "Impreza", "2018", "34.9"},
                {"Subaru", "Legacy", "2021", "37.6"},
                {"Subaru", "Outback", "2020", "35.1"},
                {"Subaru", "Forester", "2019", "32.8"},
                {"Subaru", "Crosstrek", "2021", "34.3"},
                {"Subaru", "WRX", "2018", "26.9"},

                // BMW - múltiples modelos
                {"BMW", "320i", "2020", "45.6"},
                {"BMW", "330i", "2021", "42.8"},
                {"BMW", "X3", "2019", "38.2"},
                {"BMW", "X5", "2020", "34.5"},
                {"BMW", "M3", "2018", "28.3"},
                {"BMW", "i3", "2021", "118.0"},
                {"BMW", "540i", "2019", "36.7"},

                // Mercedes-Benz - múltiples modelos
                {"Mercedes-Benz", "C300", "2019", "47.2"},
                {"Mercedes-Benz", "E350", "2021", "43.9"},
                {"Mercedes-Benz", "GLC300", "2020", "39.1"},
                {"Mercedes-Benz", "GLE350", "2018", "35.6"},
                {"Mercedes-Benz", "A220", "2019", "41.7"},
                {"Mercedes-Benz", "S560", "2020", "28.4"},

                // Audi - múltiples modelos
                {"Audi", "A4", "2021", "46.0"},
                {"Audi", "A6", "2020", "42.5"},
                {"Audi", "Q5", "2019", "37.8"},
                {"Audi", "Q7", "2021", "33.2"},
                {"Audi", "TT", "2018", "29.6"},
                {"Audi", "A3", "2020", "39.4"},

                // Tesla - múltiples modelos
                {"Tesla", "Model 3", "2022", "50.0"},
                {"Tesla", "Model S", "2021", "48.7"},
                {"Tesla", "Model X", "2020", "45.2"},
                {"Tesla", "Model Y", "2022", "47.3"},

                // Volvo - múltiples modelos
                {"Volvo", "S60", "2017", "42.7"},
                {"Volvo", "XC60", "2020", "38.9"},
                {"Volvo", "XC90", "2021", "35.4"},
                {"Volvo", "S90", "2019", "40.1"},

                // Nuevas marcas con múltiples modelos

                // Peugeot
                {"Peugeot", "308", "2018", "33.8"},
                {"Peugeot", "508", "2020", "36.2"},
                {"Peugeot", "2008", "2021", "34.7"},
                {"Peugeot", "3008", "2019", "32.5"},

                // Renault
                {"Renault", "Megane", "2019", "32.5"},
                {"Renault", "Clio", "2021", "35.4"},
                {"Renault", "Kadjar", "2020", "31.8"},
                {"Renault", "Talisman", "2018", "33.9"},

                // Fiat
                {"Fiat", "Tipo", "2020", "34.0"},
                {"Fiat", "500", "2019", "38.2"},
                {"Fiat", "Panda", "2021", "36.7"},
                {"Fiat", "500X", "2018", "32.1"},

                // Opel
                {"Opel", "Astra", "2021", "36.0"},
                {"Opel", "Corsa", "2020", "37.8"},
                {"Opel", "Insignia", "2019", "34.3"},
                {"Opel", "Crossland", "2018", "33.2"},

                // Skoda
                {"Skoda", "Octavia", "2018", "38.0"},
                {"Skoda", "Superb", "2021", "40.5"},
                {"Skoda", "Karoq", "2020", "35.7"},
                {"Skoda", "Kodiaq", "2019", "32.4"},

                // Marcas de lujo adicionales

                // Mitsubishi
                {"Mitsubishi", "Lancer", "2019", "37.5"},
                {"Mitsubishi", "Outlander", "2021", "33.8"},
                {"Mitsubishi", "Eclipse Cross", "2020", "31.6"},
                {"Mitsubishi", "Mirage", "2018", "39.2"},

                // Suzuki
                {"Suzuki", "Baleno", "2020", "35.0"},
                {"Suzuki", "Swift", "2021", "37.1"},
                {"Suzuki", "Vitara", "2019", "33.4"},
                {"Suzuki", "Jimny", "2018", "28.9"},

                // Seat
                {"Seat", "Leon", "2021", "34.5"},
                {"Seat", "Ibiza", "2020", "36.8"},
                {"Seat", "Ateca", "2019", "32.7"},
                {"Seat", "Tarraco", "2018", "30.1"},

                // Alfa Romeo
                {"Alfa Romeo", "Giulia", "2018", "43.5"},
                {"Alfa Romeo", "Stelvio", "2020", "38.9"},
                {"Alfa Romeo", "Giulietta", "2019", "35.6"},

                // Jaguar
                {"Jaguar", "XE", "2019", "44.0"},
                {"Jaguar", "XF", "2021", "41.3"},
                {"Jaguar", "F-PACE", "2020", "36.8"},
                {"Jaguar", "E-PACE", "2018", "34.2"},

                // Lexus
                {"Lexus", "IS300", "2020", "46.5"},
                {"Lexus", "ES350", "2021", "44.2"},
                {"Lexus", "RX350", "2019", "39.7"},
                {"Lexus", "NX300", "2020", "37.8"},

                // Infiniti
                {"Infiniti", "Q50", "2021", "45.2"},
                {"Infiniti", "Q60", "2019", "38.4"},
                {"Infiniti", "QX50", "2020", "36.1"},
                {"Infiniti", "QX60", "2018", "32.9"},

                // Acura
                {"Acura", "ILX", "2017", "39.9"},
                {"Acura", "TLX", "2020", "41.6"},
                {"Acura", "RDX", "2021", "37.4"},
                {"Acura", "MDX", "2019", "33.8"},

                // Cadillac
                {"Cadillac", "ATS", "2018", "42.3"},
                {"Cadillac", "CTS", "2020", "38.7"},
                {"Cadillac", "XT5", "2021", "35.2"},
                {"Cadillac", "Escalade", "2019", "26.4"},

                // Genesis
                {"Genesis", "G70", "2019", "43.8"},
                {"Genesis", "G80", "2021", "40.5"},
                {"Genesis", "G90", "2020", "37.1"},
                {"Genesis", "GV70", "2022", "36.8"},

                // Marcas adicionales

                // Jeep
                {"Jeep", "Wrangler", "2021", "25.8"},
                {"Jeep", "Cherokee", "2020", "32.4"},
                {"Jeep", "Grand Cherokee", "2019", "29.6"},
                {"Jeep", "Compass", "2021", "33.7"},
                {"Jeep", "Renegade", "2018", "30.9"},

                // Dodge
                {"Dodge", "Charger", "2020", "27.3"},
                {"Dodge", "Challenger", "2019", "25.1"},
                {"Dodge", "Durango", "2021", "26.8"},
                {"Dodge", "Journey", "2018", "28.4"},

                // Chrysler
                {"Chrysler", "300", "2020", "29.7"},
                {"Chrysler", "Pacifica", "2021", "32.1"},

                // Buick
                {"Buick", "Encore", "2019", "33.6"},
                {"Buick", "Envision", "2020", "31.8"},
                {"Buick", "Enclave", "2021", "27.9"},

                // GMC
                {"GMC", "Terrain", "2020", "30.4"},
                {"GMC", "Acadia", "2019", "28.7"},
                {"GMC", "Sierra", "2021", "24.2"},

                // Lincoln
                {"Lincoln", "MKZ", "2018", "35.8"},
                {"Lincoln", "Corsair", "2021", "33.4"},
                {"Lincoln", "Navigator", "2020", "22.6"},

                // Porsche
                {"Porsche", "Cayenne", "2021", "22.5"},
                {"Porsche", "Macan", "2020", "24.8"},
                {"Porsche", "911", "2019", "23.1"},
                {"Porsche", "Panamera", "2018", "25.3"},

                // Land Rover
                {"Land Rover", "Discovery Sport", "2020", "26.9"},
                {"Land Rover", "Range Rover Evoque", "2021", "28.1"},
                {"Land Rover", "Range Rover", "2019", "22.4"},

                // Mini
                {"Mini", "Cooper", "2020", "34.2"},
                {"Mini", "Countryman", "2021", "30.8"},
                {"Mini", "Clubman", "2019", "32.1"}
        };







        // Insertar cada registro
        for (String[] carro : carros) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_FABRICANTE, carro[0]);
            values.put(COLUMN_MODELO, carro[1]);
            values.put(COLUMN_ANIO, Integer.parseInt(carro[2]));
            values.put(COLUMN_LT_GASOLINA, Double.parseDouble(carro[3]));

            db.insert(TABLE_CARROS, null, values);
        }
        Log.d("DatabaseHelper", "30 carros insertados en la base de datos");
    }


}
