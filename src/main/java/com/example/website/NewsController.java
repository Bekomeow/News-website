package com.example.website;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EconomyButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button LiveButton;

    @FXML
    private Button SportsButton;

    @FXML
    private Button TechnologyButton;

    @FXML
    void initialize() {
        ExitButton.setOnAction(event -> {
            FXMLLoader loaderToLogin = new FXMLLoader(HelloApplication.class.getResource("AuthorizationPage.fxml"));

            ExitButton.getScene().getWindow().hide();

            try {
                loaderToLogin.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage siteStage = new Stage();
            siteStage.setScene(new Scene(loaderToLogin.getRoot()));
            siteStage.show();
        });

        TechnologyButton.setOnAction(event -> {
            FXMLLoader loaderToTechnologyPage= new FXMLLoader(HelloApplication.class.getResource("TechnologyPage.fxml"));

            TechnologyButton.getScene().getWindow().hide();

            try {
                loaderToTechnologyPage.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage siteStage = new Stage();
            siteStage.setScene(new Scene(loaderToTechnologyPage.getRoot()));
            siteStage.show();
        });

        SportsButton.setOnAction(event -> {
            FXMLLoader loaderToSportPage = new FXMLLoader(HelloApplication.class.getResource("SportPage.fxml"));

            SportsButton.getScene().getWindow().hide();

            try {
                loaderToSportPage.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage siteStage = new Stage();
            siteStage.setScene(new Scene(loaderToSportPage.getRoot()));
            siteStage.show();
        });

        LiveButton.setOnAction(event -> {
            FXMLLoader loaderToLivePage = new FXMLLoader(HelloApplication.class.getResource("LivePage.fxml"));

            LiveButton.getScene().getWindow().hide();

            try {
                loaderToLivePage.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage siteStage = new Stage();
            siteStage.setScene(new Scene(loaderToLivePage.getRoot()));
            siteStage.show();
        });

        EconomyButton.setOnAction(event -> {
            FXMLLoader loaderToEconomyPage = new FXMLLoader(HelloApplication.class.getResource("EconomyPage.fxml"));

            EconomyButton.getScene().getWindow().hide();

            try {
                loaderToEconomyPage.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage siteStage = new Stage();
            siteStage.setScene(new Scene(loaderToEconomyPage.getRoot()));
            siteStage.show();
        });

    }
}
