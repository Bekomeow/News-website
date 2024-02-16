package com.example.website;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TechnologyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem ArtificialintelligenceItem;

    @FXML
    private ScrollPane BiotechnologyPane;

    @FXML
    private ScrollPane artificialIntelligencePane;

    @FXML
    private MenuItem BiotecnologyItem;

    @FXML
    private MenuItem ExitItem;

    @FXML
    private Button HomeButton;

    @FXML
    private AnchorPane Pane;

    @FXML
    private Menu TechnologyButton;

    @FXML
    private MenuBar TechnologyMenu;

    @FXML
    void HomeButton(ActionEvent event) {

    }

    @FXML
    void initialize() {
        HomeButton.setOnAction(event -> {
            FXMLLoader loaderToNewsPage = new FXMLLoader(HelloApplication.class.getResource("NewsPage.fxml"));

            Pane.getScene().getWindow().hide();

            try {
                loaderToNewsPage.load();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            Stage siteStage  = new Stage();
            siteStage.setScene(new Scene(loaderToNewsPage.getRoot()));
            siteStage.show();
        });

        BiotecnologyItem.setOnAction(event -> {
            BiotechnologyPane.setVisible(true);
            artificialIntelligencePane.setVisible(false);
        });
        ArtificialintelligenceItem.setOnAction(event -> {
            artificialIntelligencePane.setVisible(true);
            BiotechnologyPane.setVisible(false);
        });

        HomeButton.setOnMouseEntered(event -> {
            HomeButton.setStyle("-fx-background-color: #0096C9");
        });
        HomeButton.setOnMouseExited(event -> {
            HomeButton.setStyle("-fx-background-color: #FFFFFF");
        });

        ExitItem.setOnAction(event -> {
            BiotechnologyPane.setVisible(false);
            artificialIntelligencePane.setVisible(false);
        });
    }
}
