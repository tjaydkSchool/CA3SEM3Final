package facades;

import entity.Currency;
import entity.CurrencyTrack;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import org.xml.sax.XMLReader;

public class CurrencyFacade {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    private EntityManager em = emf.createEntityManager();
//    XMLReader xmlReader = new SAXParser();
//    DOMParser dp = new DOMParser();

    public static void main(String[] args) {
        CurrencyFacade f = new CurrencyFacade();
        f.populateDB();
    }

    public void populateDB() {
        try {
            CurrencyTrack ct = new CurrencyTrack((new Date()).toString());

//            em.getTransaction().begin();
//            em.persist(ct);
//            em.getTransaction().commit();

            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            
            System.out.println(ct.getCurrencyList().size());

//            em.getTransaction().begin();
//            em.persist(ct);
//            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(CurrencyFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Double calculateCurrency(double amount, String fromCur, String toCur) {
//        Currency from = em.find(Currency.class, fromCur);
//        Currency to = em.find(Currency.class, toCur);

//        double fromCurrency = from.getRate();
//        double toCurrency = to.getRate();
//        return amount * (fromCurrency / toCurrency);
        return amount;
    }

}
