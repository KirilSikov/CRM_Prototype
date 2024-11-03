package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Note: I got a majority of the code on this class from this youtube video:
 * https://www.youtube.com/watch?v=XCgcQTQCfJQ. I dont think they're is a chance
 * that I would have figured out this double cast otherwise.
 * @author Kiril
 *
 */
public class SceneController implements EventHandler<KeyEvent>{
    /**
     * Function changes the loaded FXML file within a running instance of javaFX
     * to a given FXML file name.
     * @param event: This is just to make logScreen work.
     * @param fxml: Name of the FXML file
     * @throws IOException: Usage of the function is going only going to pass in
     * pre-determined files, but this is here to avoid try-catch code.
     */
    public void changeFile(ActionEvent event, String fxml) throws IOException {
        Parent logScreen = FXMLLoader.load(getClass().getResource(fxml));
        Scene chgScreen = new Scene(logScreen);
        chgScreen.setOnKeyPressed(this);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(chgScreen);
        window.show();
        return;
    }

    /**
     * The method generates an error message to be displayed to the user.
     * The majority of the code from this method is taken from:
     * https://www.youtube.com/watch?v=HFAsMWkiLvg (5:31).
     * Note that the error message does not have an associated FXML file. This
     * is simply to reduce the number of files within the project.
     * @param errorMsg: Content of the generated error message
     */
    public void errorMsg(String errorMsg) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Error");
        window.setWidth(400);
        Label label = new Label();
        label.setText(errorMsg);

        Button exit = new Button("Close");

        exit.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(exit);
        layout.setAlignment(Pos.BOTTOM_CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return;
    }

    /**
     * The method generates a confirmation pop-up to be displayed to the user.
     * The majority of the code from this method is taken from:
     * https://www.youtube.com/watch?v=HFAsMWkiLvg (5:31).
     * Note that the error message does not have an associated FXML file. This
     * is simply to reduce the number of files within the project.
     * @param msg: Message to be displayed in the popup
     */
    public void confirmWindow(String msg) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirmation.");
        window.setWidth(300);
        Label label = new Label();
        label.setText(msg);

        Button exit = new Button("No");
        Button proceed = new Button("Yes");

        exit.setOnAction(e -> {
            window.close();
            return;
        });
        proceed.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(30);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().addAll(exit);
        layout.setAlignment(Pos.BOTTOM_LEFT);
        layout.getChildren().addAll(proceed);
        layout.setAlignment(Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return;
    }

    /**
     * handles keyboard input for the user.
     */
    @Override
    public void handle(KeyEvent arg0) {
        //finish later

    }

}
