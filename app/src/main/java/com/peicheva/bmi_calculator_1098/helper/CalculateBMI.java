package com.peicheva.bmi_calculator_1098.helper;

public class CalculateBMI {

    private final double inputCm;
    private final double inputKg;

    public CalculateBMI(double inputCm, double inputKg)
    {
        this.inputCm = inputCm;
        this.inputKg = inputKg;
    }

    public double getInputCm() {
        return inputCm;
    }

    public double getInputKg() {
        return inputKg;
    }

    public double calculateBmi(double inputKg, double inputCm)
    {
        double result;

        double cm_to_m = 100;

        inputCm = inputCm / cm_to_m;

        result = inputKg/(inputCm*inputCm);

        result = (double) Math.round(result * 100) / 100;

        return result;


    }

    public String getBmiType(double bmi)
    {
        String type = "null";

        if (bmi<=18.5)
        {
            type = "Поднормено тегло";
        }
        else if (bmi<=24.9)
        {
            type = "Нормално тегло";
        }
        else if (bmi<=29.9)
        {
            type = "Наднормено тегло";
        }
        else if (bmi<=34.9)
        {
            type = "Затлъстяване";
        }
        else if (bmi>35)
        {
            type = "Силно затлъстяване";
        }

        return type;

    }
}
