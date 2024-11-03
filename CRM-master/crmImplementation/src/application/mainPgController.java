package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class mainPgController implements Initializable{

    @FXML
    private Button conCreate;
    @FXML
    private Button exit;
    @FXML
    private Button delButton;
    @FXML
    private Button sel1;
    @FXML
    private Button sel2;
    @FXML
    private Button sel3;
    @FXML
    private Button sel4;
    @FXML
    private Button sel5;


    @FXML
    private TableView<Contact> conList;
    @FXML
    private TableColumn<Contact, String> email;
    @FXML
    private TableColumn<Contact, String> name;
    @FXML
    private TableColumn<Contact, String> phoneNum;

    SceneController switcher = new SceneController();

    /**
     * The function switches to the contact page of the selected contact.
     * NOTE: it only goes to a dummy page for the prototype.
     * @param event: button being pressed.
     * @throws IOException
     */
    public void conSelect(ActionEvent event) throws IOException {
        switcher.changeFile(event,"ContactsPage.fxml");
    }


    /**
     * Initializes the table with the information from the "database".
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setCellValueFactory(new PropertyValueFactory<Contact,String>("contactName"));
        email.setCellValueFactory(new PropertyValueFactory<Contact,String>("contactEmail"));
        phoneNum.setCellValueFactory(new PropertyValueFactory<Contact,String>("contactPhoneNum"));

        conList.setItems(getCon());
        conList.autosize();
    }

    /**
     * Collects the data within the "database" and consolidates it into an object. Then,
     * it passes the object back to the caller.
     * @return
     */
    private ObservableList<Contact> getCon() {
        ObservableList<Contact> lst = FXCollections.observableArrayList(tableData());

        return lst;
    }

    /**
     * tableData goes through the "database" and processes the data into a datastructure.
     * Once EOF is reached, it passes this object back to the caller.
     * @return
     */
    public List<Contact> tableData() {
        List<Contact> tst = new ArrayList<Contact>();
        FileInputStream loginRead;
        try {
            loginRead = new FileInputStream("CRMContacts");
            Scanner fileRead = new Scanner(loginRead);
            while(fileRead.hasNext()) {
                Contact data = new Contact();
                data = data.grabContact(fileRead);
                tst.add(data);
            }
            fileRead.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tst;
    }

    /**
     * Changes the screen to the FXML file for contact creation.
     * @param event: Button being pressed.
     * @throws IOException
     */
    @FXML
    public void conCreation(ActionEvent event) throws IOException {
        switcher.changeFile(event,"ContactCreation.fxml");
    }

    /**
     * Produces a pop-up to confirm deletion and then deletes the selected column
     * from the table.
     * NOTE: the pop-up is not working properly. At the moment, the function will
     * delete the selected item either way.
     * @param event: Button being pressed.
     * @throws IOException
     */
    @FXML
    public void deleteContact(ActionEvent event) throws IOException {
        switcher.confirmWindow("Are you sure you want to delete this contact?");
        conList.getItems().removeAll(conList.getSelectionModel().getSelectedItem());
    }

    /**
     * Changes the screen to the FXML file for login.
     * @param event: Button being pressed.
     * @throws IOException
     */
    @FXML
    public void logout(ActionEvent event) throws IOException {
        switcher.changeFile(event,"login.fxml");
    }
}