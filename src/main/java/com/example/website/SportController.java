package com.example.website;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class SportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem ExitItem;

    @FXML
    private Button HomeButton;

    @FXML
    private Menu SportButton;

    @FXML
    private MenuBar SportMenu;

    @FXML
    private ScrollPane footballPane;

    @FXML
    private MenuItem footballItem;

    @FXML
    private MenuItem professionalBoxingItem;

    @FXML
    private ScrollPane professionalBoxingPane;

    @FXML
    void initialize() {
        HomeButton.setOnAction(event -> {
            FXMLLoader loaderToNewsPage = new FXMLLoader(HelloApplication.class.getResource("NewsPage.fxml"));

            professionalBoxingPane.getScene().getWindow().hide();

            try {
                loaderToNewsPage.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage siteStage = new Stage();
            siteStage.setScene(new Scene(loaderToNewsPage.getRoot()));
            siteStage.show();
        });

        footballItem.setOnAction(event -> {
            footballPane.setVisible(true);
            professionalBoxingPane.setVisible(false);
        });
        professionalBoxingItem.setOnAction(event -> {
            professionalBoxingPane.setVisible(true);
            footballPane.setVisible(false);
        });

        HomeButton.setOnMouseEntered(event -> {
            HomeButton.setStyle("-fx-background-color: #0096C9");
        });
        HomeButton.setOnMouseExited(event -> {
            HomeButton.setStyle("-fx-background-color: #FFFFFF");
        });

        ExitItem.setOnAction(event -> {
            footballPane.setVisible(false);
            professionalBoxingPane.setVisible(false);
        });
    }
}