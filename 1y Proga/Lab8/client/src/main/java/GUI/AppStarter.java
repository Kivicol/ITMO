package GUI;

import command.utility.Sender;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;

public class AppStarter extends Application{

    /**
     * Simply the main class
     * @author Kivicol
     */

    private static final InetAddress serverAddress;
    private static Localization localization;
    private static Sender sender;
    private static Stage mainStage;

    static {
        try {
            serverAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static final int serverPort = 1658;


    public static void main(String[] args) {
        try {
            sender = new Sender(serverAddress, serverPort);
            launch(args);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }


    // POSSIBLE VM OPTIONS:
    //
    // --module-path "C:\Program Files (x86)\Java\javafx-sdk-22.0.1\lib" --add-modules javafx.controls,javafx.fxml

    @Override
    public void start(Stage stage) throws IOException {
        localization = new Localization(ResourceBundle.getBundle("gui_ru", new Locale("ru", "RU")));
        mainStage = stage;
        authStage();
    }
    private void authStage(){
        var authLoader = new FXMLLoader(getClass().getResource("enter.fxml"));
        Parent authRoot = loadFxml(authLoader);
        Enter enter = authLoader.getController();
        enter.setCallback(this::startMain);
        enter.setSender(sender);
        enter.setLocalizer(localization);
        mainStage.setScene(new Scene(authRoot));
        mainStage.setTitle(localization.getKeyString("Routes"));
        mainStage.setResizable(false);
        mainStage.show();
    }

    private Parent loadFxml(FXMLLoader loader) {
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            System.exit(1);
        }
        return parent;
    }

    private void startMain() {
        var mainLoader = new FXMLLoader(getClass().getResource("GUI/Main.fxml"));
        var addLoader = new FXMLLoader(getClass().getResource("GUI/AddControl.fxml"));
        var visLoader = new FXMLLoader(getClass().getResource("GUI/VisualWindow.fxml"));

        var mainRoot = loadFxml(mainLoader);
        var addRoot = loadFxml(addLoader);
        var visRoot = loadFxml(visLoader);

        var addScene = new Scene(addRoot);
        var addStage = new Stage();
        addStage.setScene(addScene);
        addStage.setResizable(false);
        addStage.setTitle(localization.getKeyString("Routes"));
        AddControl addController = addLoader.getController();
        addController.setStage(addStage);
        addController.setLocalization(localization);

        var visScene = new Scene(visRoot);
        var visStage = new Stage();
        visStage.setScene(visScene);
        visStage.setResizable(false);
        visStage.setTitle(localization.getKeyString("Routes"));
        VisualWindow visualWindow = visLoader.getController();
        visualWindow.setLocalization(localization);
        visualWindow.setSender(sender);
        visualWindow.setStage(visStage);

        MainApplication mainApp = mainLoader.getController();
        mainApp.setAddController(addController);
        mainApp.setVisualWindow(visualWindow);
        mainApp.setContext(sender, localization, mainStage);
        mainApp.setAuthCallback(this::authStage);
        mainStage.setScene(new Scene(mainRoot));
        mainApp.setRefreshing(true);
        mainApp.refresh();
        mainStage.show();
    }

}

