import javafx.beans.property.SimpleStringProperty;
import javax.security.sasl.SaslServer;
public class adminUsers {

    private final SimpleStringProperty adminID;
    private final SimpleStringProperty adminUsername;
    private final SimpleStringProperty adminPassword;
    private final SimpleStringProperty adminAccCreated;

   /*  public adminUser(int id, String uname, String pword, String account_created) {
        this.adminID = new SimpleStringProperty (String.valueOf(id));
        this.adminUsername = new SimpleStringProperty (uname);
        this.adminPassword = new SimpleStringProperty (pword);
        this.adminAccCreated = new SimpleStringProperty (account_created);
    }
    */

    


    public adminUsers(int id, String adminUser, String adminPassword, String account_created) {
        this.adminID = new SimpleStringProperty (String.valueOf(id));
        this.adminUsername = new SimpleStringProperty (adminUser);
        this.adminPassword = new SimpleStringProperty (adminPassword);
        this.adminAccCreated = new SimpleStringProperty (account_created);
    }

    public String getAdminID() {
        return this.adminID.get();
    }

    public String getAdminUsername() {
        return this.adminUsername.get();
    }

    public String getAdminPassword() {
        return this.adminPassword.get();
    }

    public String getAdminAccCreated() {
        return this.adminAccCreated.get();
    }  

    public void setAdminUsername(String adminUsername) {
        this.adminUsername.set(adminUsername);
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword.set(adminPassword);
    }

    public void setAdminAccCreated(String adminAccCreated) {
        this.adminAccCreated.set(adminAccCreated);
    }


}
