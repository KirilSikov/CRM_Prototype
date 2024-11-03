package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConPgController implements Initializable{

    @FXML
    private TableView<Contact> taskList;
    @FXML
    private TableColumn<Contact,String> tasks;

    SceneController switcher = new SceneController();
    /**
     * goes back to main menu without changing the contacts folder
     * @param event: user clicking the button.
     * @throws IOException: Needed because of the file changing within the method called.
     */
    public void exitPage(ActionEvent event) throws IOException {
        switcher.changeFile(event,"MainPage.fxml");
    }

    /**
     * goes to the task creation FXML file.
     * @param event: Button pressed.
     * @throws IOException
     */
    public void createTask(ActionEvent event) throws IOException {
        switcher.changeFile(event,"TaskCreation.fxml");
    }

    /**
     * Produces a pop-up to confirm deletion and then deletes the selected column
     * from the table.
     * NOTE: the pop-up is not working properly. At the moment, the function will
     * delete the selected item either way.
     * @param event: Button being pressed.
     * @throws IOException
     */
    public void deleteTask(ActionEvent event) throws IOException {
        switcher.confirmWindow("Are you sure you want to delete this task?");
        taskList.getItems().removeAll(taskList.getSelectionModel().getSelectedItem());
    }

    /**
     * Initializes the table present in the contact page.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tasks.setCellValueFactory(new PropertyValueFactory<Contact,String>("contactName"));

        taskList.setItems(getCon());
        taskList.autosize();
    }

    /**
     * Currently fills the table with dummy data. In the future, this function would
     * process the task array, instead of grabbing a simple string.
     * @return
     */
    private ObservableList<Contact> getCon() {
        ObservableList<Contact> lst = FXCollections.observableArrayList();
        lst.add(new Contact("testing task","b","c","d"));
        lst.add(new Contact("testier task","b","c","d"));


        return lst;
    }
}
