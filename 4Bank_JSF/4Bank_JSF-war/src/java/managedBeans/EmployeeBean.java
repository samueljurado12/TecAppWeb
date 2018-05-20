/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.UserFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import persistence.User;

/**
 *
 * @author RhoLouh
 */
@Named(value = "employeeBean")
@Dependent
public class EmployeeBean {
    
    @EJB
    private UserFacade userFacade;
    
    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    /**
     * Creates a new instance of EmployeeBean
     */
    public EmployeeBean() {
    }
    
    @PostConstruct
    public void init(){
        this.users = userFacade.findAllNotEmployee();
    }
    
}
