package GUI;

import command.Client;
import command.commands.*;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.ScriptExecutor;
import command.utility.Sender;
import data.Coordinates;
import data.Route;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.text.MessageFormat;
import java.time.ZonedDateTime;
import java.util.*;

import static command.Client.setCollection;

public class MainApplication {
    private Localization localization;
    private Sender sender;
    private Runnable authCallback;
    private AddControl addController;
    private VisualWindow visualWindow;
    private int waitTime = 5000;
    private Thread refresher;
    private final HashMap<String, Locale> localeMap = new HashMap<>() {{
        put("Русский", new Locale("ru"));
        put("Eesti", new Locale("ee"));
        put("Hrvatski", new Locale("hr"));
        put("English", new Locale("au"));
    }};
    private Stage stage;
    private volatile boolean isRefreshing = false;


    @FXML
    public Label userName;
    @FXML
    public Button infoButton;
    @FXML
    public Button executeScriptButton;
    @FXML
    public Button clearButton;
    @FXML
    public Button addButton;
    @FXML
    public Button countLessThanDistanceButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button helpButton;
    @FXML
    public Button removeByIDButton;
    @FXML
    public Button showButton;
    @FXML
    public Button updateByIdButton;
    @FXML
    public Button maxByCoordinateButton;
    @FXML
    public Button minByNameButton;
    @FXML
    public ComboBox<String> languageComboBox;
    @FXML
    public TextField distanceField;
    @FXML
    public TableView<Route> tableTable;
    @FXML
    public TableColumn<Route, Integer> idColumn;
    @FXML
    public TableColumn<Route, String> nameColumn;
    @FXML
    public TableColumn<Route, Integer> coordinatesXColumn;
    @FXML
    public TableColumn<Route, Long> coordinatesYColumn;
    @FXML
    public TableColumn<Route, String> creationDateColumn;
    @FXML
    public TableColumn<Route, Integer> fromXColumn;
    @FXML
    public TableColumn<Route, Long> fromYColumn;
    @FXML
    public TableColumn<Route, String> fromNameColumn;
    @FXML
    public TableColumn<Route, Integer> toXColumn;
    @FXML
    public TableColumn<Route, Long> toYColumn;
    @FXML
    public TableColumn<Route, String> toNameColumn;
    @FXML
    public TableColumn<Route, Float> distanceColumn;

    @FXML
    public void initialize() {
        languageComboBox.setItems(FXCollections.observableArrayList(localeMap.keySet()));
        languageComboBox.setValue(Client.getCurrentLanguage());
        languageComboBox.setStyle("-fx-font: 14px \"DejaVu Sans Bold\";");
        languageComboBox.setOnAction(event -> {
            localization.setBundle(ResourceBundle.getBundle("GUI/locals/gui", localeMap.get(languageComboBox.getValue())));
            Client.setCurrentLanguage(languageComboBox.getValue());
            changeLanguage();
        });
        idColumn.setCellValueFactory(route -> new SimpleIntegerProperty(route.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(route -> new SimpleStringProperty(route.getValue().getName()));
        coordinatesXColumn.setCellValueFactory(route -> new SimpleIntegerProperty(route.getValue().getCoordinateX()).asObject());
        coordinatesYColumn.setCellValueFactory(route -> new SimpleLongProperty(route.getValue().getCoordinateY()).asObject());
        creationDateColumn.setCellValueFactory(route -> new SimpleStringProperty(route.getValue().getCreationDate().toString()));
        fromXColumn.setCellValueFactory(route -> new SimpleIntegerProperty(route.getValue().getLocationFrom().getX()).asObject());
        fromYColumn.setCellValueFactory(route -> new SimpleLongProperty(route.getValue().getLocationFrom().getY()).asObject());
        fromNameColumn.setCellValueFactory(route -> new SimpleStringProperty(route.getValue().getLocationFrom().getName()));
        toXColumn.setCellValueFactory(route -> new SimpleIntegerProperty(route.getValue().getLocationTo().getX()).asObject());
        toYColumn.setCellValueFactory(route -> new SimpleLongProperty(route.getValue().getLocationTo().getY()).asObject());
        toNameColumn.setCellValueFactory(route -> new SimpleStringProperty(route.getValue().getLocationTo().getName()));
        distanceColumn.setCellValueFactory(route -> new SimpleFloatProperty(route.getValue().getDistance()).asObject());
        distanceField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\S*")) {
                distanceField.setText(oldValue);
            }
        });

        tableTable.getSortOrder().add(idColumn);
        idColumn.setComparator(Integer::compareTo);
        tableTable.setRowFactory(tableView -> {
            var row = new TableRow<Route>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && !row.isEmpty()) {
                    doubleClickUpdate(row.getItem());
                }
            });
            return row;
        });
    }

    @FXML
    public void info() {
        try {
            BasicCommand info = new InfoCom();
            info.setUser(Client.getUser());
            var response = sender.sendAndReceive(info);
            var message = MessageFormat.format(localization.getKeyString("InfoResult"), response.getType(), response.getAmountOfElements(), localization.getDate(response.getInitializationDate()));
            DialogManager.createAlert(localization.getKeyString("Info"), message);
        } catch (NullPointerException e) {
            DialogManager.alert("RefreshLost", localization);
        }
    }

    @FXML
    public void executeScript() {
        String filepath;
        Optional<String> path = DialogManager.getFilePath(localization);
        if (path.isPresent()) {
            filepath = path.orElse("");
            try {
                File file = new File(filepath);
                if (file.exists()) {
                    ScriptExecutor se = new ScriptExecutor(file).readScript();
                    ArrayList<BasicCommand> commands = se.getCommandList();
                    if (!commands.isEmpty()) {
                        var errors = new ArrayList<String>();
                        commands.forEach(command -> {
                            command.setUser(Client.getUser());
                            var response = sender.sendAndReceive(command);
                            if (!response.getStatus().equals(ResponseStatuses.OK)) {
                                errors.add("-" + localization.getKeyString("CommandExecError") + ". " + localization.getKeyString("CheckScriptErr"));
                            }
                        });
                        if (!errors.isEmpty()) {
                            DialogManager.alert(String.join("\n", errors), localization);
                        } else {
                            DialogManager.info("ScriptExecutionSuc", localization);
                        }
                    } else {
                        DialogManager.alert("EmptyFileErr", localization);
                    }
                } else {
                    DialogManager.alert("FileNotFoundException", localization);
                }
            } catch (Exception e) {
                DialogManager.alert("ScriptExecutionErr", localization);
            }
        }
    }

    @FXML
    public void clear() {
        try {
            BasicCommand clear = new ClearCom();
            clear.setUser(Client.getUser());
            var response = sender.sendAndReceive(clear);
            if (response.getStatus().equals(ResponseStatuses.OK)) {
                DialogManager.createAlert(localization.getKeyString("Clear"), localization.getKeyString("ClearSuc"));
            } else {
                DialogManager.createAlert(localization.getKeyString("Clear"), localization.getKeyString("ClearFail"));
            }
            loadCollection();
        } catch (NullPointerException e) {
            DialogManager.alert("RefreshLost", localization);
        }
    }

    @FXML
    public void countLessThanDistance() {
        int distance;
        try {
            var input = distanceField.getText();
            if (!(input.isEmpty() || input.isBlank())) {
                distance = Integer.parseInt(input);
                if (distance <= 0) {
                    DialogManager.createAlert(localization.getKeyString("CountLessThanDistance"), localization.getKeyString("Distance") + " " + localization.getKeyString("MustBeGreaterThanZero"));
                } else {
                    var countCommand = new CountLessThanDistanceCom(distance);
                    countCommand.setUser(Client.getUser());
                    var response = sender.sendAndReceive(countCommand);
                    DialogManager.createAlert(localization.getKeyString("CountLessThanDistance"), localization.getKeyString("Number") + response.getResponse());
                }
            } else {
                DialogManager.alert("EnterDistance", localization);
            }
        } catch (NumberFormatException e) {
            DialogManager.createAlert(localization.getKeyString("CountLessThanDistance"), localization.getKeyString("Distance") + " " + localization.getKeyString("MustBeNumeric"));
        }
    }

    @FXML
    public void add() {
        addController.clear();
        addController.show();
        var route = addController.getRoute();
        if (route != null) {
            try {
                var addCommand = new AddCom(route);
                var response = sender.sendAndReceive(addCommand);
                if (response.getStatus().equals(ResponseStatuses.OK)) {
                    DialogManager.createAlert(localization.getKeyString("Add"), localization.getKeyString("AddResult"));
                    loadCollection();
                }
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localization);
            }
        }
    }

    @FXML
    public void updateById() {
        int ID;
        Route route;
        Optional<Integer> id = DialogManager.getId(localization);
        if (id.isPresent()) {
            try {
                ID = id.orElse(0);
                route = Client.getCollection().stream()
                        .filter(m -> m.getId() == ID)
                        .findAny()
                        .orElse(null);
                if (route == null) throw new NoSuchElementException();
                addController.fill(route);
                addController.show();

                var updated = addController.getRoute();
                if (updated != null) {
                    updated.setId((long) route.getId());
                    var update = new UpdateByIdCom(updated);
                    update.setUser(Client.getUser());
                    var response = sender.sendAndReceive(update);
                    if (response.getStatus().equals(ResponseStatuses.OK)) {
                        DialogManager.createAlert(localization.getKeyString("UpdateId"), localization.getKeyString("UpdateSuc"));
                    } else if (response.getStatus().equals(ResponseStatuses.ERROR)) {
                        DialogManager.createAlert(localization.getKeyString("UpdateId"), localization.getKeyString("UpdateErr"));
                    } else {
                        DialogManager.alert("BadOwnerError", localization);
                    }
                    loadCollection();
                }
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localization);
            } catch (IllegalArgumentException e) {
                DialogManager.alert("BadOwnerError", localization);
            } catch (NoSuchElementException e) {
                DialogManager.createAlert(localization.getKeyString("Error"), localization.getKeyString("NoSuchElement"));
            }
        }
    }

    @FXML
    public void removeById() {
        int Id;
        Optional<Integer> id = DialogManager.getId(localization);
        if (id.isPresent()) {
            Id = id.orElse(0);
            try {
                var remove = new RemoveByIdCom(Id);
                remove.setUser(Client.getUser());
                var response = sender.sendAndReceive(remove);
                if (response.getStatus().equals(ResponseStatuses.OK)) {
                    DialogManager.info("RemoveByIDSuc", localization);
                } else if (response.getStatus().equals(ResponseStatuses.ERROR)) {
                    DialogManager.alert("UpdateErr", localization);
                } else {
                    DialogManager.alert("BadOwnerError", localization);
                }
                loadCollection();
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localization);
            }
        }
    }

    @FXML
    public void visualise() {
        visualWindow.show(true);
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    public void doubleClickUpdate(Route item) {
        doubleClickUpdate(item, true);
    }

    private void doubleClickUpdate(Route route, boolean ignore) {
        if (ignore && !Objects.equals(route.getLogin(), Client.getUser().getLogin())) return;

        addController.fill(route);
        addController.show();

        var updatedProduct = addController.getRoute();
        if (updatedProduct != null) {
            updatedProduct.setId((long) route.getId());
            updatedProduct.setUserlogin(Client.getUser().getLogin());

            try {
                var update = new UpdateByIdCom(updatedProduct);
                update.setUser(Client.getUser());
                var response = sender.sendAndReceive(update);
                if (response.getStatus().equals(ResponseStatuses.OK)) {
                    DialogManager.createAlert(localization.getKeyString("UpdateId"), localization.getKeyString("UpdateSuc"));
                } else if (response.getStatus().equals(ResponseStatuses.ERROR)) {
                    DialogManager.createAlert(localization.getKeyString("UpdateId"), localization.getKeyString("UpdateErr"));
                } else {
                    DialogManager.alert("BadOwnerError", localization);
                }
                loadCollection();
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localization);
            }
        }
    }


    public void setAuthCallback(Runnable authCallback) {
        this.authCallback = authCallback;
    }

    public void setContext(Sender sender, Localization localization, Stage stage) {
        this.sender = sender;
        this.localization = localization;
        this.stage = stage;

        languageComboBox.setValue(Client.getCurrentLanguage());
        localization.setBundle(ResourceBundle.getBundle("GUI/locals/gui", localeMap.get(Client.getCurrentLanguage())));
        changeLanguage();

        userName.setText(Client.getUser().getLogin());
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
    }

    private void changeLanguage() {
            infoButton.setText(localization.getKeyString("Info"));
            executeScriptButton.setText(localization.getKeyString("ExecuteScript"));
            clearButton.setText(localization.getKeyString("Clear"));
            addButton.setText(localization.getKeyString("Add"));
            countLessThanDistanceButton.setText(localization.getKeyString("CountLessThanDistance"));
            exitButton.setText(localization.getKeyString("Exit"));
            helpButton.setText(localization.getKeyString("Help"));
            removeByIDButton.setText(localization.getKeyString("RemoveById"));
            showButton.setText(localization.getKeyString("Visualise"));
            updateByIdButton.setText(localization.getKeyString("UpdateId"));
            maxByCoordinateButton.setText(localization.getKeyString("MaxByCoordinate"));
            minByNameButton.setText(localization.getKeyString("MinByName"));
            userName.setText(Client.getUser().getLogin());
            addController.changeLanguage();
            loadCollection();
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    private void setCollection(LinkedList<Route> collection) {
        if (!SetComparator.compare(collection, Client.getCollection())) {
            tableTable.setItems(FXCollections.observableArrayList(collection));
            tableTable.getSortOrder().add(idColumn);
            idColumn.setComparator(Integer::compareTo);
            Client.setCollection(collection);
        }
    }

    private void loadCollection() {
        try {
            BasicCommand showCommand = new ShowCom();
            showCommand.setUser(Client.getUser());
            var response = sender.sendAndReceive(showCommand);
            if (response.getStatus().equals(ResponseStatuses.OK)) {
                setCollection((LinkedList<Route>) response.getCollection());
            }
        } catch (NullPointerException e) {
            DialogManager.alert("RefreshLost", localization);
        }
    }

    public void refresh() {
        refresher = new Thread(() -> {
            while (isRefreshing()) {
                Platform.runLater(this::loadCollection);
                try {
                    Thread.sleep(getWaitTime());
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interrupted, Failed to complete operation");
                    break;
                }
            }
        });
        refresher.start();
    }

    private int getWaitTime() {
        return this.waitTime;
    }

    private void stopRefreshing() {
        refresher.interrupt();
    }

    public void setAddController(AddControl addControl) {
        this.addController = addControl;
        addController.changeLanguage();
    }

    public void setVisualWindow(VisualWindow visualWindow) {
        this.visualWindow = visualWindow;
    }

}
