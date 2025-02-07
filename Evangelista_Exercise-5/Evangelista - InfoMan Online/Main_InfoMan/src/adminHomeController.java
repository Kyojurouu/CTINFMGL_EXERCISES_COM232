import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class adminHomeController {
    @FXML
    Label adminWelcome;

    public void displayName(String uname) {
        adminWelcome.setText("Welcome, " + uname + "!");
    }


}

