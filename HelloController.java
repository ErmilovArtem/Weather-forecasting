package com.company;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label dateTime;

    @FXML
    private Button exit;

    @FXML
    private Button month;

    @FXML
    private Button today;

    @FXML
    private Button tomorrow;

    @FXML
    private Button updateDateTime;

    @FXML
    private Button week;

    @FXML
    void initialize() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        dateTime.setText(dateFormat.format(cal.getTime()));

        updateDateTime.setOnAction(event -> {
            DateFormat dateFormatUpdate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Calendar calUpdate = Calendar.getInstance();
            dateTime.setText(dateFormatUpdate.format(calUpdate.getTime()));
        });

        today.setOnAction(event -> {
            //today.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("today.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stageToday = new Stage();
            stageToday.setScene(new Scene(root));
            stageToday.showAndWait();
        });

        tomorrow.setOnAction(event -> {
            //tomorrow.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("tomorrow.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stageToday = new Stage();
            stageToday.setScene(new Scene(root));
            stageToday.showAndWait();
        });

        week.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("week.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();

            Stage stageToday = new Stage();
            stageToday.setScene(new Scene(root));
            stageToday.showAndWait();
        });

        month.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("month.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();

            Stage stageToday = new Stage();
            stageToday.setScene(new Scene(root));
            stageToday.showAndWait();
        });

        exit.setOnAction(event -> {
            exit.getScene().getWindow().hide();
        });
    }

}