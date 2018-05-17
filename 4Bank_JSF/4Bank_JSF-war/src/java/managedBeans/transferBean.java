package managedBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.AccountFacade;
import ejb.MovementFacade;
import ejb.UserFacade;
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
public class transferBean {

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
    User employee;

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

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

  

   

    
        
    /**
     * Creates a new instance of transferBean
     */
    public transferBean() {
    }
    
    
    
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
    public String doTransfer(){
        myMovement = new Movement();
        myMovement.setAmount(amount);
        myMovement.setConcept(concept);
        myMovement.setDate(new Date());
        Account selectedAccount = accountFacade.find(idSelectedAccount);
        myMovement.setIdACCOUNT(selectedAccount);
        Account receptorAccount = accountFacade.find(idReceptorAccount);
        myMovement.setIdACCOUNTreceptor(receptorAccount);
        employee = userFacade.find(7);
        myMovement.setIdEmployee(employee);
        float aux = selectedAccount.getBalance();
        if(aux-amount>0){
            aux=aux-amount;
        }else{
            aux=0;
        }
        myMovement.setNewBalanceSender(aux);
        
        aux = receptorAccount.getBalance();
        aux=aux+amount;
        myMovement.setNewBalanceReceiver(aux);
        
        return "Transfer";
    }

    @PostConstruct 
    private void init(){
        this.accountList=accountFacade.findAll();
        
        
    }
}
