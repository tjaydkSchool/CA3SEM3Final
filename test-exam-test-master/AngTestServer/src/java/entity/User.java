package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String userName;
    private String password;  //Pleeeeease dont store me in plain text
    @ElementCollection
    List<String> roles = new ArrayList();
    @ElementCollection
    private Collection<String> companyList = new ArrayList<String>();

    public User() {

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, List<String> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public void AddRole(String role) {
        roles.add(role);
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public Collection<String> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<String> companyList) {
        this.companyList = companyList;
    }
    
    public void addCompanyToList(String cvr) {
        companyList.add(cvr);
    }
    
    public void removeCompanyFromList(String cvr) {
        companyList.remove(cvr);
    }

}
