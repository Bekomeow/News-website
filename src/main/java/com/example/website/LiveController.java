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
import javafx.stage.Stage;

public class LiveController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem ExitItem;

    @FXML
    private Button HomeButton;

    @FXML
    private Menu LiveButton;

    @FXML
    private MenuBar LiveMenu;

    @FXML
    private MenuItem LiveNewsItem;

    @FXML
    private ScrollPane LiveNewsPane;

    @FXML
    void initialize() {
        HomeButton.setOnAction(event -> {
            FXMLLoader loaderToNewsPage = new FXMLLoader(HelloApplication.class.getResource("NewsPage.fxml"));

            LiveNewsPane.getScene().getWindow().hide();

            try {
                loaderToNewsPage.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage siteStage = new Stage();
            siteStage.setScene(new Scene(loaderToNewsPage.getRoot()));
            siteStage.show();
        });

        LiveNewsItem.setOnAction(event -> {
            LiveNewsPane.setVisible(true);
        });
        HomeButton.setOnMouseEntered(event -> {
            HomeButton.setStyle("-fx-background-color: #0096C9");
        });
        HomeButton.setOnMouseExited(event -> {
            HomeButton.setStyle("-fx-background-color: #FFFFFF");
        });

        ExitItem.setOnAction(event -> {
            LiveNewsPane.setVisible(false);
        });
    }
}

