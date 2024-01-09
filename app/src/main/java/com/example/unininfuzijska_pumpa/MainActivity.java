package com.example.unininfuzijska_pumpa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private double kolicina, vrijeme;
    private int tezina, visina;

    private EditText unosKolicinaInput;
    private EditText unosVrijemeInput;
    private EditText rezultat;
    private Button gumb1;
    private Button gumb2;
    private Button gumb3;
    private Button gumb4;
    private Button prikaziMeniGumb;

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public void setVrijeme(double vrijeme) {
        this.vrijeme = vrijeme;
    }

    public void setTezina(int tezina) {
        this.tezina = tezina;
    }

    public void setVisina(int visina) {
        this.visina = visina;
    }

    public double getKolicina() {
        return this.kolicina;
    }

    public double getVrijeme() {
        return this.vrijeme;
    }

    public int getTezina() {
        return this.tezina;
    }

    public int getVisina() {
        return this.visina;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unosKolicinaInput = findViewById(R.id.unosKolicinaInput);
        unosVrijemeInput = findViewById(R.id.unosVrijemeInput);
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
                unosKolicinaInput.getText().clear();
                rezultat.getText().clear();
                showToast("Unos je očišćen.");
            }
        });
        gumb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Očisti unose
                unosVrijemeInput.getText().clear();
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
                unosKolicinaInput.getText().clear();
                unosVrijemeInput.getText().clear();
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

        setTezina(tezina);
        setVisina(visina);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void funkcija() {
        try {
            if (unosKolicinaInput != null && unosVrijemeInput != null && rezultat != null) {
                setKolicina(Double.parseDouble(unosKolicinaInput.getText().toString()));
                setVrijeme(Double.parseDouble(unosVrijemeInput.getText().toString()));

                double v1 = getKolicina() / (2 * (getVrijeme() - 1));
                double v2 = 2 * v1;

                if (v1 > 100) {
                    showToast("Početna brzina je prevelika! Povećajte broj sati.");
                } else {
                    String poruka = "Ne zaboravite odspojiti pacijenta nakon " + String.format("%.2f", getVrijeme()) + " sati!";
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
