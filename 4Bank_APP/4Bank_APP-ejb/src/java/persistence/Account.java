/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sjuradoq
 */
@Entity
@Table(name = "ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByIdACCOUNT", query = "SELECT a FROM Account a WHERE a.accountPK.idACCOUNT = :idACCOUNT")
    , @NamedQuery(name = "Account.findByIdUSERS", query = "SELECT a FROM Account a WHERE a.accountPK.idUSERS = :idUSERS")
    , @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccountPK accountPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "balance")
    private Float balance;
    @JoinColumn(name = "idUSERS", referencedColumnName = "idUSERS", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Movements> movementsList;

    public Account() {
    }

    public Account(AccountPK accountPK) {
        this.accountPK = accountPK;
    }

    public Account(int idACCOUNT, int idUSERS) {
        this.accountPK = new AccountPK(idACCOUNT, idUSERS);
    }

    public AccountPK getAccountPK() {
        return accountPK;
    }

    public void setAccountPK(AccountPK accountPK) {
        this.accountPK = accountPK;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @XmlTransient
    public List<Movements> getMovementsList() {
        return movementsList;
    }

    public void setMovementsList(List<Movements> movementsList) {
        this.movementsList = movementsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountPK != null ? accountPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountPK == null && other.accountPK != null) || (this.accountPK != null && !this.accountPK.equals(other.accountPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Account[ accountPK=" + accountPK + " ]";
    }
    
}
