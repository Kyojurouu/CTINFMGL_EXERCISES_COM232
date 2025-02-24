import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.naming.spi.DirStateFactory;
import javax.xml.transform.Templates;
import java.sql.ResultSet;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.INFORMATION;


public class adminHomeController {

    ObservableList<adminUsers> adminUsers = FXCollections.observableArrayList();

    @FXML
    Label adminWelcome;

    @FXML
    private TableColumn<adminUsers, String> adminIDCol;

    @FXML
    private TableColumn<adminUsers, String> adminAccCreatedCol;

    @FXML
    private TableColumn<adminUsers, String> adminPasswordCol;

    @FXML
    private TableView<adminUsers> adminTable;

    @FXML
    private TableColumn<adminUsers, String> adminUsernameCol;

    @FXML
    private Button adminCreateButton;

    @FXML
    private Button adminDeleteButton;
    
    @FXML
    private Button adminUpdateButton;  
    
    @FXML
    private Label addAdminPassword;

    @FXML
    private PasswordField addAdminPasswordField;

    @FXML
    private Label addAdminUser;

    @FXML
    private TextField addAdminUserField;


    public void displayName(String uname) {
        adminWelcome.setText("Welcome, " + uname + "!");
        initializeCol();
        displayadminUser();

    }

    public void initializeCol() {
        adminIDCol.setCellValueFactory(new PropertyValueFactory<>("adminID"));
        adminUsernameCol.setCellValueFactory(new PropertyValueFactory<>("adminUsername"));
        adminPasswordCol.setCellValueFactory(new PropertyValueFactory<>("adminPassword"));
        adminAccCreatedCol.setCellValueFactory(new PropertyValueFactory<>("adminAccCreated"));
        adminTable.setItems(getAdminUsers());
    }

    private void displayadminUser() {

        ResultSet result = DatabaseHandler.getadminUsers();
        adminUsers.clear();

        try {
            while (result.next()) {
                String id = result.getString("id");
                String adminUname = result.getString("adminUser");
                String adminPword = result.getString("adminPassword");
                String accCreated = result.getString("account_created");

                adminUsers newadminUser = new adminUsers(Integer.parseInt(id), adminUname, adminPword, accCreated);
                adminUsers.add(newadminUser);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        adminTable.setItems(adminUsers);
    }

        private ObservableList<adminUsers> getAdminUsers() {
        return adminUsers;
        }

        @FXML
        private void createadminUser(ActionEvent event) {

            String adminUser = addAdminUserField.getText();
            String adminPassword = addAdminPasswordField.getText();

            adminUser = adminUser.trim();
            adminPassword = adminPassword.trim();

            if (adminUser.length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Empty Username");
                alert.showAndWait();
            }

            if (adminPassword.length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Empty Password");
                alert.showAndWait();

            }

            adminUsers newadminUser = new adminUsers(0, adminUser, adminPassword, "");

            if (DatabaseHandler.addadminUser(newadminUser)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Account Created");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Account cannot be created");
                alert.showAndWait();
            }

            displayadminUser();

        }

        @FXML
        private void deleteadminUser(ActionEvent event) {

        adminUsers newadminUser = adminTable.getSelectionModel().getSelectedItem();

        if (newadminUser == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No account selected");
            alert.showAndWait();
            return;
        }

        String adminUser = newadminUser.getAdminUsername();

        if (DatabaseHandler.deleteadminUser(newadminUser)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Account Deleted");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Account cannot be deleted");
            alert.showAndWait();
        }

        displayadminUser();
    }


    @FXML
    private void updateadminUser(ActionEvent event) {
        
    adminUsers selectedAdminUser = adminTable.getSelectionModel().getSelectedItem();

    if (selectedAdminUser == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("No account selected");
        alert.showAndWait();
        return;
    }

    String adminUser = addAdminUserField.getText();
    String adminPassword = addAdminPasswordField.getText();

    adminUser = adminUser.trim();
    adminPassword = adminPassword.trim();

    if (adminUser.length() == 0) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Empty Username");
        alert.showAndWait();
        return;
    }

    if (adminPassword.length() == 0) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Empty Password");
        alert.showAndWait();
        return;
    }

    selectedAdminUser.setAdminUsername(adminUser);
    selectedAdminUser.setAdminPassword(adminPassword);

    if (DatabaseHandler.updateadminUser(selectedAdminUser)) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Account Updated");
        alert.showAndWait();
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Account cannot be updated");
        alert.showAndWait();
    }

    displayadminUser();
}

   /* @FXML
    private void updateadminUser(ActionEvent event) {

        adminUsers newadminUser = adminTable.getSelectionModel().getSelectedItem();

        if (newadminUser == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No account selected");
            alert.showAndWait();
            return;
        }

        String adminUser = newadminUser.getAdminUsername();
        String adminPassword = newadminUser.getAdminPassword();

        adminUser = adminUser.trim();
        adminPassword = adminPassword.trim();

        if (adminUser.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Empty Username");
            alert.showAndWait();
            return;
        }

        if (adminPassword.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Empty Password");
            alert.showAndWait();
            return;
        }
        

        //adminUsers updateadminUser = new adminUsers(0, adminUser, adminPassword, "");

        if (DatabaseHandler.updateadminUser(newadminUser)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Account Updated");
            alert.showAndWait();

        } else {
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Account cannot be updated");
            alert.showAndWait();
        }

        displayadminUser();
    }


    */
}

