/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.AccountFacade;
import ejb.MovementFacade;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import persistence.Account;
import persistence.Movement;
import persistence.User;

/**
 *
 * @author JavierVazquez
 */
@Named(value = "addMovementBean")
@RequestScoped
public class AddMovementBean {

    @EJB
    private MovementFacade movementFacade;

    @EJB
    private AccountFacade accountFacade;
    
    @Inject
    private LoginBean login;

    private String receptorAccountNumber;
    private String concept;
    private float amount;

    private Movement myMovement;
    private Account selectedAccount;
    private User selectedUser;
    private int selectedItem;

    /**
     * Creates a new instance of addMovementBean
     */
    public AddMovementBean() {
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getReceptorAccountNumber() {
        return receptorAccountNumber;
    }

    public void setReceptorAccountNumber(String receptorAccountNumber) {
        this.receptorAccountNumber = receptorAccountNumber;
    }

    public Movement getMyMovement() {
        return myMovement;
    }

    public void setMyMovement(Movement myMovement) {
        this.myMovement = myMovement;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    @PostConstruct
    private void init() {
        this.selectedItem = 0;
        this.selectedUser = login.getUser();
    }

    public String doAddMovement(User employee) {
        myMovement = new Movement();
        Account receptorAccount;
        Account senderAccount;

        switch (selectedItem) {
            case 0: // Transfer
                senderAccount = selectedAccount;
                receptorAccount = accountFacade.find(receptorAccountNumber);
                genMovement(senderAccount, receptorAccount, amount, concept, true);
                break;
            case 1: // Deposit
                senderAccount = accountFacade.find(0);
                receptorAccount = selectedAccount;
                genMovement(senderAccount, receptorAccount, amount, "Deposit", false);
                break;
            case 2: // Extraction
                senderAccount = selectedAccount;
                receptorAccount = accountFacade.find(0);
                genMovement(senderAccount, receptorAccount, amount, "Extraction", false);
                break;
        }

        return "AddMovement";
    }

    private void genMovement(Account sender, Account receiver, float amount, String concept, boolean isTransfer) {
        myMovement = new Movement(1);
        float newBalanceSender = sender.getBalance() - amount;
        float newBalanceReceiver = receiver.getBalance() - amount;
        if (isTransfer) {
            sender.setBalance(newBalanceSender);
            accountFacade.edit(sender);
            receiver.setBalance(newBalanceReceiver);
            accountFacade.edit(receiver);

        } else {
            if (sender.equals(accountFacade.find(0))) {
                receiver.setBalance(newBalanceReceiver);
                accountFacade.edit(receiver);
            } else {
                sender.setBalance(newBalanceReceiver);
                accountFacade.edit(sender);
            }
        }

        myMovement.setIdACCOUNT(sender);
        myMovement.setIdACCOUNTreceptor(receiver);
        myMovement.setAmount(amount);
        myMovement.setConcept(concept);
        myMovement.setNewBalanceSender(newBalanceSender);
        myMovement.setNewBalanceReceiver(newBalanceReceiver);
        myMovement.setDate(new Date());
        myMovement.setIdEmployee(selectedUser);

        movementFacade.create(myMovement);
    }

}
