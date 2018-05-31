/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.UserFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import persistence.User;

/**
 *
 * @author JavierVazquez
 */
@Named(value = "createUserBean")
@RequestScoped
public class CreateUserBean {
        
    @EJB
    private UserFacade userFacade;

    User myUser;
    String name, surname, mail, nif, address;
    Integer phone;

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

   

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Creates a new instance of createUserBean
     */
    public CreateUserBean() {
    }
    @PostConstruct 
    private void init(){
        
    }
    public String createUser() {
        myUser = new User();
        myUser.setUsername(nif);
        myUser.setEmail(mail);
        System.out.println();
        myUser.setName(name);
        System.out.println("MY TELEFONO: " + phone);
        myUser.setPhoneNumber(phone);
        myUser.setAddress(address);
        myUser.setSurname(surname);
        myUser.setNif(nif);
        
        String userpass = myUser.getName();
        
         //generacion de password
        userpass = userpass.substring(0,3);
        userpass =userpass.concat(myUser.getNif().substring(0,3));
        myUser.setPassword(userpass);
        
        
        userFacade.create(myUser);
        name = null;
        surname = null;
        mail = null;
        phone = null;
        nif = null;
        address = null;
        myUser = null;
        return "Employee";
    }

}
