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
@Named(value = "deleteAccountBean")
@RequestScoped
public class DeleteAccountBean {

    @EJB
    AccountFacade accountFacade;
    
    /**
     * Creates a new instance of DeleteAccountBean
     */
    public DeleteAccountBean() {
    }

    public void deleteAccount(String accountId) {
        Account account = accountFacade.find(accountId);
        accountFacade.remove(account);
    }
}
