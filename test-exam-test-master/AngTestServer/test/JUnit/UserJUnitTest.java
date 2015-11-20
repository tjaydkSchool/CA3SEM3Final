package JUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Company;
import entity.User;
import facades.UserFacade;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import rest.ApplicationConfig;

/**
 *
 * @author Dennis
 */
public class UserJUnitTest {

    UserFacade facade;
    
    public UserJUnitTest() {
    }
    

    @Before
    public void setUp() {
        facade = new UserFacade();
    }

    @After
    public void tearDown() {
    }

    /*
     ===========================================================================
     ================= START OF TEST OF ENTITY CLASS ===========================
     ===========================================================================
     */
    @Test
    public void testCreateUserEntity() {
        String username = "user";
        String password = "pass";

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        assertEquals(user.getUserName(), username);
        assertEquals(user.getPassword(), password);

    }

    @Test
    public void testAddCompanyToUserEntityCompanyList() {
        User user = new User();
        Company comp = new Company();
        user.addCompanyToList("9999");

        assertTrue(user.getCompanyList().size() > 0);
    }

    @Test
    public void testRemoveCompanyFromUserEntityCompanyList() {
        User user = new User();
        user.addCompanyToList("9999");

        assertTrue(user.getCompanyList().size() > 0);

        user.removeCompanyFromList("9999");
        assertTrue(user.getCompanyList().size() == 0);
    }

    /*
     ================= END OF TEST OF ENTITY CLASS =============================
     */
    
    
    /*
     ===========================================================================
     ================= START OF TEST OF PERSISTENCE ============================
     ===========================================================================
     */
    @Test
    public void testCreateUserInDatabase() {
        User user = new User("tester", "tester");
        facade.createUser(user);
        User userFromDB = facade.getUser("tester");
        assertEquals(user.getUserName(), userFromDB.getUserName());
        
        facade.deleteUser(user.getUserName());
    }
    
    @Test
    public void testPasswordHashed() {
        User user = new User("tester", "tester");
        facade.createUser(user);
        User hashedPasswordUser = facade.getUser(user.getUserName());
        
        assertNotSame(user.getPassword(), hashedPasswordUser.getPassword());
        
        facade.deleteUser(user.getUserName());
    }
    
    @Test
    public void testPasswordValidation() {
        User user = new User("tester", "tester");
        facade.createUser(user);
        assertTrue(facade.validateUser(user.getUserName(), user.getPassword()));
        
        facade.deleteUser(user.getUserName());
    }

    /*
     ================= END OF TEST OF PERSISTENCE ==============================
     */
}
