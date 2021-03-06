/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.AccountFacade;
import ejb.UserFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import persistence.Account;
import persistence.User;

/**
 *
 * @author JavierVazquez
 */
@Named(value = "editUserBean")
@RequestScoped
public class EditUserBean {

    @Inject 
    private LoginBean loginBean;
    
    @EJB
    private AccountFacade accountFacade;

    @EJB
    private UserFacade userFacade;

    private List<Account> accounts;
    
    int idUser;
    User myUser;
    String username;
    String name;
    String surname;
    String email;
    String nif;
    int phone;
    String address;
    String password;
    String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    
    

    /**
     * Creates a new instance of EditUserBean
     */
    public EditUserBean() {
    }


    public String EditValues() {
        
        myUser = this.loginBean.getUseraux();
    
        if (this.username != null && !username.equals(myUser.getUsername())) {
            myUser.setUsername(username);
        }
        if (name != null && !name.equals(myUser.getName())) {
            myUser.setName(name);
        }
        if (surname != null && !surname.equals(myUser.getSurname())) {
            myUser.setSurname(surname);
        }
        if (email != null && !email.equals(myUser.getEmail())) {
            myUser.setEmail(email);
        }
        if (nif != null && !nif.equals(myUser.getNif())) {
            myUser.setNif(nif);
        }
        if (address != null && !address.equals(myUser.getAddress())) {
            myUser.setAddress(address);
        }
        if (phone != 0 && phone != myUser.getPhoneNumber()) {
            myUser.setUsername(username);
        }
       /* if(password!=null && !password.equals(myUser.getPassword())){
            myUser.setPassword(password);
        } */
        if (!newPassword.equals("") && !newPassword.equals(myUser.getPassword())) {
            myUser.setPassword(newPassword);
            password=newPassword;
            newPassword="";
        } 
        userFacade.edit(myUser);
        myUser=null;
        return "EditUser";
    }
    
    public List<Account> accountsWithSession() {
        this.myUser=this.loginBean.getUseraux();
        this.accounts=accountFacade.queryAllAccountsOfUser(myUser);
        return this.accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @PostConstruct
    public void init(){
        this.myUser = this.loginBean.getUseraux();
        this.idUser = myUser.getIdUSER();
        this.myUser = userFacade.find(idUser);
        this.username = myUser.getUsername();
        this.name = myUser.getName();
        this.surname = myUser.getSurname();
        this.email = myUser.getEmail();
        this.nif = myUser.getNif();
        this.address = myUser.getNif();
        this.phone = myUser.getPhoneNumber();
        this.password=myUser.getPassword();
        this.accounts = accountFacade.queryAllAccountsOfUser(myUser);
        
    }
    
    
    
}
