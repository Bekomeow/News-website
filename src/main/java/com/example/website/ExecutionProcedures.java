package com.example.website;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

class ExecutionProcedures {
    void readFXML(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(path));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage siteStage = new Stage();
        siteStage.setResizable(false);
        siteStage.setScene(new Scene(fxmlLoader.getRoot()));
        siteStage.show();
    }

    void createFolder(String data) {
        File folder = new File("usersFolder");
        File file = new File(folder, "users");
        folder.mkdir();

        try(RandomAccessFile usersFile = new RandomAccessFile(file, "rw")) {
            if(file.exists()) {
                usersFile.writeBytes("username: "+ "*********" + "\n" +
                        "password: " + "*********" + "\n" +
                        "email: " + "*********" + "\n" +
                        "gender: " + "*********" + "\n\n\n");
            }
            usersFile.seek(usersFile.length());
            usersFile.writeBytes(data);
            usersFile.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    String readFile(int position, int size) {
        byte[] bytes;
        File folder = new File("usersFolder");
        File file = new File(folder, "users");
        folder.mkdir();

        try(RandomAccessFile usersFile = new RandomAccessFile(file, "rw")) {

            if(!usersFile.readLine().substring(0, 10).equals("username: ")) {
                throw new FileNotFoundException();
            }
            else {
                bytes = new byte[size];
                usersFile.seek(position);
                usersFile.read(bytes);
                usersFile.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(bytes);
    }

    void AddData(String email, String login, String password, String gender) {
        login = String.format("%-9s", login);
        password = String.format("%-9s", password);
        email = String.format("%-9s", email);
        gender = String.format("%-9s", gender);

        if(email.trim().isEmpty() || login.trim().isEmpty() || password.trim().isEmpty() || gender.trim().isEmpty()) {
            System.out.println("Fill in all the fields");
            readFXML("EmptyError.fxml");
        }
        else if(!email.contains("@")) {
            System.out.println("invalid Email");
            readFXML("invalidEmailError.fxml");
        }
        else if(!CountLines(login, email)) {
            System.out.println("such user already exists");
            readFXML("ExistsError.fxml");
        }
        else {
            checkData(login, password);
        }
        if(!login.equals(password) && CountLines(login, email) && login.length()<10 && password.length()<10 && email.length() < 10 &&
                !email.trim().isEmpty() && !login.trim().isEmpty() && !password.trim().isEmpty() && !gender.trim().isEmpty() && email.contains("@")) {

            String result = "username: "+ login + "\n" +
                    "password: " + password + "\n" +
                    "email: " + email + "\n" +
                    "gender: " + gender + "\n\n\n";

            CountLines(login, email);
            createFolder(result);

            System.out.println("Successful registration");
            readFXML("SuccessfulRegistration.fxml");
        }
    }

    void checkData(String login, String password) {
        if(login.equals(password) && !login.trim().equals("")) {
            System.out.println("Password Matched");
            readFXML("PasswordMatchedError.fxml");
        }
        else if(login.length()>9 || password.length()>9) {
            System.out.println("Login or Password entered incorrect");
            readFXML("LoginError.fxml");
        }
    }

    void logic(String login, String password, Button b) {
//        System.out.println(login);
//        System.out.println(password);
        boolean res = false;
        File folder = new File("usersFolder");
        File file = new File(folder, "users");
        folder.mkdir();
        try (RandomAccessFile file1 = new RandomAccessFile(file, "rw")) {
            readFile(0,1);
//            System.out.println(file.length());
            for (int i = /*10*/87; i < file.length(); i += 77) {
//                System.out.println(readFile(i, 9));
//                System.out.println(readFile(i+20, 9));
                if (readFile(i, 9).trim().equals(login)) {
                    if (readFile(i + 20, 9).trim().equals(password)) res = true;
                    break;
                }
            }

            if (login.isEmpty() || password.isEmpty()) {
                System.out.println("Fill in all the fields");
                readFXML("EmptyError.fxml");
            } else if (res == true) {
                FXMLLoader loaderToNewsPage = new FXMLLoader(HelloApplication.class.getResource("NewsPage.fxml"));

                b.getScene().getWindow().hide();

                try {
                    loaderToNewsPage.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Stage siteStage = new Stage();
                siteStage.setScene(new Scene(loaderToNewsPage.getRoot()));
                siteStage.show();
                System.out.println("File Created");
            } else {
                readFXML("LoginError.fxml");
            }


        } catch (Exception e) {
            readFXML("FileExistsError.fxml");
        }
    }

    boolean CountLines(String login, String email) {
        boolean res = true;
        File folder = new File("usersFolder");
        File file = new File(folder, "users");
        folder.mkdir();
        try (RandomAccessFile file1 = new RandomAccessFile(file, "rw")) {
            for(int i = /*10*/87;i<file.length();i+=77) {

                if(readFile(i, 9).equals(login) || readFile(i+37, 9).equals(email)) {
                    res = false;
//                    System.out.println(readFile(i, 9)+"\n");
                    break;
                }
            }

            int countLines = 0;
            file1.seek(0);
            while(file1.readLine()!=null) {
                countLines++;
            }

            System.out.println("number of lines: " + countLines);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    void repeatPassword(String email, String password1, String password2) {
        int l = password1.length();
        for(int i = l;i<9;i++) password1+=" ";
//        System.out.println(email);
//        System.out.println(password);
        boolean res = false;
        File folder = new File("usersFolder");
        File file = new File(folder, "users");
        folder.mkdir();
        try (RandomAccessFile file1 = new RandomAccessFile(file, "rw")) {

//            System.out.println(file.length());
            for (int i = /*47*/124; i < file.length(); i += 77) {
//                System.out.println(i);
//                System.out.println(readFile(i, 9));
//                System.out.println(readFile(i+20, 9));
                if (readFile(i, 9).trim().equals(email)) {
                    res = true;
                    file1.seek(i - 17);
                    break;
                }
            }


            if (email.trim().isEmpty() || password1.trim().isEmpty() || password2.trim().isEmpty()) {
                System.out.println("Fill in all the fields");
                readFXML("EmptyError.fxml");
            }
//            else if(res == true) {
//                System.out.println("Password was changed successfully");
//                readFXML("passwordСhanged.fxml");
//            }
            else if(res == false) {
                System.out.println("invalid email");
                readFXML("invalidEmailError.fxml");
            }
            else if(!password1.trim().equals(password2.trim())) {
                readFXML("invalidPassword.fxml");
                System.out.println("password entered incorrectly");
            }
            else {
                file1.writeBytes(password1);
                System.out.println("Password was changed successfully");
                readFXML("passwordСhanged.fxml");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}