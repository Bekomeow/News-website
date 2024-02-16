package com.example.website;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    void initialize() {
        LoginButton.setOnAction(event -> {
            try {
                RandomAccessFile adminInfo = new RandomAccessFile("AdminInfo", "rw");
                String Login = adminInfo.readLine();
                String Password = adminInfo.readLine();
                if(Login.substring(Login.length()-6, Login.length()).equals(LoginField.getText()) && Password.substring(Password.length()-9, Password.length()).equals(PasswordField.getText())) {
                    LoginButton.getScene().getWindow().hide();

                    FXMLLoader loaderToNewsPage = new FXMLLoader(HelloApplication.class.getResource("NewsPage.fxml"));

                    try {
                        loaderToNewsPage.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Stage siteStage = new Stage();
                    siteStage.setScene(new Scene(loaderToNewsPage.getRoot()));
                    siteStage.show();
                }
                else {
                    FXMLLoader loaderToLoginError = new FXMLLoader(HelloApplication.class.getResource("LoginError.fxml"));

                    try {
                        loaderToLoginError.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Stage siteStage = new Stage();
                    siteStage.setScene(new Scene(loaderToLoginError.getRoot()));
                    siteStage.show();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        LoginButton.setOnMouseEntered(event -> {
            LoginButton.setStyle("-fx-background-color: #224C8D; -fx-background-radius: 50");
        });
        LoginButton.setOnMouseExited(event -> {
            LoginButton.setStyle("-fx-background-radius: 50; -fx-background-color: #57A6DA");
        });
    }

    void admin(Pane pane) {
        FXMLLoader loaderToAdminPage = new FXMLLoader(HelloApplication.class.getResource("AdminPage.fxml"));

        pane.getScene().getWindow().hide();

        try {
            loaderToAdminPage.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage siteStage = new Stage();
        siteStage.setScene(new Scene(loaderToAdminPage.getRoot()));
        siteStage.show();
    }

}
