package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class WeekController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<?> calendar;

    @FXML
    private RadioButton celsius;

    @FXML
    private ToggleGroup choice;

    @FXML
    private Label dateTime;

    @FXML
    private RadioButton fahrenheit;

    @FXML
    private TableColumn<?, ?> friday;

    @FXML
    private TableColumn<?, ?> monday;

    @FXML
    private TableColumn<?, ?> saturday;

    @FXML
    private TableColumn<?, ?> sunday;

    @FXML
    private TableColumn<?, ?> thursday;

    @FXML
    private TableColumn<?, ?> tuesday;

    @FXML
    private TableColumn<?, ?> wednesday;

    @FXML
    void initialize() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        dateTime.setText(dateFormat.format(cal.getTime()));

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
        });


        celsius.setOnAction(event -> {

        });

        fahrenheit.setOnAction(event -> {

        });
    }

}
