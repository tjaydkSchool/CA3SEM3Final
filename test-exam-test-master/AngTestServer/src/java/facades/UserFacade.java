package facades;

import entity.Currency;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import security.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import xml.XmlReader;

public class UserFacade {

    private final Map<String, User> users = new HashMap<>();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    private EntityManager em = emf.createEntityManager();
    private PasswordHash ph = new PasswordHash();

    public static void main(String[] args) {
        UserFacade f = new UserFacade();
    }

    public UserFacade() {
        if (em.find(User.class, "user") == null) {
            try {
                User user = new User();
                user.setUserName("user");
                user.setPassword(PasswordHash.createHash("test"));
                
                User admin = new User();
                admin.setUserName("admin");
                admin.setPassword(PasswordHash.createHash("test"));

                em.getTransaction().begin();
                em.persist(user);
                em.persist(admin);
                em.getTransaction().commit();

                user.AddRole("User");
                admin.AddRole("Admin");

                em.getTransaction().begin();
                em.persist(user);
                em.persist(admin);
                em.getTransaction().commit();
                
//                XmlReader xml = new XmlReader();
                
                
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void createDB() {
        if (em.find(User.class, "user") == null) {
            try {
                User user = new User();
                user.setUserName("user");
                user.setPassword(PasswordHash.createHash("test"));
                
                User admin = new User();
                admin.setUserName("admin");
                admin.setPassword(PasswordHash.createHash("test"));

                em.getTransaction().begin();
                em.persist(user);
                em.persist(admin);
                em.getTransaction().commit();

                user.AddRole("User");
                admin.AddRole("Admin");

                em.getTransaction().begin();
                em.persist(user);
                em.persist(admin);
                em.getTransaction().commit();
                
                XmlReader xml = new XmlReader();
                
                
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Create a user and persist in database, the password is Hashed for
     * security before being stored.
     *
     * @param username
     * @param password
     */
    public void createUser(User userData) {
        try {
            User user = new User(userData.getUserName(), ph.createHash(userData.getPassword()));
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

            user.AddRole("User");
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Return a user from database by user id
     *
     * @param id
     * @return User
     */
    public User getUser(String username) {
        return em.find(User.class, username);
    }

    /**
     * Update user in database
     *
     * @param user
     */
    public void updateUser(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    /**
     * Delete user from database
     *
     * @param user
     */
    public void deleteUser(String username) {
        User user = em.find(User.class, username);
        System.out.println(user.getPassword());
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    /**
     * Add company to a users list of companies followed
     *
     * @param user
     * @param cvr
     */
    public void addCompanyToUserList(User user, String cvr) {
        user.addCompanyToList(cvr);
    }

    /**
     * Remove company from users list of companies followed
     *
     * @param user
     * @param cvr
     */
    public void removeCompanyFromUserList(User user, String cvr) {
        user.removeCompanyFromList(cvr);
    }

    /**
     * Test if user is validated
     *
     * @param username
     * @param password
     */
    public boolean validateUser(String username, String password) {
        try {
            User user = em.find(User.class, username);
            if (PasswordHash.validatePassword(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /*
     Return the Roles if users could be authenticated, otherwise null
     */
    public List<String> authenticateUser(String userName, String password) {
        User user = em.find(User.class, userName);

        try {
            return user != null && (ph.validatePassword(password, user.getPassword())) ? user.getRoles() : null;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*
     Return currencies
     */
    public List<Currency> getCurrencyList() {
        TypedQuery q = em.createQuery("SELECT c FROM Currency c", Currency.class);
        return q.getResultList();
    }

}
