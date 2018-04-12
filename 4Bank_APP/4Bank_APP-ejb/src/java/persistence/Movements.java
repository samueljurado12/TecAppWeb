/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sjuradoq
 */
@Entity
@Table(name = "MOVEMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movements.findAll", query = "SELECT m FROM Movements m")
    , @NamedQuery(name = "Movements.findByIdMOVEMENTS", query = "SELECT m FROM Movements m WHERE m.idMOVEMENTS = :idMOVEMENTS")
    , @NamedQuery(name = "Movements.findByIdUSERSreceptor", query = "SELECT m FROM Movements m WHERE m.idUSERSreceptor = :idUSERSreceptor")
    , @NamedQuery(name = "Movements.findByConcept", query = "SELECT m FROM Movements m WHERE m.concept = :concept")
    , @NamedQuery(name = "Movements.findByAmount", query = "SELECT m FROM Movements m WHERE m.amount = :amount")
    , @NamedQuery(name = "Movements.findByNewBalance", query = "SELECT m FROM Movements m WHERE m.newBalance = :newBalance")})
public class Movements implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMOVEMENTS")
    private Integer idMOVEMENTS;
    @Column(name = "idUSERS_receptor")
    private Integer idUSERSreceptor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "concept")
    private String concept;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private float amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "new_balance")
    private float newBalance;
    @JoinColumns({
        @JoinColumn(name = "idUSERS", referencedColumnName = "idUSERS")
        , @JoinColumn(name = "idACCOUNT", referencedColumnName = "idACCOUNT")})
    @ManyToOne(optional = false)
    private Account account;
    @JoinColumn(name = "idUSERS", referencedColumnName = "idUSERS")
    @ManyToOne(optional = false)
    private Users idUSERS;

    public Movements() {
    }

    public Movements(Integer idMOVEMENTS) {
        this.idMOVEMENTS = idMOVEMENTS;
    }

    public Movements(Integer idMOVEMENTS, String concept, float amount, float newBalance) {
        this.idMOVEMENTS = idMOVEMENTS;
        this.concept = concept;
        this.amount = amount;
        this.newBalance = newBalance;
    }

    public Integer getIdMOVEMENTS() {
        return idMOVEMENTS;
    }

    public void setIdMOVEMENTS(Integer idMOVEMENTS) {
        this.idMOVEMENTS = idMOVEMENTS;
    }

    public Integer getIdUSERSreceptor() {
        return idUSERSreceptor;
    }

    public void setIdUSERSreceptor(Integer idUSERSreceptor) {
        this.idUSERSreceptor = idUSERSreceptor;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(float newBalance) {
        this.newBalance = newBalance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Users getIdUSERS() {
        return idUSERS;
    }

    public void setIdUSERS(Users idUSERS) {
        this.idUSERS = idUSERS;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMOVEMENTS != null ? idMOVEMENTS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movements)) {
            return false;
        }
        Movements other = (Movements) object;
        if ((this.idMOVEMENTS == null && other.idMOVEMENTS != null) || (this.idMOVEMENTS != null && !this.idMOVEMENTS.equals(other.idMOVEMENTS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Movements[ idMOVEMENTS=" + idMOVEMENTS + " ]";
    }
    
}
