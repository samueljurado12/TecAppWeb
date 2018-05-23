/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.UserFacade;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import persistence.User;

/**
 *
 * @author ubuntie
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {
    @EJB
    UserFacade userFacade;

    @ManagedProperty("#{param.userID}")
    protected String userID;
    
    protected User currentUser;
    
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    
    @PostConstruct
    public void init(){
        userFacade.find(userID);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    
}
