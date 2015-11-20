package JUnit;

import entity.Currency;
import facades.CurrencyFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CurrencyJUnitTest {
    
    CurrencyFacade cf;
    
    public CurrencyJUnitTest() {
        cf = new CurrencyFacade();
    }
    
    /*
     ===========================================================================
     ================= START OF TEST OF ENTITY CLASS ===========================
     ===========================================================================
     */
    @Test
    public void testCreateCurrency() {
        Currency cur = new Currency();
        cur.setName("DKK");
        assertEquals(cur.getName(), "DKK");
    }
    
    @Test
    public void testUpdateCurrency() {
        Currency cur = new Currency();
        cur.setName("DKK");
        cur.setName("SEK");
        assertEquals(cur.getName(), "SEK");
    }
    
    /*
     ================= END OF TEST OF ENTITY CLASS =============================
     */
}
