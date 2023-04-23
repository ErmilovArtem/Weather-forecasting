package com.company;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class TodayController {
    public double convertKelvinToCelsius(double kelvin) {
        return Math.round(kelvin - 273.15);
    }

    public double convertKelvinToPharyngitis(double kelvin) {
        return Math.round((kelvin - 273.15) * 9 / 5 + 32);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private RadioButton celsius;

    @FXML
    private ToggleGroup choice;

    @FXML
    private Label dateTime;

    @FXML
    private RadioButton fahrenheit;

    @FXML
    private Text temperature;

    @FXML
    private Text temperatureValue;

    @FXML
    void initialize() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        dateTime.setText(dateFormat.format(cal.getTime()));

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
        });

        int N = 2270;

        celsius.setOnAction(event -> {
            try {
                String[] dates = new String[N];

                File dataIndex = new File("data_for_index.txt");
                FileReader fr = new FileReader(dataIndex);
                BufferedReader reader1 = new BufferedReader(fr);
                String line1 = reader1.readLine();

                //int index = 0;
                for (int i = 0; i < N; i++) {
                    dates[i] = line1;
                    line1 = reader1.readLine();
                    //index++;
                }

                String[] predToday = new String[N];

                File tempToday = new File("temperature_today.txt");
                FileReader fr2 = new FileReader(tempToday);
                BufferedReader reader2 = new BufferedReader(fr2);
                String line2 = reader2.readLine();

                //index = 0;
                for (int i = 0; i < N; i++) {
                    predToday[i] = line2;
                    line2 = reader2.readLine();
                    //index++;
                }

                double temp = Double.parseDouble(predToday[0]);

                temperatureValue.setText(String.valueOf(convertKelvinToCelsius(temp)));
                temperature.setText("℃");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fahrenheit.setOnAction(event -> {
            try {
                String[] dates = new String[N];

                File dataIndex = new File("data_for_index.txt");
                FileReader fr = new FileReader(dataIndex);
                BufferedReader reader1 = new BufferedReader(fr);
                String line1 = reader1.readLine();

                //int index = 0;
                for (int i = 0; i < N; i++) {
                    dates[i] = line1;
                    line1 = reader1.readLine();
                    //index++;
                }

                String[] predToday = new String[N];

                File tempToday = new File("temperature_today.txt");
                FileReader fr2 = new FileReader(tempToday);
                BufferedReader reader2 = new BufferedReader(fr2);
                String line2 = reader2.readLine();

                //index = 0;
                for (int i = 0; i < N; i++) {
                    predToday[i] = line2;
                    line2 = reader2.readLine();
                    //index++;
                }
                double temp = Double.parseDouble(predToday[0]);

                temperatureValue.setText(String.valueOf(convertKelvinToPharyngitis(temp)));
                temperature.setText("℉");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}