package com.example.unininfuzijska_pumpa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private EditText unos1;
    private EditText unos2;
    private EditText rezultat;
    private Button gumb1;
    private Button gumb2;
    private Button gumb3;
    private Button gumb4;
    private Button prikaziMeniGumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unos1 = findViewById(R.id.unos1);
        unos2 = findViewById(R.id.unos2);
        rezultat = findViewById(R.id.rezultat);
        gumb1 = findViewById(R.id.gumb1);
        gumb2 = findViewById(R.id.gumb2);
        gumb3 = findViewById(R.id.gumb3);
        gumb4 = findViewById(R.id.gumb4);

        prikaziMeniGumb = findViewById(R.id.prikaziMeniGumb);

        gumb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Očisti unose
                unos1.getText().clear();
                rezultat.getText().clear();
                showToast("Unos je očišćen.");
            }
        });
        gumb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Očisti unose
                unos2.getText().clear();
                rezultat.getText().clear();
                showToast("Unos je očišćen.");
            }
        });

        gumb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Izračunaj i postavi rezultat
                funkcija();
            }
        });

        gumb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Očisti unose i rezultat
                unos1.getText().clear();
                unos2.getText().clear();
                rezultat.getText().clear();
                showToast("Unos i rezultat su očišćeni.");
            }
        });

        prikaziMeniGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuFragment();
            }
        });
    }

    private void showMenuFragment() {
        // Create an instance of the MenuFragment
        MenuFragment menuFragment = MenuFragment.newInstance();
    
        // Set the onCloseListener in the main activity
        menuFragment.setOnCloseListener(new MenuFragment.OnCloseListener() {
            @Override
            public void onClose(int tezina, int visina) {
                onFragmentClose(tezina, visina);
            }
        });
    
        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
    
        // Begin the transaction
        fragmentManager.beginTransaction()
                .add(menuFragment, "menuFragment")
                .commit();
    }


    public void onFragmentClose(int tezina, int visina) {
        // Access the tezina and visina values here
        // You can perform any necessary operations with these values

        // Example: Display a toast with the values
        String message = "Tezina: " + tezina + ", Visina: " + visina;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void funkcija() {
        try {
            if (unos1 != null && unos2 != null && rezultat != null) {
                double V = Double.parseDouble(unos1.getText().toString());
                double t = Double.parseDouble(unos2.getText().toString());
                double v1 = V / (2 * (t - 1));
                double v2 = 2 * v1;

                if (v1 > 100) {
                    showToast("Početna brzina je prevelika! Povećajte broj sati.");
                } else {
                    String poruka = "Ne zaboravite odspojiti pacijenta nakon " + String.format("%.2f", t) + " sati!";
                    rezultat.setText("Protok prvog i zadnjeg sata iznosi: " + String.format("%.3f", v1)
                            + " ml/h.\nProtok srednjeg sata iznosi: " + String.format("%.3f", v2) + " ml/h.\n"
                            + poruka);
                    showToast("Izračunato!");
                }
            }
        } catch (NumberFormatException e) {
            showToast("Molimo unesite validne brojeve.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
