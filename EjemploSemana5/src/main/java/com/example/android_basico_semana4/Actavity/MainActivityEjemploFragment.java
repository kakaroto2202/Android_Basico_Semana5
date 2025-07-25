package com.example.android_basico_semana4.Actavity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.android_basico_semana4.Fragment.FragmentOne;
import com.example.android_basico_semana4.Fragment.FragmentTwo;
import com.example.android_basico_semana4.R;

public class MainActivityEjemploFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_ejemplo_fragment);
      /*  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Button btnFrag1 = findViewById(R.id.btn_fragment1);
        Button btnFrag2 = findViewById(R.id.btn_fragment2);

        btnFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new FragmentOne(), 1);
            }
        });

        btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new FragmentTwo(), 2);
            }
        });

    }

    private void openFragment(Fragment fragment, int number) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
       if(number == 1) ft.replace(R.id.fragment_container, fragment);
       else
           ft.replace(R.id.fragment_container2, fragment);

        ft.commit();

        Toast.makeText(this, "Abriendo Fragment #" + number, Toast.LENGTH_SHORT).show();
    }


}