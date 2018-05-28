/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.UserFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import persistence.User;

/**
 *
 * @author RhoLouh
 */
@Named(value = "logoutBean")
@SessionScoped
public class LogoutBean implements Serializable {

    @EJB
    private UserFacade userFacade;
    
    @Inject
    private LoginBean login;
    /**
     * Creates a new instance of LogoutBean
     */
    public LogoutBean() {
    }

    public String doLogout() {
        login = null;
            
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";

    }

}
