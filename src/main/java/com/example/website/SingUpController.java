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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EmailField;

    @FXML
    private Line Email_Indicator;

    @FXML
    private RadioButton Female;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField LoginField;

    @FXML
    private Line Login_Indicator;

    @FXML
    private RadioButton Male;

    @FXML
    private RadioButton Other;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Line Password_Indicator;

    @FXML
    private Button SignUpButton;

    @FXML
    private ImageView loadMeow;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    void initialize() {
        LoginButton.setOnMouseEntered(event -> {
            LoginButton.setStyle("-fx-background-color: #224C8D; -fx-background-radius: 50");
        });
        LoginButton.setOnMouseExited(event -> {
            LoginButton.setStyle("-fx-background-radius: 50; -fx-background-color: #57A6DA");
        });

        SignUpButton.setOnMouseEntered(event -> {
            SignUpButton.setStyle("-fx-background-color: #224C8D; -fx-background-radius: 50");
        });
        SignUpButton.setOnMouseExited(event -> {
            SignUpButton.setStyle("-fx-background-radius: 50; -fx-background-color: #57A6DA");
        });

        File mediaFile = new File("C:\\Users\\BEKZHAN\\IdeaProjects\\Online news website\\src\\main\\resources\\Sound\\buttonSound.mp3");
        Media media = new Media(mediaFile.toURI().toString());
        MediaPlayer mp = new MediaPlayer(media);

        LoginButton.setOnAction(event -> {
            mp.stop();
            mp.play();
            loadMeow.setVisible(true);

            mp.setOnEndOfMedia(() -> {
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

        LoginField.setOnKeyReleased(event -> {
            if(LoginField.getText().length()>9) {
                Login_Indicator.setStroke(Color.RED);
            }
            else {
                Login_Indicator.setStroke(Color.rgb(87, 166, 218));
            }
        });

        PasswordField.setOnKeyReleased(event -> {
            if(PasswordField.getText().length()>9) {
                Password_Indicator.setStroke(Color.RED);
            }
            else {
                Password_Indicator.setStroke(Color.rgb(87, 166, 218));
            }
        });

        final String[] gender = {""};

        Male.setOnAction(event -> gender[0] = "Male");
        Female.setOnAction(event -> gender[0] = "Female");
        Other.setOnAction(event -> gender[0] = "Other");

        SignUpButton.setOnAction(event -> {
            mp.stop();
            mp.play();
            loadMeow.setVisible(true);

            mp.setOnEndOfMedia(() -> {
                loadMeow.setVisible(false);
                ExecutionProcedures signUp = new ExecutionProcedures();
                signUp.AddData(EmailField.getText(), LoginField.getText(), PasswordField.getText(), gender[0]);
            });
        });
    }
}

