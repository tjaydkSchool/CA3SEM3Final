package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CurrencyTrack {
    @Id
    private String date;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Currency> currencyList = new ArrayList();
    
    public CurrencyTrack() {
        
    }

    public CurrencyTrack(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public void addToCurrencyList(Currency c) {
        currencyList.add(c);
    }
    
    public void deleteFromCurrencyList(Currency c) {
        currencyList.remove(c);
    }
    
}
