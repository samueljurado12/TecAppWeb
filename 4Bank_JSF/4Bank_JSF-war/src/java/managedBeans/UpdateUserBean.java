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
import persistence.User;

/**
 *
 * @author ubuntie
 */
@Named(value = "updateUserBean")
@RequestScoped
public class UpdateUserBean {

    @EJB
    UserFacade userFacade;
    
    User user;
    String username, name, surname, mail, nif, phone, address, password;
    
    /**
     * Creates a new instance of UpdateUser
     */
    public UpdateUserBean() {
    }
    
    public void updateUser(String id){
        user = userFacade.find(id);
        
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setAddress(address);
        user.setEmail(mail);
        user.setNif(nif);
        user.setPassword(password);
        user.setPhoneNumber(Integer.parseInt(phone));
        
        userFacade.edit(user);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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
}
