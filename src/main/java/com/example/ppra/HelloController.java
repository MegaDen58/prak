package com.example.ppra;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;


public class HelloController {

    @FXML
    TextField height, weight, pressure, sugar, age;
    @FXML
    CheckBox Man, Woman;
    @FXML
    Button button;

    public void goButton() {
        try{
            int height1 = Integer.parseInt(height.getText());
            int weight1 = Integer.parseInt(weight.getText());
            String pressure1 = pressure.getText();
            double sugar1 = Double.parseDouble(sugar.getText());
            int age1 = Integer.parseInt(age.getText());
            String sex = "";

            if(Man.isSelected()) sex += "Man";
            if(Woman.isSelected()) sex += "Woman";

            if(sex.length() > 6){
                JOptionPane.showMessageDialog(null, "Ошибка");
            }
            else{
                Adapter.sugar = checkForHighSugar(age1, sugar1) + String.format(" (%s)", sugar1);
                Adapter.pressure = checkForHighPressure(pressure1, age1) + String.format(" (%s)", pressure1);
                Adapter.weight = checkForHighWeight(height1, weight1) + String.format(" (%s)", weight1);

                Stage stageCLose = (Stage) button.getScene().getWindow();
                stageCLose.close();

                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("last.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 720, 567);
                stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.show();


            }
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(null, "Ошибка");
            System.out.println(exception.getMessage());
        }
    }

    public static String checkForHighWeight(double height, int weight){
        String toOutput = "В норме";
        double x = weight / Math.pow(height / 100, 2);

        if(x > 25 && x < 31){
            toOutput = "Избыточная масса";
            Diseases.weight = true;
        }
        if(x > 30 && x < 36){
            toOutput = "Ожирение I степени";
            Diseases.weight = true;
        }
        if(x > 35 && x < 41){
            toOutput = "Ожирение II степени";
            Diseases.weight = true;
        }
        if(x > 40){
            toOutput = "Ожирение III степени";
            Diseases.weight = true;
        }
        return toOutput;
    }

    public static String checkForHighPressure(String pressure, int age){
        String result = "";
        String[] pr = pressure.split("/");
        int pr0 = Integer.parseInt(pr[0]);
        int pr1 = Integer.parseInt(pr[1]);
        if(age > 14 && age < 55){
            if(pr0 < 117 && pr0 > 105 && pr1 < 79 && pr1 > 72){
                result = "Минимум";
            }
            if(pr0 < 130 && pr0 > 117 && pr1 < 86 && pr1 > 73){
                result = "Среднее";
            }
            if(pr0 < 145 && pr0 > 129 && pr1 < 90 && pr1 > 86){
                result = "Максимум";
                Diseases.pressure = true;
            }
        }
        if(age > 55){
            if(pr0 < 124 && pr0 > 118 && pr1 < 85 && pr1 > 82){
                result = "Минимум";
            }
            if(pr0 < 137 && pr0 > 130 && pr1 < 89 && pr1 > 85){
                result = "Среднее";
            }
            if(pr0 < 151 && pr0 > 143 && pr1 < 93 && pr1 > 91){
                result = "Максимум";
                Diseases.pressure = true;
            }
        }
        return result;
    }

    public static String checkForHighSugar(int age, double sugar){
        String result = "";
        if(age <= 14){
            if(sugar > 2.7 && sugar < 4.5){
                result = "В норме";
            }
            if(sugar < 2.6){
                result = "В упадке";
                Diseases.sugar = 0;
            }
            else{
                result = "В избытке";
                Diseases.sugar = 1;
            }
        }
        if(age >= 15 && age <= 60){
            if(sugar >= 4.1 && sugar <= 5.9){
                result = "В норме";
            }
            if(sugar < 4.1){
                result = "В упадке";
                Diseases.sugar = 0;
            }
            else{
                result = "В избытке";
                Diseases.sugar = 1;
            }
        }
        if(age >= 61 && age <= 90){
            if(sugar >= 4.6 && sugar <= 6.4){
                result = "В норме";
            }
            if(sugar < 4.6){
                result = "В упадке";
                Diseases.sugar = 0;
            }
            else{
                result = "В избытке";
                Diseases.sugar = 1;
            }
        }
        if(age > 90){
            if(sugar >= 4.2 && sugar <= 6.7){
                result = "В норме";
            }
            if(sugar < 4.2){
                result = "В упадке";
                Diseases.sugar = 0;
            }
            else{
                result = "В избытке";
                Diseases.sugar = 1;
            }
        }
        return result;
    }
}