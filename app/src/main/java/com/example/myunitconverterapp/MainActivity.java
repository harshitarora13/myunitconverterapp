package com.example.myunitconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    //Variables
    Spinner options;
    TextView viewResult1, viewResult2, viewResult3;
    TextView viewUnit1, viewUnit2, viewUnit3;
    ImageButton buttonLength, buttonTemp, buttonWeight;

    private final String[] userOptions = {"Metre", "Celsius", "Kilogram"};

    private void initialiseSpinner() {
        options = findViewById(R.id.spinnerOptions);
        ArrayAdapter<String> optionsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, userOptions);
        optionsAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        options.setAdapter(optionsAdapter);
    }

    //Method to validate the userInput
    private double tryParseDouble(String userInput) {
        if (userInput != null || !userInput.isEmpty() ||!userInput.equals(".")) {
            try {
                return Double.parseDouble(userInput);
            }
            catch (Exception e) {
                Toast.makeText(this, "Please Enter a number.", Toast.LENGTH_SHORT).show();
                return 0;
            }
        }
        else
            Toast.makeText(this, "Please Enter a number.", Toast.LENGTH_SHORT).show();
        return 0;
    }

    //Method to store user input
    private Double getInput() {
        EditText userInput = findViewById(R.id.editUserInput);
        String input = userInput.getText().toString();
        Double validate = tryParseDouble(input);
        if (validate != 0)
            return validate;
        else
            return 0.0;
    }

    //Method to convert Decimal to String and round to two decimal places.
    public String twoDecimal(double input) {
        String result = new DecimalFormat("#.0#").format(input);
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLength = findViewById(R.id.buttonLength);
        buttonTemp = findViewById(R.id.buttonTemp);
        buttonWeight = findViewById(R.id.buttonWeight);
        viewResult1 = findViewById(R.id.textViewResult1);
        viewUnit1 = findViewById(R.id.textViewUnit1);
        viewResult2 = findViewById(R.id.textViewResult2);
        viewUnit2 = findViewById(R.id.textViewUnit2);
        viewResult3 = findViewById(R.id.textViewResult3);
        viewUnit3 = findViewById(R.id.textViewUnit3);
        initialiseSpinner();
        buttonLength.setOnClickListener(this);
        buttonTemp.setOnClickListener(this);
        buttonWeight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String select = options.getSelectedItem().toString();
        double input  = getInput();

        switch (v.getId()) {
            case R.id.buttonLength: lengthConv(input, select);
                break;
            case R.id.buttonTemp: tempConv(input, select);
                break;
            case R.id.buttonWeight: weightConv(input, select);
                break;
        }
    }

    public void lengthConv(double metre, String selection) {
        if (!selection.equals("Metre"))
            Toast.makeText(this, "Please select the correct conversion option!", Toast.LENGTH_SHORT).show();
        else if (metre == 0.0)
            Toast.makeText(this, "Input cannot be empty or zero!", Toast.LENGTH_SHORT).show();
        else {
            double toCM = metre * 100.0;
            double toFoot = metre * 3.28;
            double toInch = metre * 39.3701;
            viewResult1.setText(twoDecimal(toCM));
            viewResult2.setText(twoDecimal(toFoot));
            viewResult3.setText(twoDecimal(toInch));
            viewUnit1.setText("cm");
            viewUnit2.setText("Ft");
            viewUnit3.setText("In");
        }
    }

    public void weightConv(double kg, String selection) {
        if (!selection.equals("Kilogram"))
            Toast.makeText(this, "Please select the correct conversion option!", Toast.LENGTH_SHORT).show();
        else if (kg == 0.0)
            Toast.makeText(this, "Input cannot be empty or zero!", Toast.LENGTH_SHORT).show();
        else {
            double toG = kg * 1000.0;
            double toOz = kg * 35.274;
            double toLb = kg * 2.205;
            viewResult1.setText(twoDecimal(toG));
            viewResult2.setText(twoDecimal(toOz));
            viewResult3.setText(twoDecimal(toLb));
            viewUnit1.setText("Gram");
            viewUnit2.setText("Ounce(Oz)");
            viewUnit3.setText("Pound(lb)");
        }
    }

    public void tempConv(double C, String selection) {
        if (!selection.equals("Celsius"))
            Toast.makeText(this, "Please select the correct conversion option!", Toast.LENGTH_SHORT).show();
        else if (C == 0.0)
            Toast.makeText(this, "Input cannot be empty or zero!", Toast.LENGTH_SHORT).show();
        else {
            double toF = (C * 1.8) + 32;
            double toK = C + 273.15;
            viewResult1.setText(twoDecimal(toF));
            viewResult2.setText(twoDecimal(toK));
            viewResult3.setText("");
            viewUnit1.setText("Fahrenheit");
            viewUnit2.setText("Kelvin");
            viewUnit3.setText("");
        }
    }
}