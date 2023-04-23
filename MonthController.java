package com.company;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

public class MonthController {
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
    private TableView<?> calendar;
    @FXML
    private TableColumn<?, ?> fridays;
    @FXML
    private TableColumn<?, ?> mondays;
    @FXML
    private TableColumn<?, ?> saturdays;
    @FXML
    private TableColumn<?, ?> sundays;
    @FXML
    private TableColumn<?, ?> thursdays;
    @FXML
    private TableColumn<?, ?> tuesdays;
    @FXML
    private TableColumn<?, ?> wednesdays;

    public MonthController() {
    }

    @FXML
    void initialize() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        this.dateTime.setText(dateFormat.format(cal.getTime()));
        this.backButton.setOnAction((event) -> {
            this.backButton.getScene().getWindow().hide();
        });
        this.celsius.setOnAction((event) -> {
        });
        this.fahrenheit.setOnAction((event) -> {
        });
    }
}