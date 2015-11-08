package facades;

import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AdminFacade {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    private EntityManager em = emf.createEntityManager();
    
    public AdminFacade() {
    }
    
    public static void main(String[] args) {
        AdminFacade f = new AdminFacade();
        System.out.println(f.getUserList().size());
    }

    public List<User> getUserList() {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u", User.class);
        return q.getResultList();
    }
;

    public void deleteUser(String user) {
        User u = em.find(User.class, user);
        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();
    }


}
