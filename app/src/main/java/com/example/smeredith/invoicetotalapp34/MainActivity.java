package com.example.smeredith.invoicetotalapp34;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity implements TextView.OnEditorActionListener {

    //declare instance variables for the widgets

    private EditText inputeditText;
    private TextView percentTextView;
    private TextView discountTextView;
    private TextView totalTextView;

    private String subtotalString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the widgets from the R class
        inputeditText =  findViewById(R.id.inputeditText);
        percentTextView =  findViewById(R.id.percenttextView);
        discountTextView =  findViewById(R.id.discounttextView);
        totalTextView =  findViewById(R.id.totaltextView);

        inputeditText.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }

    private void calculateAndDisplay(){
        //get subtotal
        subtotalString = inputeditText.getText().toString();
        float subtotal;
        if(subtotalString.equals("")){
            subtotal = 0;
        } else {
            subtotal = Float.parseFloat(subtotalString);
        }

        //get the discount percent
        float discoutPercent = 0;

        if(subtotal >=200){
            discoutPercent = .2f;
        } else if (subtotal >=100){
            discoutPercent = .1f;
        } else {
            discoutPercent = 0;
        }

        //calculate the discount
        float discountAmount = subtotal * discoutPercent;
        float total = subtotal - discountAmount;

        //display the data on the layout
        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(discoutPercent));

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        discountTextView.setText(currency.format(discountAmount));
        totalTextView.setText(currency.format(total));

    }
}
