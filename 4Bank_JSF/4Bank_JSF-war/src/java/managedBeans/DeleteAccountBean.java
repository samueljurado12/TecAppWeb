/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.AccountFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import persistence.Account;

/**
 *
 * @author JavierVazquez
 */
@Named(value = "deleteAccountBean")
@Dependent
public class DeleteAccountBean {

    @EJB
    private AccountFacade accountFacade;

    Account account; 

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    
    /**
     * Creates a new instance of DeleteAccountBean
     */
    public DeleteAccountBean() {
    }
    
    public void deleteAccount (Account a){
        Account account = a;
        accountFacade.remove(account);
        account=null;
    }
}
