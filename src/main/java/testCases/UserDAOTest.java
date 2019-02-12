package testCases;

import CheckBook.DataAccess.DAO.UserDAO;
import CheckBook.DataAccess.Models.SessionUser;
import com.mysql.cj.Session;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {

    /**
     * assumes the a row exists with key pair values below exists
     * # id, first_name, last_name, email, password, user_name, street_address, city, state, country, zip_code, user_type, date_registered
     * '1', 'TESTFNAME', 'TESTLNAME', 'TESTEMAIL', 'TESTPASSWORD', 'TESTUSERNAME', '123 street', 'TEST', 'test', 'test', NULL, '1', '1970-01-02 00:00:00'
     */
    @Test
    public void testcheckLoginReturnsTrue() {

        UserDAO uDAO = new UserDAO();

        SessionUser legitSU = new SessionUser();
        SessionUser methodCall = uDAO.checkLogin("TESTUSERNAME", "TESTPASSWORD");

        legitSU.setUserID(1);
        legitSU.setFirstName("TESTFNAME");
        legitSU.setLastName("TESTLNAME");


        boolean worked = false;

        if (legitSU.getUserID() == methodCall.getUserID() && legitSU.getFirstName().equals(methodCall.getFirstName()) && legitSU.getLastName().equals(methodCall.getLastName())) {
            worked = true;
        }


        assertTrue(worked);

    }

    @Test
    public void testCheckLoginReturnsFalse() {

        UserDAO uDAO = new UserDAO();

        SessionUser legitSU = new SessionUser();
        SessionUser methodCall = uDAO.checkLogin("notinDB", "NOTINDB");

        boolean worked = false;
        if (methodCall.getLastName() == null && methodCall.getFirstName() == null && methodCall.getUserID() == -1) {
            worked = true;
        }

        assertTrue(worked);
    }


    @Test
    public void testCheckLoginThrowsException() {
        UserDAO uDAO = new UserDAO();
        uDAO.getConn().closeConnection();

        SessionUser methodCall = uDAO.checkLogin("doesnt", "matter");


        boolean worked = false;
        if (methodCall.getLastName() == null && methodCall.getFirstName() == null && methodCall.getLastName() == null) {
            worked = true;
        }

        assertTrue(worked);

    }

    @Test
    public void testGetUserByIdReturnsValidUser() {

        UserDAO uDAO = new UserDAO();

        SessionUser methodCall = uDAO.getUserById(1);
        SessionUser myUser = new SessionUser();
        myUser.setLastName("TESTLNAME");
        myUser.setFirstName("TESTFNAME");
        myUser.setUserID(1);

        boolean worked = false;

        if (myUser.getUserID() == methodCall.getUserID() && myUser.getFirstName().equals(methodCall.getFirstName()) && myUser.getLastName().equals(methodCall.getLastName())) {
            worked = true;
        }


        assertTrue(worked);

    }

    @Test
    public void testGetUserByIdReturnsNullIfUserNotExist() {
        UserDAO uDAO = new UserDAO();

        SessionUser methodCall = uDAO.getUserById(2);


        boolean worked = false;
        if (methodCall.getLastName() == null && methodCall.getFirstName() == null && methodCall.getUserID() == -1) {
            worked = true;
        }
        assertTrue(worked);
    }


    @Test
    public void testGetUserByIdCatchesException() {

        UserDAO uDAO = new UserDAO();
        uDAO.getConn().closeConnection();
        SessionUser methodCall = uDAO.checkLogin((String) "can be anything", "con is closed so it throws");

        boolean worked = false;
        if (methodCall.getLastName() == null && methodCall.getFirstName() == null && methodCall.getUserID() == -1) {
            worked = true;
        }
        assertTrue(worked);

    }


}