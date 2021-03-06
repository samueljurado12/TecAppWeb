/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RhoLouh
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByIdACCOUNT", query = "SELECT a FROM Account a WHERE a.idACCOUNT = :idACCOUNT")
    , @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "idACCOUNT")
    private String idACCOUNT;
    @Basic(optional = false)
    @NotNull
    @Column(name = "balance")
    private float balance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idACCOUNT")
    private List<Movement> movementList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idACCOUNTreceptor")
    private List<Movement> movementList1;
    @JoinColumn(name = "idUSER", referencedColumnName = "idUSER")
    @ManyToOne(optional = false)
    private User idUSER;

    public Account() {
    }

    public Account(String idACCOUNT) {
        this.idACCOUNT = idACCOUNT;
    }

    public Account(String idACCOUNT, float balance) {
        this.idACCOUNT = idACCOUNT;
        this.balance = balance;
    }

    public String getIdACCOUNT() {
        return idACCOUNT;
    }

    public void setIdACCOUNT(String idACCOUNT) {
        this.idACCOUNT = idACCOUNT;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @XmlTransient
    public List<Movement> getMovementList() {
        return movementList;
    }

    public void setMovementList(List<Movement> movementList) {
        this.movementList = movementList;
    }

    @XmlTransient
    public List<Movement> getMovementList1() {
        return movementList1;
    }

    public void setMovementList1(List<Movement> movementList1) {
        this.movementList1 = movementList1;
    }

    public User getIdUSER() {
        return idUSER;
    }

    public void setIdUSER(User idUSER) {
        this.idUSER = idUSER;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idACCOUNT != null ? idACCOUNT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.idACCOUNT == null && other.idACCOUNT != null) || (this.idACCOUNT != null && !this.idACCOUNT.equals(other.idACCOUNT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Account[ idACCOUNT=" + idACCOUNT + " ]";
    }
    
}
