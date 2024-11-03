package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ConCreate {
    @FXML
    public Button exit;
    @FXML
    public Button createTask;
    @FXML
    public TextField name;
    @FXML
    public TextField email;
    @FXML
    public TextField address;
    @FXML
    public TextField phoneNum;


    //switcher avoids repeating the same code over and over
    public SceneController switcher = new SceneController();

    public String inpName;
    public String inpEmail;
    public String inpAddress;
    public String inpPhoneNum;

    /**
     * goes back to main menu without changing the contacts folder
     * @param event: user clicking the button.
     * @throws IOException: Needed because of the file changing within the method called.
     */
    public void exitCreation(ActionEvent event) throws IOException {
        switcher.changeFile(event,"MainPage.fxml");
    }

    /**
     * creates a contact within the CRM "Database" given user info. Returns to main menu
     * once completed.
     * @param event: user clicking the button.
     * @throws IOException: Needed because of the file changing within the method called.
     */
    public void createContact(ActionEvent event) throws IOException {
        boolean val = isValid(email.getText());

        if(val) {
            String line = "\n" + name.getText() + "|" + email.getText() + "|"
                    + phoneNum.getText() + "|" + address.getText() + "\n$";
            //taken from https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
            Files.write(Paths.get("CRMContacts"), line.getBytes(), StandardOpenOption.APPEND);
            switcher.changeFile(event,"MainPage.fxml");
        }
        else {
            switcher.errorMsg("Invalid email");
            email.clear();
        }
    }

    /**
     * Checks if a given email is valid by pattern matching a given input.
     * If valid, returns true. Otherwise, false.
     * Taken from here: https://www.educba.com/java-email-validation/
     * @param email
     * @return
     */
    public static boolean isValid(String email)
    {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }
}
