/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.AccountFacade;
import ejb.MovementFacade;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
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
    
    @Inject
    private LoginBean login;
    
    String selectedAccountID;
    
    User activeUser;
    List<Account> accountList;
    Account selectedAccount;
    List<Movement> movementList;
    Map<Integer, String> receptors;

    public String getSelectedAccountID() {
        return selectedAccountID;
    }

    public void setSelectedAccountID(String selectedAccountID) {
        this.selectedAccountID = selectedAccountID;
    }

    public Map<Integer, String> getReceptors() {
        return receptors;
    }

    public void setReceptors(Map<Integer, String> receptors) {
        this.receptors = receptors;
    }

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
    
    public void doUpdateTable(){
        this.selectedAccount = accountFacade.find(this.selectedAccountID);
        this.movementList = movementFacade.queryAllMovementsFromAndToAccount(selectedAccount);
        receptors = new TreeMap<>();
        for (Movement mov : movementList) {
            User otherAccount = getUser(mov, selectedAccount);
            receptors.put(otherAccount.getIdUSER(), otherAccount.getName() + " " + otherAccount.getSurname());
        }
    }
    
    @PostConstruct
    private void init(){
        this.activeUser = login.getUser();
        this.accountList = accountFacade.queryAllAccountsOfUser(activeUser);
        this.selectedAccount = accountList.get(0);
        this.selectedAccountID = selectedAccount.getIdACCOUNT();
        doUpdateTable();
    }
    
    private User getUser(Movement mov, Account selectedAccount) {
        if (mov.getIdACCOUNT().equals(selectedAccount)) {
            return mov.getIdACCOUNTreceptor().getIdUSER();
        } else {
            return mov.getIdACCOUNT().getIdUSER();
        }
    }
}
