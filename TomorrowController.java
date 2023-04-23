package com.company;


import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class TomorrowController {
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
    private RadioButton fahrenheit;

    @FXML
    private Text temperature;

    @FXML
    private Label dateTime;

    @FXML
    private Text temperatureValue;

    @FXML
    void initialize() {
        //tomorrowDate.setText("09.12.2021");

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);
        dateTime.setText(dateFormat.format(cal.getTime()));

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
        });

        celsius.setOnAction(event -> {
            int N=2270;
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

                File tempToday = new File("temperature_tomorrow.txt");
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
            int N = 2270;
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

                File tempToday = new File("temperature_tomorrow.txt");
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

