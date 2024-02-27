package client.controller.statistics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsFormController implements Initializable {

    @FXML
    private BarChart<?, ?> chart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2023");
        series1.getData().add(new XYChart.Data("Austria", 2323));
        series1.getData().add(new XYChart.Data("Austria", 4334));
        series1.getData().add(new XYChart.Data("Austria", 10323));
        series1.getData().add(new XYChart.Data("Austria", 25223));
        series1.getData().add(new XYChart.Data("Austria", 11113));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2024");
        series2.getData().add(new XYChart.Data("Austria", 2323));
        series2.getData().add(new XYChart.Data("Austria", 4334));
        series2.getData().add(new XYChart.Data("Austria", 10323));
        series2.getData().add(new XYChart.Data("Austria", 25223));
        series2.getData().add(new XYChart.Data("Austria", 11113));

        chart.getData().addAll(series1,series2);
        chart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
    }
}
