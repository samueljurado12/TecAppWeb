package managedBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.UserFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import persistence.User;

/**
 *
 * @author RhoLouh
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    @EJB
    private UserFacade userFacade;
    
    private User user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    public String doLogin(){
        User usuario = userFacade.queryUserByUsername(username);
        setUser(usuario);
        if(user != null && username != null && password.equals(user.getPassword())){
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("user", user);
            session.setAttribute("selectedAccount", null);
            return user.getIsEmployee() ? "Employee" : "Accounts";
        }
        else {
            return "index";
        }
    }
    
}
