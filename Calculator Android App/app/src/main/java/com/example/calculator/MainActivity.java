package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.com.example.calulator.main.Calculation;

public class MainActivity extends AppCompatActivity {

    private Calculation calculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculation = new Calculation();

        // Numbers
        Button one = findViewById(R.id.one);
        one.setOnClickListener(new NumberPress());

        Button two = findViewById(R.id.two);
        two.setOnClickListener(new NumberPress());

        Button three = findViewById(R.id.three);
        three.setOnClickListener(new NumberPress());

        Button four = findViewById(R.id.four);
        four.setOnClickListener(new NumberPress());

        Button five = findViewById(R.id.five);
        five.setOnClickListener(new NumberPress());

        Button six = findViewById(R.id.six);
        six.setOnClickListener(new NumberPress());

        Button seven = findViewById(R.id.seven);
        seven.setOnClickListener(new NumberPress());

        Button eight = findViewById(R.id.eight);
        eight.setOnClickListener(new NumberPress());

        Button nine = findViewById(R.id.nine);
        nine.setOnClickListener(new NumberPress());

        Button zero = findViewById(R.id.zero);
        zero.setOnClickListener(new NumberPress());

        // Operators
        Button plus = findViewById(R.id.plus);
        plus.setOnClickListener(new CalculationPress());

        Button minus = findViewById(R.id.minus);
        minus.setOnClickListener(new CalculationPress());

        Button multiply = findViewById(R.id.multiply);
        multiply.setOnClickListener(new CalculationPress());

        Button divide = findViewById(R.id.divide);
        divide.setOnClickListener(new CalculationPress());

        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new CalculationPress());

        Button decimal = findViewById(R.id.decimal);
        decimal.setOnClickListener(new CalculationPress());

        Button equal = findViewById(R.id.equal);
        equal.setOnClickListener(new CalculationPress());

        // Update Calculator View
        calculation.setCalculatorView((TextView) findViewById(R.id.valueArea));
    }

    class NumberPress implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.one:
                    Log.i("BUTTON PRESS:", "1");
                    calculation.updateNumber(1);
                    break;
                case R.id.two:
                    Log.i("BUTTON PRESS:", "2");
                    calculation.updateNumber(2);
                    break;
                case R.id.three:
                    Log.i("BUTTON PRESS:", "3");
                    calculation.updateNumber(3);
                    break;
                case R.id.four:
                    Log.i("BUTTON PRESS:", "4");
                    calculation.updateNumber(4);
                    break;
                case R.id.five:
                    Log.i("BUTTON PRESS:", "5");
                    calculation.updateNumber(5);
                    break;
                case R.id.six:
                    Log.i("BUTTON PRESS:", "6");
                    calculation.updateNumber(6);
                    break;
                case R.id.seven:
                    Log.i("BUTTON PRESS:", "7");
                    calculation.updateNumber(7);
                    break;
                case R.id.eight:
                    Log.i("BUTTON PRESS:", "8");
                    calculation.updateNumber(8);
                    break;
                case R.id.nine:
                    Log.i("BUTTON PRESS:", "9");
                    calculation.updateNumber(9);
                    break;
                case R.id.zero:
                    Log.i("BUTTON PRESS:", "0");
                    calculation.updateNumber(0);
                    break;
            }
        }
    }

    class CalculationPress implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.plus:
                    Log.i("BUTTON PRESS", "Plus");
                    if (calculation.getCalculatorState().equals(Calculation.CalculatorState.OperatorStatus)){
                        return;
                    }
                    calculation.setDecimalSpots(10);
                    calculation.setOperatorSymbol("+");
                    calculation.setCalculatorState(Calculation.CalculatorState.OperatorStatus);
                    break;
                case R.id.minus:
                    Log.i("BUTTON PRESS:", "Minus");
                    if (calculation.getCalculatorState().equals(Calculation.CalculatorState.OperatorStatus)){
                        return;
                    }
                    calculation.setDecimalSpots(10);
                    calculation.setOperatorSymbol("-");
                    calculation.setCalculatorState(Calculation.CalculatorState.OperatorStatus);
                    break;
                case R.id.multiply:
                    Log.i("BUTTON PRESS:", "multiply");
                    if (calculation.getCalculatorState().equals(Calculation.CalculatorState.OperatorStatus)){
                        return;
                    }
                    calculation.setDecimalSpots(10);
                    calculation.setOperatorSymbol("*");
                    calculation.setCalculatorState(Calculation.CalculatorState.OperatorStatus);
                    break;
                case R.id.divide:
                    Log.i("BUTTON PRESS:", "divide");
                    if (calculation.getCalculatorState().equals(Calculation.CalculatorState.OperatorStatus)){
                        return;
                    }
                    calculation.setDecimalSpots(10);
                    calculation.setOperatorSymbol("/");
                    calculation.setCalculatorState(Calculation.CalculatorState.OperatorStatus);
                    break;
                case R.id.clear:
                    Log.i("BUTTON PRESS:", "clear");
                    calculation.reset();
                    break;
                case R.id.decimal:
                    Log.i("BUTTON PRESS:", "decimal");
                    if (calculation.isDecimal()){
                        return;
                    }
                    calculation.setDecimalSpots(10);
                    calculation.setDecimalMode(true);
                    break;
                case R.id.equal:
                    Log.i("BUTTON PRESS:", "equal");
                    calculation.equal();
                    break;
            }
        }
    }
}
