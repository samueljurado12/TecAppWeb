/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
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
    
    private Movement myMovement;
    private Account selectedAccount;
    private User selectedUser;
    private int selectedItem;
    /**
     * Creates a new instance of addMovementBean
     */
    public AddMovementBean() {
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
    private void init(){
        this.selectedItem = 0;
    }
    
    public String addMovement(){
        myMovement= new Movement();
        myMovement.setIdACCOUNT(selectedAccount);
        
        return "AddMovement";
    }
    
}
