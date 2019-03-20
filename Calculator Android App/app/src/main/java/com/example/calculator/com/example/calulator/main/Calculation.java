package com.example.calculator.com.example.calulator.main;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Calculation{

    // Current Value
    private float currentValue;
    private float changingValue;

    private boolean isDecimal;
    private float decimalSpots;

    private TextView textView;


    // Server
    OkHttpClient client;

    public enum CalculatorState {
        OperatorStatus, Numberstatus
    }
    private CalculatorState calculatorState;

    private String operatorSymbol;

    public Calculation(){
        currentValue = 0;
        calculatorState = CalculatorState.Numberstatus;
        isDecimal = false;
        decimalSpots = 10;
        operatorSymbol = "";

        client = new OkHttpClient();
    }

    public void updateNumber(float number){
        if (calculatorState.equals(CalculatorState.Numberstatus)){
            if (isDecimal && currentValue != 0){
                number /= decimalSpots;
                currentValue += number;
                decimalSpots *= 10;
            } else {
                currentValue *= 10;
                currentValue += number;
            }
        } else {
            if (isDecimal && changingValue != 0){
                number /= decimalSpots;
                changingValue += number;
                decimalSpots *= 10;
            } else {
                changingValue *= 10;
                changingValue += number;
            }
        }

        // Update View
        if (calculatorState.equals(CalculatorState.OperatorStatus)){
            textView.setText(operatorSymbol + Float.toString(changingValue));
        } else {
            textView.setText(operatorSymbol + Float.toString(currentValue));
        }
    }

    // Reset view
    public void reset(){
        isDecimal = false;
        calculatorState = CalculatorState.Numberstatus;
        currentValue = 0;
        changingValue = 0;
        operatorSymbol = "";
        decimalSpots = 10;
        textView.setText("0");
    }

    // Calculate Changes
    public void equal(){

        // Build URL Request
        String urlRequest = "";

        switch (operatorSymbol){
            case "+":
                urlRequest = "http://10.1.115.180:8080/CalculatorBackend/calculateTest?initialValue="+currentValue+"&changingValue="+changingValue+"&operatorSymbol=plus";
                break;
            case "-":
                urlRequest = "http://10.1.115.180:8080/CalculatorBackend/calculateTest?initialValue="+currentValue+"&changingValue="+changingValue+"&operatorSymbol=minus";
                break;
            case "*":
                urlRequest = "http://10.1.115.180:8080/CalculatorBackend/calculateTest?initialValue="+currentValue+"&changingValue="+changingValue+"&operatorSymbol=multiply";
                break;
            case "/":
                urlRequest = "http://10.1.115.180:8080/CalculatorBackend/calculateTest?initialValue="+currentValue+"&changingValue="+changingValue+"&operatorSymbol=divide";
                break;
        }

        // Build Request
        Request request = new Request.Builder()
                .url(urlRequest)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();

        // Send Request and Set new calculation
        new ServerCalculation().execute(request);
    }

    public interface AsyncResponse {
        void processFinished(String output);
    }

    class ServerCalculation extends AsyncTask<Request, Void, Response>{
        Response response = null;

        // Async Task
        @Override
        protected Response doInBackground(Request... requests){
            try {
                response = client.newCall(requests[0]).execute();
            } catch (IOException e){
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(Response response){
            super.onPostExecute(response);
            Log.i("Server response", response.toString());
            try{
                textView.setText(response.body().string());
                changingValue = 0;
                operatorSymbol = "";
                isDecimal = false;
                decimalSpots = 10;
                setCalculatorState(CalculatorState.Numberstatus);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    // Setters and Getters
    public CalculatorState getCalculatorState(){
        return  calculatorState;
    }

    public void setCalculatorState(CalculatorState calculatorState){
        this.calculatorState = calculatorState;
    }

    public void setCalculatorView(TextView textView){
        this.textView = textView;
    }

    public void setDecimalMode(boolean isDecimal){
        this.isDecimal = isDecimal;
    }

    public boolean isDecimal(){
        return  isDecimal;
    }

    public void setOperatorSymbol(String operatorSymbol){
        this.operatorSymbol = operatorSymbol;
    }

    public void setDecimalSpots(float decimalSpots){
        this.decimalSpots = decimalSpots;
    }
}
