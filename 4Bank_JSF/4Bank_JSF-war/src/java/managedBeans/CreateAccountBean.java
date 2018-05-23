/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.AccountFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import persistence.Account;

/**
 *
 * @author ubuntie
 */
@Named(value = "createAccountBean")
@RequestScoped
public class CreateAccountBean {

    @EJB
    AccountFacade accountFacade;
    
    int balance;
    /**
     * Creates a new instance of CreateAccountBean
     */
    public CreateAccountBean() {
    }

    public void createAccount(){
        Account account = new Account();
        account.setBalance(balance);
        accountFacade.create(account);
    }
    
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
