package org.organizerguipro.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.organizerguipro.model.Item;
import org.organizerguipro.model.ItemList;

public class OrganizerWindowController
{

    private ObservableList<Item> items;
    private ItemList itemList;

    @FXML
    private TableView itemsTable;
    @FXML
    private TextField newName;
    @FXML
    private TextField itemToRemoveId;
    @FXML
    private TableColumn nameTabCol;
    @FXML
    private TableColumn descriptionTabCol;
    @FXML
    private Button addBtn;
    @FXML
    private TextArea newDescription;
    @FXML
    private TableColumn idTabCol;
    @FXML
    private TextArea oneItemDescription;
    @FXML
    private TextField oneItemName;
    @FXML
    private TextField itemToShowId;
    @FXML
    private Button showItemBtn;
    @FXML
    private Button clearListBtn;
    @FXML
    private Button removeBtn;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        prepareAndSetTable();
        setButtonsOnActionListeners();

        setTextFieldOnlyNumbers(itemToRemoveId);
        setTextFieldOnlyNumbers(itemToShowId);
        setKeyboardShortcuts();
    }

    /**
     * Constructs a new OrganizerWindowController with the specified ItemList.
     *
     * @param itemList The ItemList to use.
     */
    public OrganizerWindowController(ItemList itemList) {
        this.itemList = itemList;
        addSampleData();
        items = FXCollections.observableArrayList(itemList.getList());
    }

    /**
     * Adds some sample data to the list.
     */
    private void addSampleData() {
        itemList.addItem(new Item("name1", "description1"));
        itemList.addItem(new Item("contact1", "tel: 123456789"));
        itemList.addItem(new Item("note", "do not forget to buy milk"));
        itemList.addItem(new Item("name2", "description2"));
    }

    /**
     * Shows an error dialog with the specified message.
     *
     * @param message The message to show.
     */
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Organizer GUI Pro");
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Prepares the table and sets its content.
     */
    private void prepareAndSetTable() {
        itemsTable.setItems(items);

        idTabCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        nameTabCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        descriptionTabCol.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));

        items.addListener(new ListChangeListener<Item>()
                          {
                              @Override
                              public void onChanged(Change<? extends Item> change) {
                                  while (change.next()) {
                                      if (change.wasAdded()) {
                                          itemList.addItem((Item) change.getAddedSubList().get(0));
                                      }
                                      if (change.wasRemoved()) {
                                          for (Item item : change.getRemoved()) {
                                              int id = item.getId();
                                              try {
                                                  itemList.removeItem(id);
                                              } catch (ItemList.NoItemException e) {
                                                  showErrorDialog("No item with id " + id + " found");
                                              }
                                          }
                                      }
                                  }
                              }
                          }

        );

        itemsTable.setEditable(true);

        nameTabCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameTabCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue());
            }
        });

        descriptionTabCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionTabCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setDescription(t.getNewValue());
            }
        });
    }

    /**
     * Sets the action listeners for the buttons.
     */
    private void setButtonsOnActionListeners() {
        addBtn.setOnAction(e -> {
            String name = newName.getText();
            String description = newDescription.getText();
            if (name != null && description != null) {
                items.add(new Item(name, description));
            }
        });

        removeBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(itemToRemoveId.getText());
                boolean found = false;
                for (Item item : items) {
                    if (item.getId() == id) {
                        items.remove(item);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    showErrorDialog("No item with id " + id + " found");
                }
            } catch (NumberFormatException ex) {
                showErrorDialog("Invalid id format");
            }
        });

        showItemBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(itemToShowId.getText());
                oneItemName.setText(itemList.getItem(id).getName());
                oneItemDescription.setText(itemList.getItem(id).getDescription());
            } catch (ItemList.NoItemException ex) {
                showErrorDialog("No item with given id found");
            } catch (NumberFormatException ex) {
                showErrorDialog("Invalid id format");
            }
        });

        clearListBtn.setOnAction(e -> {
            items.clear();
            itemList.clearList();
        });
    }

    /**
     * Sets the specified TextField to accept only numbers.
     *
     * @param textField The TextField to set.
     */
    private void setTextFieldOnlyNumbers(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));
    }

    private void setKeyboardShortcuts() {
        addBtn.setMnemonicParsing(true);
        addBtn.setText("_Add");

        removeBtn.setMnemonicParsing(true);
        removeBtn.setText("_Remove");

        showItemBtn.setMnemonicParsing(true);
        showItemBtn.setText("_Show");

        clearListBtn.setMnemonicParsing(true);
        clearListBtn.setText("_Clear");
    }

}