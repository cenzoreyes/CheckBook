package CheckBook.DataAccess.DAO;

import CheckBook.DataAccess.Models.SessionUser;
import CheckBook.DataAccess.Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private CheckBookDB conn = new CheckBookDB();

    public SessionUser checkLogin(String userName, String pass) {

        String sql = "select * from checkbook_users where user_name=? and password=?;";
        SessionUser user = new SessionUser();


        try {
            PreparedStatement stmnt = conn.openConnection().prepareStatement(sql);

            stmnt.setString(1, userName);
            stmnt.setString(2, pass);

            ResultSet rs = stmnt.executeQuery();

            if (rs.first()) {
                user.setUserID(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                conn.closeConnection();
                stmnt.close();
                return user;
            } else {
                conn.closeConnection();
                stmnt.close();
                return null;
            }

        } catch (SQLException e) {
            conn.closeConnection();
            System.out.println("Exception raised during login process, the process has been terminated.");
            return null;
        }

    }

    public User getUser(int id) {
        String sql = "SELECT * from checkbook_users WHERE id=?";
        User user = new User();

        try {
            PreparedStatement get = conn.openConnection().prepareStatement(sql);
            get.setInt(1, id);

            ResultSet rs = get.executeQuery();

            if (rs.first()) {
                user.setId(rs.getInt("id"));
                user.setfName(rs.getString("first_name"));
                user.setlName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("street_address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setCountry(rs.getString("country"));
                user.setZipcode(rs.getString("zip_code"));
                user.setUserType(rs.getInt("user_type"));
                user.setRegistrationDate(rs.getString("date_registered"));
            }

            conn.closeConnection();
            get.close();
        } catch (SQLException ex) {
            conn.closeConnection();
            System.out.println("Error fetching user details from database for user " + id + ". The process has been terminated.");
        }

        return user;
    }

    public SessionUser getUserById(int id) {
        String sql = "select * from checkbook_users where id=?;";
        SessionUser user = new SessionUser();


        try {
            PreparedStatement stmnt = conn.openConnection().prepareStatement(sql);


            stmnt.setString(1, String.valueOf(id));


            ResultSet rs = stmnt.executeQuery();

            if (rs.first()) {
                user.setUserID(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                conn.closeConnection();
                stmnt.close();
                return user;
            } else {
                conn.closeConnection();
                stmnt.close();
                return user;
            }

        } catch (SQLException e) {
            conn.closeConnection();
            System.out.println("Exception raised during login process, the process has been terminated.");
            return user;
        }

    }

    public void createUser(User newUser) {
        String userSql = "INSERT INTO checkbook_dev.checkbook_users(`first_name`,`last_name`,`email`,`password`,`user_name`,`street_address`,`city`,state,`country`,`zip_code`,`user_type`,`date_registered`)VALUES(?,?,?,?,?,?,?,?,?,?,1,NOW());";
        String acctSql = "INSERT INTO `checkbook_dev`.`accounts`(`user_id`,`acct_balance`,`acct_type`)VALUES(?,?,?);";
        String sql = "select * from checkbook_users where user_name=? and password=?;";
        try {
            PreparedStatement createUser = conn.openConnection().prepareStatement(userSql);
            createUser.setString(1, newUser.getfName());
            createUser.setString(2, newUser.getlName());
            createUser.setString(3, newUser.getEmail());
            createUser.setString(4, newUser.getPassword());
            createUser.setString(5, newUser.getUsername());
            createUser.setString(6, newUser.getAddress());
            createUser.setString(7, newUser.getCity());
            createUser.setString(8, newUser.getState());
            createUser.setString(9, newUser.getCountry());
            createUser.setString(10, newUser.getZipcode());

            createUser.executeUpdate();
            createUser.close();

            SessionUser createdUser = new SessionUser();
            PreparedStatement getUserID = conn.openConnection().prepareStatement(sql);
            getUserID.setString(1, newUser.getUsername());
            getUserID.setString(2, newUser.getPassword());
            ResultSet rs = getUserID.executeQuery();
            if (rs.first()) {
                createdUser.setUserID(rs.getInt("id"));
                createdUser.setFirstName(rs.getString("first_name"));
                createdUser.setLastName(rs.getString("last_name"));
                getUserID.close();
            }


            PreparedStatement createAcct = conn.openConnection().prepareStatement(acctSql);
            createAcct.setInt(1, createdUser.getUserID());
            createAcct.setDouble(2, 1000);
            createAcct.setString(3, "CHECKING");

            createAcct.executeUpdate();
            conn.closeConnection();
            createAcct.close();
        } catch (SQLException ex) {
            conn.closeConnection();
            System.out.println("Unable to add new user " + newUser.getfName() + " " + newUser.getlName() + " to database. ");
        }
    }

    public CheckBookDB getConn() {
        return conn;
    }

    public void setConn(CheckBookDB conn) {
        this.conn = conn;
    }
}
