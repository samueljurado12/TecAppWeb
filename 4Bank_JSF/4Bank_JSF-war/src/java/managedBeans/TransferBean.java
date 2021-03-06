package managedBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.AccountFacade;
import ejb.MovementFacade;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import persistence.Account;
import persistence.Movement;
import persistence.User;

/**
 *
 * @author JavierVazquez
 */
@Named(value = "transferBean")
@RequestScoped
public class TransferBean  {



    @EJB
    private MovementFacade movementFacade;

    @EJB
    private AccountFacade accountFacade;

    List <Account> accountList;
    String idSelectedAccount;
    String idReceptorAccount;
    Movement myMovement;
    Float amount;
    String concept;
    User  activeUser;
    Account selectedAccount, receptorAccount;

  
      /**
     * Creates a new instance of transferBean
     */
    public TransferBean() {
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public String getIdSelectedAccount() {
        return idSelectedAccount;
    }

    public void setIdSelectedAccount(String idSelectedAccount) {
        this.idSelectedAccount = idSelectedAccount;
    }

    public String getIdReceptorAccount() {
        return idReceptorAccount;
    }

    public void setIdReceptorAccount(String idReceptorAccount) {
        this.idReceptorAccount = idReceptorAccount;
    }

    public Movement getMyMovement() {
        return myMovement;
    }

    public void setMyMovement(Movement myMovement) {
        this.myMovement = myMovement;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public Account getReceptorAccount() {
        return receptorAccount;
    }

    public void setReceptorAccount(Account receptorAccount) {
        this.receptorAccount = receptorAccount;
    }

    @PostConstruct 
    private void init(){
        HttpSession session = SessionUtils.getSession();
        this.activeUser = (User)session.getAttribute("user");
        this.accountList = activeUser.getAccountList();
        this.selectedAccount = accountList.get(0);
    }
    public String doTransfer(){
        myMovement = new Movement();

        myMovement.setAmount(amount);
     
        myMovement.setConcept(concept);
       
        myMovement.setDate(new Date());
        selectedAccount = accountFacade.find(idSelectedAccount);
        myMovement.setIdACCOUNT(selectedAccount);
        if(accountFacade.find(idReceptorAccount)==null){
        
        return "Transfer";
        }
        receptorAccount = accountFacade.find(idReceptorAccount);
        myMovement.setIdACCOUNTreceptor(receptorAccount);
        float aux = selectedAccount.getBalance();
       
        if((aux- amount )>0){
            aux=aux-amount;
            
        }else{
            aux=0;
        }
        selectedAccount.setBalance(aux);
        accountFacade.edit(selectedAccount);
        myMovement.setNewBalanceSender(aux);
        
        aux = receptorAccount.getBalance();
        aux=aux+amount;
        myMovement.setNewBalanceReceiver(aux);
        receptorAccount.setBalance(aux);
        accountFacade.edit(receptorAccount);
        movementFacade.create(myMovement);
        this.amount=null; 
        this.idReceptorAccount=null;
        this.concept=null;
        return "Transfer";
    }
}
