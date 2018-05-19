/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.AccountFacade;
import ejb.MovementFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
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
    
    User activeUser;
    List<Account> accountList;
    Account selectedAccount;
    List<Movement> movementList;
    Map<Integer, String> receptors;

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
        this.movementList = movementFacade.queryAllMovementsFromAndToAccount(selectedAccount);
        receptors = new TreeMap<Integer, String>();
        for (Movement mov : movementList) {
            User otherAccount = getUser(mov, selectedAccount);
            receptors.put(otherAccount.getIdUSER(), otherAccount.getName() + " " + otherAccount.getSurname());
        }
    }
    
    public String formatDate(Date date){
        return new SimpleDateFormat("EEE dd/MM/YYYY").format(date);
    }
    
    @PostConstruct
    private void init(){
        HttpSession session = SessionUtils.getSession();
        this.activeUser = (User)session.getAttribute("user");
        this.accountList = activeUser.getAccountList();
        this.selectedAccount = accountList.get(0);
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
