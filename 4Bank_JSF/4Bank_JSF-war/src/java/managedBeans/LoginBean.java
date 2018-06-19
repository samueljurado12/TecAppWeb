package managedBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.UserFacade;
import java.io.Serializable;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import persistence.Account;
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
    private User useraux;
    private Account accountaux;
    private final static Locale ENGLISH = new Locale("en");
    private final static Locale SPANISH = new Locale("es");
    private Locale currentLocale;

    public Account getAccountaux() {
        return accountaux;
    }

    public void setAccountaux(Account accountaux) {
        this.accountaux = accountaux;
    }

    public User getUseraux() {
        return useraux;
    }

    public void setUseraux(User useraux) {
        this.useraux = useraux;
    }

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
    
    public String doLogin() {
        User usuario = userFacade.queryUserByUsername(username);
        setUser(usuario);
        if (user != null && username != null && password.equals(user.getPassword())) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("user", user);
            session.setAttribute("selectedAccount", null);
            session.setAttribute("useraux", useraux);
            return user.getIsEmployee() ? "Employee" : "Accounts";
        } else {
            return "index";
        }
    }
    
    //Language
    public Locale getCurrentLocale() {
        return (currentLocale);
    }

    public String setEnglish() {
        currentLocale = ENGLISH;
        changeLocale();
        return null;
    }

    public String setSpanish() {
        currentLocale = SPANISH;
        changeLocale();
        return null;
    }

    private void changeLocale() {
        UIViewRoot view = FacesContext.getCurrentInstance()
                .getViewRoot();
        view.setLocale(currentLocale);
    }
    
    public String checkLog(){
        if (user != null){
            return user.getIsEmployee() ? "Employee" : "Accounts";
        } else
            return null;
    }
    
    public String redirectIfEmp(){
        if (user == null){
            return "index";
        }
        else if (user != null){
            return user.getIsEmployee() ? "Employee" : null;
        } else
            return null;
    }
    
    public String redirectIfUser(){
        if (user == null){
            return "index";
        }
        else if (user != null){
            return user.getIsEmployee() ? null : "Accounts";
        } else
            return null;
    }
    
    public String redirectIfNotLog(){
        if (user == null){
            return "index";
        } else
            return null;
    }
}
