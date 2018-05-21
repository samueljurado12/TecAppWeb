/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
public class addMovementBean {
    
    Movement myMovement;
    Account selectedAccount;
    User selectedUser;
    /**
     * Creates a new instance of addMovementBean
     */
    public addMovementBean() {
    }
    @PostConstruct 
    private void init(){
        
    }
    public String addMovement(){
        myMovement= new Movement();
        myMovement.setIdACCOUNT(selectedAccount);
        
        return "addMovement";
    }
    
}
