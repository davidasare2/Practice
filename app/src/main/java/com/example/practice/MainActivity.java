package com.example.practice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText getName, getAge;
    Button submitButton;
    String pName;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            getName = findViewById(R.id.etvName);
            getAge = findViewById(R.id.etvAge);
            submitButton = findViewById(R.id.btnSubmit);

            submitButton.setEnabled(false);

            TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    pName = getName.getText().toString().trim();
                    String pAge = getAge.getText().toString().trim();

                    boolean isValid = !pName.isEmpty() && !pAge.isEmpty();

                    if (isValid){
                        try {
                            age = Integer.parseInt(pAge);
                        } catch (NumberFormatException e) {
                            getAge.setError("Input must be a number!");
                            isValid = false;
                        }
                        submitButton.setEnabled(isValid);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };
            getName.addTextChangedListener(watcher);
            getAge.addTextChangedListener(watcher);

            submitButton.setOnClickListener(this);

            return insets;
        });
    }
    public void onClick(View view){
        new AlertDialog.Builder(this)
                .setTitle("Confirm Submission")
                .setMessage("Are you sure of your submission")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("MSG", "Hello");
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }
}