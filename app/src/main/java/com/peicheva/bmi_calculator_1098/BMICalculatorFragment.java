package com.peicheva.bmi_calculator_1098;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.peicheva.bmi_calculator_1098.helper.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BMICalculatorFragment extends Fragment {

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        TextView lblBMI;
        Button btnCalculate;
        TextView txtHeight;
        TextView txtWeight;

    com.peicheva.bmi_calculator_1098.helper.CalculateBMI calculateBMI;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bmicalculator,container,false);
            btnCalculate = v.findViewById(R.id.btnCalculate);
            lblBMI = v.findViewById(R.id.lblBMI);
            txtHeight = v.findViewById(R.id.txtHeight);
            txtWeight = v.findViewById(R.id.txtWeight);


            btnCalculate.setOnClickListener((view) -> {
                {
                    try {
                        double txtH = Double.parseDouble(txtHeight.getText().toString());
                        double txtW = Double.parseDouble(txtWeight.getText().toString());

                        calculateBMI = new com.peicheva.bmi_calculator_1098.helper.CalculateBMI(txtH,txtW);

                        // Получаване на тегло
                        double bmiWeight = calculateBMI.getInputKg();

                        // Получаване на височина
                        double bmiHeight = calculateBMI.getInputCm();

                        // Изчисляване на ИТМ
                        double bmi = calculateBMI.calculateBmi(calculateBMI.getInputKg(),calculateBMI.getInputCm());

                        // Получаване на ИТМ тип
                        String bmiType = calculateBMI.getBmiType(bmi);

                        // Получаване на дата
                        String daTe = formatter.format(date);


                        // Добавяне на данни към базата данни
                        com.peicheva.bmi_calculator_1098.helper.BmiDatatable BmiDatatable = new BmiDatatable(getActivity());
                        BmiDatatable.openDB();
                        BmiDatatable.insertRecord(daTe,Double.toString(bmiWeight), Double.toString(bmiHeight), Double.toString(bmi),bmiType);
                        BmiDatatable.closeDB();

                        // Показване на Toast със съобщение за резултат
                        Toast.makeText(getActivity(),"Вашия ИТМ: " + bmi +" " + bmiType,Toast.LENGTH_SHORT).show();

                        // Добавяне на елементи за показване на резултата
                        String text = "Вашия ИТМ\n"+bmi+"\n"+bmiType;
                        lblBMI.setText(text);

                    }

                    catch (Exception x)
                    {
                        Toast.makeText(getActivity(),"Моля, въведете валидни стойности\n" + x,Toast.LENGTH_SHORT).show();

                    }


                }
            });


    return v;

    }
}
