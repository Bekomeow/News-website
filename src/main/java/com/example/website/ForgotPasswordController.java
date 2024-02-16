package com.example.website;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class ForgotPasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EmailField;

    @FXML
    private Line Email_Indicator;

    @FXML
    private Button LoginButton;

    @FXML
    private PasswordField NewPasswordField;

    @FXML
    private Line NewPassword_Indicator;

    @FXML
    private PasswordField RepeatPasswordField;

    @FXML
    private Line RepeatPassword_Indicator;

    @FXML
    private Button RestorePasswordButton;

    @FXML
    private ImageView loadMeow;

    @FXML
    void initialize() {
        LoginButton.setOnMouseEntered(event -> {
            LoginButton.setStyle("-fx-background-color: #224C8D; -fx-background-radius: 50");
        });
        LoginButton.setOnMouseExited(event -> {
            LoginButton.setStyle("-fx-background-radius: 50; -fx-background-color: #57A6DA");
        });

        RestorePasswordButton.setOnMouseEntered(event -> {
            RestorePasswordButton.setStyle("-fx-background-color: #224C8D; -fx-background-radius: 50");
        });
        RestorePasswordButton.setOnMouseExited(event -> {
            RestorePasswordButton.setStyle("-fx-background-radius: 50; -fx-background-color: #57A6DA");
        });

        File mediaFile = new File("C:\\Users\\BEKZHAN\\IdeaProjects\\Online news website\\src\\main\\resources\\Sound\\buttonSound.mp3");
        Media media = new Media(mediaFile.toURI().toString());
        MediaPlayer mp = new MediaPlayer(media);

        LoginButton.setOnAction(event -> {
            mp.stop();
            mp.play();
            loadMeow.setVisible(true);

            mp.setOnEndOfMedia(() -> {
                loadMeow.setVisible(false);
                FXMLLoader loaderToLogin = new FXMLLoader(HelloApplication.class.getResource("AuthorizationPage.fxml"));

                LoginButton.getScene().getWindow().hide();

                try {
                    loaderToLogin.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Stage siteStage = new Stage();
                siteStage.setScene(new Scene(loaderToLogin.getRoot()));
                siteStage.show();
            });
        });

        EmailField.setOnKeyReleased(event -> {
            if(EmailField.getText().length()>9) {
                Email_Indicator.setStroke(Color.RED);
            }
            else {
                Email_Indicator.setStroke(Color.rgb(87, 166, 218));
            }
        });

        NewPasswordField.setOnKeyReleased(event -> {
            if(NewPasswordField.getText().length()>9) {
                NewPassword_Indicator.setStroke(Color.RED);
            }
            else {
                NewPassword_Indicator.setStroke(Color.rgb(87, 166, 218));
            }
        });

        RepeatPasswordField.setOnKeyReleased(event -> {
            if(RepeatPasswordField.getText().length()>9) {
                RepeatPassword_Indicator.setStroke(Color.RED);
            }
            else {
                RepeatPassword_Indicator.setStroke(Color.rgb(87, 166, 218));
            }
        });


        RestorePasswordButton.setOnAction(event -> {
            mp.stop();
            mp.play();
            loadMeow.setVisible(true);

            mp.setOnEndOfMedia(() -> {
                loadMeow.setVisible(false);
                ExecutionProcedures forgotPassword = new ExecutionProcedures();
                forgotPassword.repeatPassword(EmailField.getText(), NewPasswordField.getText(), RepeatPasswordField.getText());
            });
        });
    }
}
