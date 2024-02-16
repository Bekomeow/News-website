package com.example.website;

import java.io.File;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Forgot_password;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignUpButton;

    @FXML
    private ImageView loadMeow;

    @FXML
    private AnchorPane Pane;

    @FXML
    private Line login_Indicator;

    @FXML
    private Line password_Indicator;

    @FXML
    private CheckBox remmember_me;

    @FXML
    void initialize(MouseEvent event) {

    }

    @FXML
    void initialize() throws FileNotFoundException {

        Pane.setOnKeyPressed(keyEvent -> {
            Admin beko = new Admin();
            switch (keyEvent.getCode()) {
                case DELETE: beko.admin(Pane); break;
            }
        });

        Forgot_password.setOnMouseEntered(event -> {
            Forgot_password.setTextFill(Color.rgb(34, 76, 141));
        });
        Forgot_password.setOnMouseExited(event -> {
            Forgot_password.setTextFill(Color.WHITE);
        });

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

        SignUpButton.setOnAction(event -> {
            mp.stop();
            mp.play();
            loadMeow.setVisible(true);

            mp.setOnEndOfMedia(() -> {
                loadMeow.setVisible(false);
                FXMLLoader loaderToSignUp = new FXMLLoader(HelloApplication.class.getResource("SignUpPage.fxml"));

                SignUpButton.getScene().getWindow().hide();

                try {
                    loaderToSignUp.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Stage siteStage = new Stage();
                siteStage.setScene(new Scene(loaderToSignUp.getRoot()));
                siteStage.show();
            });
        });

        Forgot_password.setOnMouseClicked(event -> {
            mp.stop();
            mp.play();
            loadMeow.setVisible(true);

            mp.setOnEndOfMedia(() -> {
                loadMeow.setVisible(false);
                FXMLLoader loaderToForgotPasswordPage = new FXMLLoader(HelloApplication.class.getResource("ForgotPasswordPage.fxml"));

                Forgot_password.getScene().getWindow().hide();

                try {
                    loaderToForgotPasswordPage.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Stage siteStage = new Stage();
                siteStage.setScene(new Scene(loaderToForgotPasswordPage.getRoot()));
                siteStage.show();
            });
        });

        LoginField.setOnKeyReleased(event -> {
            if(LoginField.getText().length()>9) {
                login_Indicator.setStroke(Color.RED);
            }
            else {
                    login_Indicator.setStroke(Color.rgb(87, 166, 218));
            }
        });

        PasswordField.setOnKeyReleased(event -> {
            if(PasswordField.getText().length()>9) {
                password_Indicator.setStroke(Color.RED);
            }
            else {
                password_Indicator.setStroke(Color.rgb(87, 166, 218));
            }
        });

        File folder = new File("usersFolder");
        File file1 = new File(folder, "users");
        folder.mkdir();
        RandomAccessFile file = new RandomAccessFile(file1, "rw");

        LoginButton.setOnAction(event -> {
            mp.stop();
            mp.play();
            loadMeow.setVisible(true);

            mp.setOnEndOfMedia(() -> {
                loadMeow.setVisible(false);
                ExecutionProcedures login = new ExecutionProcedures();
                login.logic(LoginField.getText(), PasswordField.getText(), LoginButton);
                if(remmember_me.isSelected() && LoginField.getText().trim().length()<10 && PasswordField.getText().trim().length()<10) {
                    String result = "username: "+ String.format("%-9s", LoginField.getText()) + "\n" +
                            "password: " + String.format("%-9s", PasswordField.getText()) + "\n" +
                            "email: " + "*********" + "\n" +
                            "gender: " + "*********" + "\n\n\n";
                    try {
                        file.seek(0);
                        file.writeBytes(result);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });

        remmember_me.setOnAction(event -> {
            mp.stop();
            mp.play();
            String login = String.format("%-9s", LoginField.getText());
            String password = String.format("%-9s", PasswordField.getText());

            try {
                String result = "username: "+ "*********" + "\n" +
                        "password: " + "*********" + "\n" +
                        "email: " + "*********" + "\n" +
                        "gender: " + "*********" + "\n\n\n";

                if(remmember_me.isSelected() && LoginField.getText().trim().length()<10 && PasswordField.getText().trim().length()<10) {
                    result = "username: "+ login + "\n" +
                            "password: " + password + "\n" +
                            "email: " + "*********" + "\n" +
                            "gender: " + "*********" + "\n\n\n";
                }
                file.seek(0);
                file.writeBytes(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });



        if(!readFile(10, 9).equals("*********") && readFile(0, 10).equals("username: ")) {
            remmember_me.setSelected(true);
            LoginField.setText(readFile(10, 9).trim());
            PasswordField.setText(readFile(30, 9).trim());
        }
    }

    String readFile(int position, int size) {
        byte[] bytes;

        File folder = new File("usersFolder");
        File file1 = new File(folder, "users");
        folder.mkdir();
        try(RandomAccessFile usersFile = new RandomAccessFile(file1, "rw")) {
            bytes = new byte[size];
            usersFile.seek(position);
            usersFile.read(bytes);
            usersFile.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(bytes);
    }
}



