/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.AccountFacade;
import ejb.UserFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import persistence.Account;
import persistence.User;

/**
 *
 * @author ubuntie
 */
@Named(value = "editUserBean")
@RequestScoped
public class EditUserBean {
    
    @EJB
    UserFacade userFacade;
    
    @ManagedProperty("#{param.userID}")
    protected String userID;
    
    protected User user;
    protected List<Account> accountList;
    /**
     * Creates a new instance of EditUserBean
     */
    public EditUserBean() {}
    
    @PostConstruct
    public void init(){
        user = userFacade.find(userID);
        accountList = user.getAccountList();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
