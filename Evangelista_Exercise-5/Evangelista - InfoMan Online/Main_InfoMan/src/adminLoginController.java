import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class adminLoginController {

    @FXML
    Label userNameID;

    @FXML
    Label passwordID;

    @FXML
    TextField userNameField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button loginButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginButtonHandler(ActionEvent event) throws IOException {

        String uname = userNameField.getText();
        String pword = passwordField.getText();


        if (DatabaseHandler.validateLogin(uname, pword)) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminHome.fxml"));

            root = loader.load();

            stage = (Stage) loginButton.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
           //System.out.println("login succesful");


        }
        else
        {
            System.out.println("login unsuccesful");
        }

    }
}