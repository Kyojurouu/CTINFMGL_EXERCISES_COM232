import java.sql.*;

public class DatabaseHandler {

    private static DatabaseHandler handler = null;
    private static Statement stmt = null;
    private static PreparedStatement pstatement = null;

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    public static Connection getDBConnection()
    {
        Connection connection = null;
        String dburl = "jdbc:mysql://localhost:3306/TikTokDB?serverTimezone=Asia/Kuala_Lumpur";
        String userName = "root";
        String password = "password2025";

        try
        {
            connection = DriverManager.getConnection(dburl, userName, password);

        } catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = getDBConnection().createStatement();
            result = stmt.executeQuery(query);
        }
        catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally {
        }
        return result;
    }

    public static boolean validateLogin(String adminUser, String adminPassword){

        getInstance();
        String query = "SELECT * FROM adminUsers WHERE adminUser = '" + adminUser + "' AND adminPassword = '" + adminPassword + "'";
        
        System.out.println(query);

        ResultSet result = handler.execQuery(query);
        try {
            if (result.next()) {
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static ResultSet getadminUsers() {
        ResultSet result = null;

        try {
            String query = "SELECT * FROM adminUsers";
            result = handler.execQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
     }

     public static boolean addadminUser(adminUsers adminUser) {
        try {
            pstatement = getDBConnection().prepareStatement("INSERT INTO adminUsers(adminUser, adminPassword) VALUES(?, ?)");
            pstatement.setString(1, adminUser.getAdminUsername());
            pstatement.setString(2, adminUser.getAdminPassword());

            return pstatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

     }

     public static boolean deleteadminUser(adminUsers adminUser) {
        try {
            pstatement = getDBConnection().prepareStatement("DELETE FROM adminUsers WHERE adminUser = ?");
            pstatement.setString(1, adminUser.getAdminUsername());

            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

     }

     public static boolean updateadminUser(adminUsers adminUser) {
        try {
           // pstatement = getDBConnection().prepareStatement("UPDATE 'adminUsers' SET adminUser = ?, adminPassword = ? WHERE adminUser = ?");
            pstatement = getDBConnection().prepareStatement("UPDATE adminUsers SET adminUser = ?, adminPassword = ? WHERE id = ?");
            //pstatement.setInt(1, Integer.parseInt(adminUser.getAdminID()));
            pstatement.setString(1, adminUser.getAdminUsername());
            pstatement.setString(2, adminUser.getAdminPassword());
            pstatement.setInt(3, Integer.parseInt(adminUser.getAdminID()));
            //pstatement.setString(3, adminUser.getAdminUsername());
            

            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

     }




}

