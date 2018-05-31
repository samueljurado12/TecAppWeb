/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.UserFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import persistence.User;

/**
 *
 * @author JavierVazquez
 */
@Named(value = "deleteUserBean")
@RequestScoped
public class DeleteUserBean {

    @EJB
    private UserFacade userFacade;
    
    @Inject
    private EmployeeBean employeeBean;
    
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Creates a new instance of DeleteUserBean
     */
    public DeleteUserBean() {
    }
    
     public void deleteUser (User u){
        user = u;
        userFacade.remove(user);
        this.employeeBean.users.remove(user);
        user=null;
    }
    
}
