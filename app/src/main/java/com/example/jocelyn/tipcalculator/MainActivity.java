package com.example.jocelyn.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double billTotal = 0;
    private double tipPercentage = 0;
    private int numPeople = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        final TextView seekBarValue = (TextView)findViewById(R.id.tipPercent);
        final TextView tipAmountText = (TextView)findViewById(R.id.tipAmount);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                tipPercentage = progress;
                seekBarValue.setText("Tip Percentage: " + String.valueOf(progress) + "%");
                tipAmountText.setText("Tip Amount: $" + (double) progress/100*billTotal);
                updateTotalPayment();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        final EditText billTotalText = (EditText) findViewById(R.id.billTotal);
        billTotalText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                billTotal = Integer.parseInt( billTotalText.getText().toString() );
                updateTotalPayment();
            }
        });

    }

    public void addPerson( View v ) {
        TextView peopleText = (TextView)findViewById(R.id.people);
        numPeople++;
        peopleText.setText( String.valueOf(numPeople) );
        updateTotalPayment();
    }

    public void subtractPerson( View v ) {
        TextView peopleText = (TextView)findViewById(R.id.people);
        if( numPeople > 1 ) {
            numPeople--;
        }
        peopleText.setText( String.valueOf(numPeople) );
        updateTotalPayment();
    }


    public void updateTotalPayment() {
        TextView totalPayment = (TextView) findViewById(R.id.totalPayment);
        totalPayment.setText( "Each Person Pays: " +
                billTotal * ( 1 + (tipPercentage/100) ) / numPeople);
    }

}
