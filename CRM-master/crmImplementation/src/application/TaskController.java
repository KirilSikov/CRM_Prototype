package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TaskController {

    @FXML
    public Button exit;
    @FXML
    public Button createTask;


    //switcher avoids repeating the same code over and over
    public SceneController switcher = new SceneController();
    /**
     * goes back to main menu without changing the contacts folder
     * @param event: user clicking the button.
     * @throws IOException: Needed because of the file changing within the method called.
     */
    public void exitCreation(ActionEvent event) throws IOException {
        switcher.changeFile(event,"ContactsPage.fxml");
    }

    /**
     * goes back to main menu without changing the contacts folder
     * @param event: user clicking the button.
     * @throws IOException: Needed because of the file changing within the method called.
     */
    public void createTask(ActionEvent event) throws IOException {
        switcher.changeFile(event,"ContactsPage.fxml");
    }
}
