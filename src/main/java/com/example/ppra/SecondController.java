package com.example.ppra;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondController implements Initializable {
    @FXML
    Label resLabel, recomendation, docRecomendation;
    @FXML
    ImageView imageView;
    @FXML
    Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String doctors = "Рекомендуется посетить: терапевт";
        String sugar = Adapter.sugar;
        String pressure = Adapter.pressure;
        String weight = Adapter.weight;
        String path = "C:\\Users\\extre\\IdeaProjects\\ppra\\src\\main\\resources\\com\\example\\ppra\\";
        String stringFile = "";
        String comment = "";
        String jir = "\tПри ожирении рекомендуется разобраться в психологических причинах лишнего веса, " +
                "начать больше двигаться регулярно, " +
                "записывать всё съеденное в день, " +
                "соблюдать режим питания.\n";
        String pres = "\tПри проблемах с необходимы: " +
                "домашний самоконтроль; " +
                "следить за массой тела; " +
                "увеличить потребление фруктов с магнием и калием; " +
                "потреблять меньше соли; ограничить потребление жидкости; " +
                "соблюдать режим дня. \n";

        String sug = "\t При проблемах с сахаром необходимо " +
                "составить график ежедневных тренеровок вместе со своим лечащим врачом," +
                "следовать оптимальному плану питания," +
                "исключать сладкую пищу," +
                "поддерживать здоровый вес," +
                "не курить," +
                "ограничить употребление алкоголя. \n";


        String result = String.format("Сахар: %s\nДавление: %s\nМасса: %s", sugar, pressure, weight);
        resLabel.setText(result);

        if(Diseases.sugar < 2) {
            stringFile = path + "original.jpg";
            comment += sug;
            doctors += ", эндокринолог";
        }
        if(Diseases.weight) {
            stringFile = path + "prom.jpeg";
            comment += jir;
            doctors += ", врач-диетолог";
        }
        if(Diseases.pressure) {
            stringFile = path + "ggt.jpeg";
            comment += pres;
            doctors += ", кардиолог, нефролог, терапевт, невролог";
        }


        File file = new File(stringFile);
        imageView.setImage(new Image(file.toURI().toString()));
        recomendation.setText(comment);
        docRecomendation.setText(doctors + ".");
    }
}
