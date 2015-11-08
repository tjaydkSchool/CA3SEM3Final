package xml;

import entity.Currency;
import entity.CurrencyTrack;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class XmlReader extends DefaultHandler implements Runnable {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_OPENSHIFT");
    private EntityManager em = emf.createEntityManager();
    

    CurrencyTrack ct = new CurrencyTrack();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document (Sax-event)");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(ct.getCurrencyList().size());
        em.getTransaction().begin();
        em.persist(ct);
        em.getTransaction().commit();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        ct.setDate(new Date().toString());

        CurrencyTrack ct = new CurrencyTrack(new Date().toString());

        String name = attributes.getValue(0);
        String desc = attributes.getValue(1);
        String rate = attributes.getValue(2);

        if (name != null && desc != null && rate != null) {
            if (!rate.equals("-") && !rate.equals("DKK")) {
//                System.out.println("Here");
                addCurrencyToList(new Currency(name, desc, Double.parseDouble(rate)));
//                System.out.println(name + desc + rate);
            }
        }

//        System.out.println("Name: " + attributes.getValue(0) + " Description: " + attributes.getValue(1) + " Rate: " + attributes.getValue(2));

//        System.out.print("Element: " + localName + ": ");
//        for (int i = 0; i < attributes.getLength(); i++) {
//            System.out.print("[Atribute: NAME: " + attributes.getLocalName(i) + " VALUE: " + attributes.getValue(i) + "] ");
//            System.out.println(attributes.getValue(i));
//            xmlElements.put(attributes.getLocalName(i), attributes.getValue(i));
//        }
//        System.out.println(ct.getCurrencyList().size());
    }

    public void addCurrencyToList(Currency c) {
        ct.addToCurrencyList(c);
    }
    
    
    public static void main(String[] argv) {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}
