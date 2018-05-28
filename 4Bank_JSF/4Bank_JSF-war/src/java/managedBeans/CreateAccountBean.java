/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.AccountFacade;
import ejb.UserFacade;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import persistence.Account;
import persistence.User;

/**
 *
 * @author JavierVazquez
 */
@Named(value = "createAccountBean")
@Dependent
public class CreateAccountBean {
   
    
    @EJB
    private AccountFacade accountFacade;

    @EJB
    private UserFacade userFacade;
   
    
    private int idUser;
    
    
    int idAccount;
    Account myAccount; 
    User myUser;
    int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public void setMyAccount(Account myAccount) {
        this.myAccount = myAccount;
    }

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }
    
    
    /**
     * Creates a new instance of CreateAccountBean
     */
    public CreateAccountBean() {
    }
    
    public String newAccount(){
        myUser= userFacade.find(idUser);
        if(amount > 0){
            Account newAccount = new Account();
            newAccount.setIdUSER(myUser);
            newAccount.setBalance(amount);
            
            double rand = Math.floor(Math.random()*(9000000000.0)+1000000000.0);

            NumberFormat nf = DecimalFormat.getInstance();
            nf.setMaximumFractionDigits(0);
            String str = nf.format(rand).replaceAll("[-+.^:,]", "");
            newAccount.setIdACCOUNT("ES0130031337" + Integer.toString((int)(rand%100)) + str);

            accountFacade.create(newAccount);
        }   
        return "Employee";
    }
}
