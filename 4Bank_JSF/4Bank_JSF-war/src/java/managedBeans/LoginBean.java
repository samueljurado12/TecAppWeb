package managedBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.UserFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;
import persistence.User;

/**
 *
 * @author RhoLouh
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    @EJB
    private UserFacade userFacade;
    
    private String username;
    private String password;
    private String msg; //TODO necesario para fallo en login 

    public LoginBean() {
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    public String doLogin(){
        User user = userFacade.queryUserByUsername(username);
        
        if(user != null && username != null && password.equals(user.getPassword())){
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("user", user);
            session.setAttribute("selectedAccount", null);
            if (user.getIsEmployee())
                return "Accounts";
            else
                return "Accounts";
        }
        else {
            return "index";
        }
    }
    
}
