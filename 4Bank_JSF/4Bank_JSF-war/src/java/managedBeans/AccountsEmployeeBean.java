/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.AccountFacade;
import ejb.MovementFacade;
import ejb.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import persistence.Account;
import persistence.Movement;
import persistence.User;

/**
 *
 * @author JavierVazquez
 */
@Named(value = "accountsEmployeeBean")
@RequestScoped
public class AccountsEmployeeBean {

    @EJB
    private UserFacade userFacade;

    @EJB
    private AccountFacade accountFacade;

    @EJB
    private MovementFacade movementFacade;
    
    @Inject
    private LoginBean loginBean;

    User activeUser;
    List<Account> accountList;
    Account selectedAccount;
    List<Movement> movementList;
    Map<Integer, String> receptors;
    
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

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public List<Movement> getMovementList() {
        return movementList;
    }

    public void setMovementList(List<Movement> movementList) {
        this.movementList = movementList;
    }

    public Map<Integer, String> getReceptors() {
        return receptors;
    }

    public void setReceptors(Map<Integer, String> receptors) {
        this.receptors = receptors;
    }
    
   

    /**
     * Creates a new instance of accountsEmployeeBean
     */
    public AccountsEmployeeBean() {
    }
    
    public void doUpdateTable(){
        
        this.movementList = movementFacade.queryAllMovementsFromAndToAccount(selectedAccount);
        receptors = new TreeMap<>();
        for (Movement mov : movementList) {
            User otherAccount = getUser(mov, selectedAccount);
            receptors.put(otherAccount.getIdUSER(), otherAccount.getName() + " " + otherAccount.getSurname());
        }
    }
    
    public String formatDate(Date date){
        return new SimpleDateFormat("EEE dd/MM/YYYY").format(date);
    }
    
    public List<Movement> movementsWithSession() {
        this.selectedAccount = this.loginBean.getAccountaux();
        doUpdateTable();
        return this.movementList;
    }

    
    public String doMovements(String idAccount){
        this.selectedAccount = accountFacade.find(idAccount);
        this.loginBean.setAccountaux(selectedAccount);
        return "AddMovement";
    }
    
    @PostConstruct
    private void init(){
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
