package GUI;

import command.Client;
import command.commands.BasicCommand;
import command.commands.ShowCom;
import command.utility.ResponseStatuses;
import command.utility.Sender;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Random;

public class VisualWindow {
    private boolean refresh;
    private Thread refresher;
    private Stage stage;
    private Localization localization;
    private Sender sender;
    private HashMap<String, Color> colorMap;
    private HashMap<Integer, Label> infoMap;
    private Random random;

    @FXML
    public ImageView backButton;
    @FXML
    public AnchorPane anchor;
    @FXML
    public Line yLine;
    @FXML
    public Line xLine;

    @FXML
    public void initialize() {
        colorMap = new HashMap<>();
        infoMap = new HashMap<>();
        random = new Random();
        infoMap.clear();
        backButton.setOnMouseClicked(event -> {
            refresher.interrupt();
            stage.close();
        });
    }

    private void loadCollection() {
        try{
            BasicCommand showCommand = new ShowCom();
            showCommand.setUser(Client.getUser());
            var response = sender.sendAndReceive(showCommand);
            if (response.getStatus().equals(ResponseStatuses.OK)) {
                Platform.runLater(this::visualise);
            }
        } catch (NullPointerException e) {
            DialogManager.alert("RefreshLost", localization);
        }
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setSender(Sender sender){
        this.sender = sender;
    }

    public void refresh() {
        refresher = new Thread(() -> {
            while (refresh) {
                Platform.runLater(this::loadCollection);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interrupted, Failed to complete operation");
                    break;
                }
            }
        });
        refresher.start();
    }

    public void show(boolean refresh) {
        this.refresh = refresh;
        refresh();
    }

    private void visualise() {
        anchor.getChildren().clear();
        anchor.getChildren().add(backButton);
        anchor.getChildren().add(xLine);
        anchor.getChildren().add(yLine);
        for (var route : Client.getCollection()) {
            var owner = route.getLogin();
            if (!colorMap.containsKey(owner)) {
                var r = random.nextDouble();
                var g = random.nextDouble();
                var b = random.nextDouble();
                if (Math.abs(r - g) + Math.abs(r - b) + Math.abs(b - g) < 0.6) {
                    r += (1 - r) / 1.4;
                    g += (1 - g) / 1.4;
                    b += (1 - b) / 1.4;
                }
                colorMap.put(owner, Color.color(r, g, b));
            }
            var size = Math.min((route.getDistance() + 1) * 5, 60);
            var circle = new Circle(size, colorMap.get(owner));
            circle.setStroke(Color.WHITE);
            circle.setStrokeWidth(3);
            double x = (route.getCoordinates().getX()) * 30 + 300;
            double y = -(route.getCoordinates().getY()) * 20 + 200;
            var id = new Text("id:" + route.getId());
            var info = new Label(localization.getKeyString("Distance") + " " + route.getDistance());
            info.setVisible(false);
            x = x > 600 ? 550 : x;
            x = x < -600 ? -550 : x;
            y = y > 400 ? 350 : y;
            y = y < -400 ? -350 : y;

            circle.setOnMouseEntered(mouseEvent -> {
                id.setVisible(false);
                info.setVisible(true);
                circle.setFill(colorMap.get(owner).brighter());
                circle.toFront();
                info.toFront();
            });

            circle.setOnMouseExited(mouseEvent -> {
                id.setVisible(true);
                info.setVisible(false);
                circle.setFill(colorMap.get(owner));
                id.toBack();
                circle.toBack();
                xLine.toBack();
                yLine.toBack();
            });
            id.setFont(Font.font("DejaVu Sans Bold", size / 2.5));
            info.setFont(Font.font("DejaVu Sans Bold", 15));
            info.setStyle("-fx-text-fill: #C29292;");

            anchor.getChildren().add(circle);
            anchor.getChildren().add(id);

            infoMap.put(route.getId(), info);
            if (!refresh) {
                var path = new Path();
                path.getElements().add(new MoveTo(-500, -150));
                path.getElements().add(new HLineTo(x));
                path.getElements().add(new VLineTo(y));
                id.translateXProperty().bind(circle.translateXProperty().subtract(id.getLayoutBounds().getWidth() / 2));
                id.translateYProperty().bind(circle.translateYProperty().add(id.getLayoutBounds().getHeight() / 4));
                info.translateXProperty().bind(circle.translateXProperty().add(-circle.getRadius() * 1.2));
                info.translateYProperty().bind(circle.translateYProperty().subtract(50));
                var transition = new PathTransition();
                transition.setDuration(Duration.millis(750));
                transition.setNode(circle);
                transition.setPath(path);
                transition.setOrientation(PathTransition.OrientationType.NONE);
                transition.play();
            } else {
                circle.setCenterX(x);
                circle.setCenterY(y);
                info.translateXProperty().bind(circle.centerXProperty().add(circle.getRadius()));
                info.translateYProperty().bind(circle.centerYProperty().subtract(120));
                id.translateXProperty().bind(circle.centerXProperty().subtract(id.getLayoutBounds().getWidth() / 2));
                id.translateYProperty().bind(circle.centerYProperty().add(id.getLayoutBounds().getHeight() / 4));
                var darker = new FillTransition(Duration.millis(750), circle);
                darker.setFromValue(colorMap.get(owner));
                darker.setToValue(colorMap.get(owner).darker().darker());
                var brighter = new FillTransition(Duration.millis(750), circle);
                brighter.setFromValue(colorMap.get(owner).darker().darker());
                brighter.setToValue(colorMap.get(owner));
                var transition = new SequentialTransition(darker, brighter);
                transition.play();
            }
        }
        for (var id : infoMap.keySet()) {
            anchor.getChildren().add(infoMap.get(id));
        }
        stage.showAndWait();
    }

}
