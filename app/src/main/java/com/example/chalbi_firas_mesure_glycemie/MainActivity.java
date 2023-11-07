package com.example.chalbi_firas_mesure_glycemie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etValeur;
    private Button btnConsulter;
    private SeekBar sbAge;
    private RadioButton rbIsFasting, rbIsNotFasting;
    private TextView tvAge, tvResultat;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        // Set listener for the SeekBar
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAge.setText("Votre âge = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Set click listener for the "CONSULTER" button
        btnConsulter.setOnClickListener(this::calculer);
    }

    public void calculer(View v) {
        int age;
        float valeurMesure;
        boolean verifAge = false;
        boolean verifValeur = false;

        if (sbAge.getProgress() != 0) {
            verifAge = true;
        } else {
            Toast.makeText(MainActivity.this, "Veuillez vérifier votre âge", Toast.LENGTH_SHORT).show();
        }

        if (!etValeur.getText().toString().isEmpty()) {
            try {
                valeurMesure = Float.parseFloat(etValeur.getText().toString());
                verifValeur = true;
            } catch (NumberFormatException e) {
                verifValeur = false;
                Toast.makeText(MainActivity.this, "Veuillez vérifier la valeur mesurée", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Veuillez vérifier la valeur mesurée", Toast.LENGTH_LONG).show();
        }

        if (verifAge && verifValeur) {
            age = sbAge.getProgress();
            valeurMesure = Float.parseFloat(etValeur.getText().toString());
            boolean isFasting = rbIsFasting.isChecked();

            if (isFasting) {
                if (age >= 13) {
                    if (valeurMesure < 5)
                        tvResultat.setText("Le niveau de glycémie est bas");
                    else if (valeurMesure >= 5 && valeurMesure <= 7)
                        tvResultat.setText("Le niveau de glycémie est normal");
                    else
                        tvResultat.setText("Le niveau de glycémie est élevé");
                } else if (age >= 6 && age <= 12) {
                    if (valeurMesure < 5)
                        tvResultat.setText("Le niveau de glycémie est bas");
                    else if (valeurMesure >= 5 && valeurMesure <= 10)
                        tvResultat.setText("Le niveau de glycémie est normal");
                    else
                        tvResultat.setText("Le niveau de glycémie est élevé");
                } else if (age < 6) {
                    if (valeurMesure < 5.5)
                        tvResultat.setText("Le niveau de glycémie est bas");
                    else if (valeurMesure >= 5.5 && valeurMesure <= 10)
                        tvResultat.setText("Le niveau de glycémie est normal");
                    else
                        tvResultat.setText("Le niveau de glycémie est élevé");
                }
            } else {
                if (valeurMesure > 10.0)
                    tvResultat.setText("Le niveau est élevé");
                else
                    tvResultat.setText("Le niveau est normal");
            }
        }
    }

    public void init() {
        etValeur = findViewById(R.id.etValeur);
        sbAge = findViewById(R.id.sbAge);
        btnConsulter = findViewById(R.id.btnConsulter);
        rbIsFasting = findViewById(R.id.rbtOui);
        rbIsNotFasting = findViewById(R.id.rbtNon);
        tvAge = findViewById(R.id.tvAge);
        tvResultat = findViewById(R.id.tvResultat);
        radioGroup = findViewById(R.id.radioGroup);
    }
}
