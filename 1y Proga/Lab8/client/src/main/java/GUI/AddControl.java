package GUI;

import data.Coordinates;
import data.Location;
import data.Route;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class AddControl {

    private Stage stage;
    private Route route;
    private Localization localization;

    @FXML
    public Button add;
    @FXML
    public Button backButton;
    @FXML
    public Label nameLabel;
    @FXML
    public TextField nameField;
    @FXML
    public Label coordXLabel;
    @FXML
    public TextField coordXField;
    @FXML
    public Label coordYLabel;
    @FXML
    public TextField coordYField;
    @FXML
    public Label fromXLabel;
    @FXML
    public TextField fromXField;
    @FXML
    public Label fromYLabel;
    @FXML
    public TextField fromYField;
    @FXML
    public Label fromNameLabel;
    @FXML
    public TextField fromNameField;
    @FXML
    public Label toXLabel;
    @FXML
    public TextField toXField;
    @FXML
    public Label toYLabel;
    @FXML
    public TextField toYField;
    @FXML
    public Label toNameLabel;
    @FXML
    public TextField toNameField;
    @FXML
    public Label distanceLabel;
    @FXML
    public TextField distanceField;

    @FXML
    public void initialize() {
        backButton.setOnMouseClicked(event -> stage.close());
    }

    @FXML
    public void add() {
        nameField.setText(nameField.getText());
        coordXField.setText(coordXField.getText());
        coordYField.setText(coordYField.getText());
        fromXField.setText(fromXField.getText());
        fromYField.setText(fromYField.getText());
        fromNameField.setText(fromNameField.getText());
        toXField.setText(toXField.getText());
        toYField.setText(toYField.getText());
        toNameField.setText(toNameField.getText());
        distanceField.setText(distanceField.getText());

        var errors = new ArrayList<String>();

        String name = nameField.getText();
        if (nameField.getText().isEmpty() || nameField.getText().isBlank()) {
            name = null;
            errors.add("-" + localization.getKeyString("Name") + " " + localization.getKeyString("CannotBeEmpty"));
        }

        int coordX = 0;
        try {
            coordX = Integer.parseInt(coordXField.getText());
        } catch (NumberFormatException e) {
            errors.add("-" + localization.getKeyString("CoordX") + " " + localization.getKeyString("MustBeInteger"));
        }

        Long coordY = null;
        try {
            coordY = Long.parseLong(coordYField.getText());
        } catch (NumberFormatException e) {
            errors.add("-" + localization.getKeyString("CoordY") + " " + localization.getKeyString("MustBeLong"));
        }

        Integer fromX = null;
        try {
            fromX = Integer.parseInt(fromXField.getText());
        } catch (NumberFormatException e) {
            errors.add("-" + localization.getKeyString("FromX") + " " + localization.getKeyString("MustBeInteger"));
        }

        long fromY = 0;
        try {
            fromY = Long.parseLong(fromYField.getText());
        }catch (NumberFormatException e) {
            errors.add("-" + localization.getKeyString("FromY") + " " + localization.getKeyString("MustBeLong"));
        }

        String fromName = fromNameField.getText();
        if (fromNameField.getText().isEmpty() || fromNameField.getText().isBlank()) {
            fromName = null;
            errors.add("-" + localization.getKeyString("FromName") + " " + localization.getKeyString("CannotBeEmpty"));
        }

        Integer toX = null;
        try {
            toX = Integer.parseInt(toXField.getText());
        } catch (NumberFormatException e) {
            errors.add("-" + localization.getKeyString("ToX") + " " + localization.getKeyString("MustBeInteger"));
        }

        long toY = 0;
        try {
            toY = Long.parseLong(toYField.getText());
        } catch (NumberFormatException e) {
            errors.add("-" + localization.getKeyString("ToY") + " " + localization.getKeyString("MustBeLong"));
        }

        String toName = toNameField.getText();
        if (toNameField.getText().isEmpty() || toNameField.getText().isBlank()) {
            toName = null;
            errors.add("-" + localization.getKeyString("ToName") + " " + localization.getKeyString("CannotBeEmpty"));
        }

        float distance = 0;
        try {
            distance = Float.parseFloat(distanceField.getText());
            if (distance <= 0) {
                errors.add("-" + localization.getKeyString("Distance") + " " + localization.getKeyString("MustBeGreaterThanZero"));
            }
        } catch (NumberFormatException e) {
            errors.add("-" + localization.getKeyString("Distance") + " " + localization.getKeyString("MustBeFloat"));
        }

        if (!errors.isEmpty()){
            DialogManager.createAlert(localization.getKeyString("Error"), String.join("\n", errors));
        } else {
            route = new Route(name, new Coordinates(coordX, coordY), ZonedDateTime.now(), new Location(fromX, fromY, fromName), new Location(toX, toY, toName), distance);
            stage.close();
        }
    }

    public void clear(){
        nameField.clear();
        coordXField.clear();
        coordYField.clear();
        fromXField.clear();
        fromYField.clear();
        fromNameField.clear();
        toXField.clear();
        toYField.clear();
        toNameField.clear();
        distanceField.clear();
    }

    public Route getRoute() {
        return route;
    }

    public void changeLanguage() {
        add.setText(localization.getKeyString("Add"));
        nameLabel.setText(localization.getKeyString("Name"));
        coordXLabel.setText(localization.getKeyString("CoordX"));
        coordYLabel.setText(localization.getKeyString("CoordY"));
        fromXLabel.setText(localization.getKeyString("FromX"));
        fromYLabel.setText(localization.getKeyString("FromY"));
        fromNameLabel.setText(localization.getKeyString("FromName"));
        toXLabel.setText(localization.getKeyString("ToX"));
        toYLabel.setText(localization.getKeyString("ToY"));
        toNameLabel.setText(localization.getKeyString("ToName"));
        distanceLabel.setText(localization.getKeyString("Distance"));
    }

    public void show() {
        if (!stage.isShowing()) {
            stage.showAndWait();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public void fill(Route route){
        nameField.setText(route.getName());
        coordXField.setText(String.valueOf(route.getCoordinates().getX()));
        coordYField.setText(String.valueOf(route.getCoordinates().getY()));
        fromXField.setText(String.valueOf(route.getLocationFrom().getX()));
        fromYField.setText(String.valueOf(route.getLocationFrom().getY()));
        fromNameField.setText(route.getLocationFrom().getName());
        toXField.setText(String.valueOf(route.getLocationTo().getX()));
        toYField.setText(String.valueOf(route.getLocationTo().getY()));
        toNameField.setText(route.getLocationTo().getName());
        distanceField.setText(String.valueOf(route.getDistance()));
        add.setText(localization.getKeyString("UpdateById"));
    }


}
