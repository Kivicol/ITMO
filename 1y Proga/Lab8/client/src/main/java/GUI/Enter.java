package GUI;

import command.Client;
import command.commands.RegistrationCom;
import command.utility.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class Enter {
    private Runnable callback;
    private Localization localization;
    private Sender sender;
    private final HashMap<String, Locale> localeMap = new HashMap<>() {{
        put("Русский", new Locale("ru"));
        put("Eesti", new Locale("ee"));
        put("Hrvatski", new Locale("hr"));
        put("English", new Locale("au"));
    }};


    @FXML
    private Label loginTitle;
    @FXML
    private TextField loginField;
    @FXML
    private Label passwordTitle;
    @FXML
    private TextField passwordField;
    @FXML
    private Button authButton;
    @FXML
    private Button regButton;
    @FXML
    public ComboBox<String> languageComboBox;

    public void start(){
        languageComboBox.setItems(FXCollections.observableArrayList(localeMap.keySet()));
        languageComboBox.setValue(Client.getCurrentLanguage());
        languageComboBox.setStyle("-fx-font: 14px \"DejaVu Sans Bold\";");
        languageComboBox.setOnAction(event -> {
            var newLang = languageComboBox.getValue();
            localization.setBundle(ResourceBundle.getBundle("GUI/locals/gui", localeMap.get(newLang)));
            Client.setCurrentLanguage(newLang);
            changeLanguage();
        });
        passwordField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\S*")) {
                passwordField.setText(oldValue);
            }
        });
    }
    @FXML
    public void authentification() {
        try {
            if (loginField.getText().isEmpty() || passwordField.getText().isEmpty()){
                throw new IllegalArgumentException();
            }
            var user = new UserData(loginField.getText(), passwordField.getText(), true);
            var regCommand = new RegistrationCom(user);
            var request = new Request(regCommand);
            sender.send(Serializer.serialize(request));
            var response = sender.receive();
            if (response.getStatus().equals(ResponseStatuses.OK)){
                Client.setUser(request.getCommandName().getUser());
                Client.setCurrentLanguage(languageComboBox.getValue());
                callback.run();
            } else {
                DialogManager.alert("SignInError", localization);
            }
        } catch (IOException | ClassNotFoundException e) {
            DialogManager.alert("RefreshLost", localization);
        } catch (IllegalArgumentException e){
            DialogManager.alert("EmptyFieldsError", localization);
        }
    }
    @FXML
    public void registration() {
        try {
            if (loginField.getText().isEmpty() || loginField.getText().length() > 40 || passwordField.getText().isEmpty()){
                throw new IllegalArgumentException();
            }
            var user = new UserData(loginField.getText(), passwordField.getText(), false);
            var regCommand = new RegistrationCom(user);
            var request = new Request(regCommand);
            sender.send(Serializer.serialize(request));
            var response = sender.receive();
            if (response.getStatus().equals(ResponseStatuses.OK)){
                Client.setUser(request.getCommandName().getUser());
                Client.setCurrentLanguage(languageComboBox.getValue());
                callback.run();
            } else {
                DialogManager.alert("UserAlreadyExists", localization);
            }
        } catch (IOException | ClassNotFoundException e) {
            DialogManager.alert("RefreshLost", localization);
        } catch (IllegalArgumentException e){
            DialogManager.alert("RegEmptyFields", localization);
        }
    }
    public void changeLanguage() {
        loginTitle.setText(localization.getKeyString("LoginField"));
        passwordTitle.setText(localization.getKeyString("PasswordField"));
        authButton.setText(localization.getKeyString("SignUpButton"));
        regButton.setText(localization.getKeyString("CreateAccountButton"));
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setLocalizer(Localization localization) {
        this.localization = localization;
    }


}
