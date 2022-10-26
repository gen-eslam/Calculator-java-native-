package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnDiv, btnMal, btnMin, btnEqual, btnAc, btnDel, btnDot;
    private TextView textViewResult, textViewHistory;
    private String number = null, status = null, history="";
    private double firstNum = 0, lastNum = 0;
    boolean operator = false;
    DecimalFormat decimalFormat = new DecimalFormat("######.######");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        btnClick(btn0);
        btnClick(btn1);
        btnClick(btn2);
        btnClick(btn3);
        btnClick(btn4);
        btnClick(btn5);
        btnClick(btn6);
        btnClick(btn7);
        btnClick(btn8);
        btnClick(btn9);
        btnClick(btnDot);
        btnAdd.setOnClickListener(view -> {
            history +="+";
            checkOperation("addition");});
        btnDiv.setOnClickListener(view -> {
            history +="/";
            checkOperation("division");
        });
        btnMin.setOnClickListener(view -> {
            history +="-";
            checkOperation("subtraction");});
        btnMal.setOnClickListener(view -> {
            history +="*";
            checkOperation("multiplication");});
        btnEqual.setOnClickListener(view -> checkOperation(status));
        btnAc.setOnClickListener(view -> {
            number = null;
            status = null;
            textViewResult.setText("0");
            textViewHistory.setText("");
            firstNum = 0;
            lastNum = 0;
            history = "";
        });
        btnDel.setOnClickListener(view -> {
            if (number != null && number.length() > 1) {
                number = number.substring(0, number.length() - 1);
                textViewResult.setText(number);
            } else {
                number = null;
                status = null;
                textViewResult.setText("0");
                textViewHistory.setText("");
                firstNum = 0;
                lastNum = 0;
            }


        });


    }

    //init all btn and text view
    private void initComponent() {

        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnAdd = findViewById(R.id.btn_add);
        btnDiv = findViewById(R.id.btn_divide);
        btnMal = findViewById(R.id.btn_multi);
        btnMin = findViewById(R.id.btn_minus);
        btnEqual = findViewById(R.id.btn_equal);
        btnAc = findViewById(R.id.btn_AC);
        btnDel = findViewById(R.id.btn_del);
        btnDot = findViewById(R.id.btn_dot);
        textViewResult = findViewById(R.id.txt_view_result);
        textViewHistory = findViewById(R.id.txt_view_history);

    }

    //
    private void numberClick(String number) {
        if (this.number == null) {
            this.number = number;
        } else {
            this.number += number;
        }
        textViewResult.setText(this.number);
        operator = true;

    }

    private void btnClick(Button btn) {
        if (btn.getText().toString().equals(".")) {
            if (number == null) {
                number = "0";

            }
        }
        btn.setOnClickListener(view -> {
            historyShow(btn);
            numberClick(btn.getText().toString());
        });
    }

    private void plus() {
        lastNum = Double.parseDouble(textViewResult.getText().toString());
        firstNum += lastNum;
        textViewResult.setText(decimalFormat.format(firstNum));
        operator = true;

    }

    private void minus() {
        if (firstNum == 0)
            firstNum = Double.parseDouble(textViewResult.getText().toString());
        else {
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            firstNum -= lastNum;
        }
        textViewResult.setText(decimalFormat.format(firstNum));
        operator = true;

    }

    private void multiply() {
        if (firstNum == 0) {
            firstNum = 1;
        }
        lastNum = Double.parseDouble(textViewResult.getText().toString());
        firstNum *= lastNum;
        textViewResult.setText(decimalFormat.format(firstNum));
        operator = true;


    }

    private void divide() {
        lastNum = Double.parseDouble(textViewResult.getText().toString());
        if (firstNum == 0) {
            firstNum = lastNum;
        } else {

            firstNum /= lastNum;
        }

        textViewResult.setText(decimalFormat.format(firstNum));
        operator = true;


    }

    private void checkOperation(String operation) {


        if (operation != null) {
            status = operation;

            if (operator) {
                switch (status) {
                    case "multiplication":
                        multiply();

                        break;
                    case "division":

                        divide();

                        break;
                    case "addition":
                        plus();

                        break;
                    case "subtraction":
                        minus();

                        break;
                    default:
                        break;
                }
                operator = false;
                number = null;


            }
        } else {
            number = textViewResult.getText().toString();
        }


    }

    private void historyShow(Button btn) {
        history += btn.getText().toString();
        textViewHistory.setText(history);

    }


}