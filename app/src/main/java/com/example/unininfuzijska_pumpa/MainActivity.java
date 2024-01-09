package com.example.unininfuzijska_pumpa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText unos1;
    private EditText unos2;
    private EditText rezultat;
    private Button gumb1;
    private Button gumb2;
    private Button gumb3;
    private Button gumb4;

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
                    rezultat.setText("Protok prvog i zadnjeg sata iznosi: " + String.format("%.3f", v1) + " ml/h.\nProtok srednjeg sata iznosi: " + String.format("%.3f", v2) + " ml/h.\n" + poruka);
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
