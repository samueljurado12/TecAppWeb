/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.AccountFacade;
import ejb.MovementFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import persistence.Account;
import persistence.Movement;
import persistence.User;

/**
 *
 * @author sjuradoq
 */
@Named(value = "accountsBean")
@Dependent
public class AccountsBean {

    @EJB
    private MovementFacade movementFacade;

    @EJB
    private AccountFacade accountFacade;
    
    User activeUser;
    List<Account> accountList;
    Account selectedAccount;
    List<Movement> movementList;

    public List<Movement> getMovementList() {
        return movementList;
    }

    public void setMovementList(List<Movement> movementList) {
        this.movementList = movementList;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
    
    /**
     * Creates a new instance of AccountsBean
     */
    public AccountsBean() {
    }
    
    @PostConstruct
    private void init(){
        this.accountList = accountFacade.findAll();
        this.selectedAccount = accountList.get(0);
        this.movementList = movementFacade.queryAllMovementsFromAndToAccount(selectedAccount);
    }
}
