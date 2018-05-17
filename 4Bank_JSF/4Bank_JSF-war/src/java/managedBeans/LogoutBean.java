/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.UserFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import persistence.User;

/**
 *
 * @author RhoLouh
 */
@ManagedBean(name = "logoutBean")
@SessionScoped
public class LogoutBean {

    @EJB
    private UserFacade userFacade;

    /**
     * Creates a new instance of LogoutBean
     */
    public LogoutBean() {
    }

    public String doLogout() {
        User user = (User) SessionUtils.getSession().getAttribute("user");

        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";

    }

}
