package managedBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.AccountFacade;
import ejb.MovementFacade;
import ejb.UserFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import persistence.Account;
import persistence.Movement;
import persistence.User;

/**
 *
 * @author JavierVazquez
 */
@Named(value = "transferBean")
@Dependent
public class transferBean implements Serializable  {


    @EJB
    private UserFacade userFacade;

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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

      /**
     * Creates a new instance of transferBean
     */
    public transferBean() {
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Movement getMyMovement() {
        return myMovement;
    }

    public void setMyMovement(Movement myMovement) {
        this.myMovement = myMovement;
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

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
    

    @PostConstruct 
    private void init(){
        this.activeUser = SessionUtils.getActiveUser();
        this.accountList = accountFacade.queryAllAccountsOfUser(activeUser);
        this.selectedAccount = accountList.get(0);
    }
    public String doTransfer(){
        myMovement = new Movement();
//        System.out.println("MY CANTIDAD " + amount);
//        myMovement.setAmount(amount);
       System.out.println("MY CONCEPTO " + concept);
        myMovement.setConcept(concept);
       
        myMovement.setDate(new Date());
        System.out.println("MY CUENTA " + idSelectedAccount);
        selectedAccount = accountFacade.find(idSelectedAccount);
        myMovement.setIdACCOUNT(selectedAccount);
        receptorAccount = accountFacade.find(idReceptorAccount);
        myMovement.setIdACCOUNTreceptor(receptorAccount);
        float aux = selectedAccount.getBalance();
        System.out.println("ESTO ES MI BALANCEEEEE" + aux);
        if((aux- amount )>0){
            aux=aux-amount;
        }else{
            aux=0;
        }
        myMovement.setNewBalanceSender(aux);
        
        aux = receptorAccount.getBalance();
        aux=aux+amount;
        myMovement.setNewBalanceReceiver(aux);
        
        movementFacade.create(myMovement);
        
        return "Transfer";
    }
}
