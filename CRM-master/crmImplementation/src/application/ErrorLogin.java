package application;

import java.io.IOException;

import javafx.event.ActionEvent;

public class ErrorLogin {
    SceneController switcher = new SceneController();

    public void exitCreation(ActionEvent event) throws IOException {
        switcher.changeFile(event,"application.login.fxml");
    }
}
