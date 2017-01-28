package com.example.brendan.tipcalculator;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    boolean hstClick;
    double spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Clear button
    public void onClear(View view) {

        //Clear button will be used to clear the tip and total amount
        TextView TipText = (TextView) findViewById(R.id.TipID);
        TextView TotalText = (TextView) findViewById(R.id.Totalid);

        //Clear button will be cleared for tip amount and total cost
        TipText.setText("");
        TotalText.setText("");
    }


    public void onCalculate(View view) {

        // Check Bill Information for invalid data
        // find the bill input field
        TextView editText = (TextView) findViewById(R.id.BillID);

        //break down input as string
        String input = editText.getText().toString();

        // check for valid numeric input from user
        if(!input.matches("-?\\d+(\\.\\d+)?"))
        {
            //Error handing comment when the bill amount is not a number
            Toast.makeText(this, "Not a Valid Input!", Toast.LENGTH_LONG).show();
            return;
        }

        //Text view for tip and total text
        TextView TipText = (TextView) findViewById(R.id.TipID);
        TextView TotalText = (TextView) findViewById(R.id.Totalid);

        //Text for total cost and tip
        EditText CostTotal = (EditText) findViewById(R.id.BillID);
        Spinner TipTotal = (Spinner) findViewById(R.id.spinner2);

        //Cost value
        String costTotal = CostTotal.getText().toString();
        Double dblcostTotal;

        dblcostTotal = Double.valueOf(costTotal);

        //Tip percent value
        String tippercent = TipTotal.getSelectedItem().toString();
        Double dbltippercent;

        tippercent = tippercent.substring(0, tippercent.length() - 1);
        dbltippercent = Double.valueOf(tippercent);

        //Tip percent total is divided by 100
        Double dblTipTotal = dblcostTotal * (dbltippercent / 100);
        Double dblBillTotal;


        //When HST box is checked or when it is not checked
        CheckBox hstBox = (CheckBox) findViewById(R.id.checkBox2);

        if (hstBox.isChecked())
        {
            //When HST box is checked, tip amount plus total cost is added, and multiplied by 1.13 for HST amount
            dblBillTotal = dblTipTotal + (dblcostTotal * 1.13);
        }
        else
        {
            //When HST box is not checked, tip amount plus total cost is added
            dblBillTotal = dblTipTotal + (dblcostTotal);
        }

        //Tip total will be rounded to 2 decimal places
        TipText.setText(String.format("$%.2f", dblTipTotal));

        //Bill total will be rounded to 2 decimal places
        TotalText.setText(String.format("$%.2f", dblBillTotal));

    }

    // gets spinner value
    // returns the spinner value as a double
    public double getspinnerValue(View view){

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        String tip = spinner.getSelectedItem().toString();

        double dTip = Double.parseDouble(tip);

        return dTip;
    }

    //bill amount and tip %
    public double  calculate(double bill , double tip, boolean hasHST ){

        double total;
        // if HST is enabled
        if (hasHST){
            //.13 is needed for HST amount
            total =   .13 * (bill* tip);
            //no HST, .13 is not needed
        }else {
            total =  (bill* tip);
        }

        return total;

    }

    //This method is used for checking the HST button
    public void onCheckboxClicked(View view) {
        CheckBox hstBox = (CheckBox) findViewById(R.id.checkBox2);

        boolean checked = ((CheckBox) view).isChecked();

                // if HST button is pressed
                if (checked){

                    hstClick=true;

                }
                // if the HST option isn't enabled
                else{

                    hstClick= true;

                }
        }

    //This method checks if the bill amount entered is a valid number
    public boolean checkInput(View view){

    boolean isValid ;

    // find the bill input field
    TextView editText = (TextView) findViewById(R.id.BillID);

    //break down input as string
    String input = editText.getText().toString();

    if(input.matches("-?\\d+(\\.\\d+)?"))
    {
        //Error handling comment when the bill amount is not a number
        Toast.makeText(this, "Not a Valid Input!", Toast.LENGTH_LONG).show();
    }

    return true;

  }

}



