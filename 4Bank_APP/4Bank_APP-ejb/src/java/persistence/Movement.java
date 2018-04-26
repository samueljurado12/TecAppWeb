/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sjuradoq
 */
@Entity
@Table(name = "MOVEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movement.findAll", query = "SELECT m FROM Movement m")
    , @NamedQuery(name = "Movement.findByIdMOVEMENT", query = "SELECT m FROM Movement m WHERE m.idMOVEMENT = :idMOVEMENT")
    , @NamedQuery(name = "Movement.findByConcept", query = "SELECT m FROM Movement m WHERE m.concept = :concept")
    , @NamedQuery(name = "Movement.findByAmount", query = "SELECT m FROM Movement m WHERE m.amount = :amount")
    , @NamedQuery(name = "Movement.findByNewBalance", query = "SELECT m FROM Movement m WHERE m.newBalance = :newBalance")
    , @NamedQuery(name = "Movement.findByDate", query = "SELECT m FROM Movement m WHERE m.date = :date")})
public class Movement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMOVEMENT")
    private Integer idMOVEMENT;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "idACCOUNT", referencedColumnName = "idACCOUNT")
    @ManyToOne(optional = false)
    private Account idACCOUNT;
    @JoinColumn(name = "idACCOUNT_receptor", referencedColumnName = "idACCOUNT")
    @ManyToOne(optional = false)
    private Account idACCOUNTreceptor;

    public Movement() {
    }

    public Movement(Integer idMOVEMENT) {
        this.idMOVEMENT = idMOVEMENT;
    }

    public Movement(Integer idMOVEMENT, String concept, float amount, float newBalance, Date date) {
        this.idMOVEMENT = idMOVEMENT;
        this.concept = concept;
        this.amount = amount;
        this.newBalance = newBalance;
        this.date = date;
    }

    public Integer getIdMOVEMENT() {
        return idMOVEMENT;
    }

    public void setIdMOVEMENT(Integer idMOVEMENT) {
        this.idMOVEMENT = idMOVEMENT;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getIdACCOUNT() {
        return idACCOUNT;
    }

    public void setIdACCOUNT(Account idACCOUNT) {
        this.idACCOUNT = idACCOUNT;
    }

    public Account getIdACCOUNTreceptor() {
        return idACCOUNTreceptor;
    }

    public void setIdACCOUNTreceptor(Account idACCOUNTreceptor) {
        this.idACCOUNTreceptor = idACCOUNTreceptor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMOVEMENT != null ? idMOVEMENT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movement)) {
            return false;
        }
        Movement other = (Movement) object;
        if ((this.idMOVEMENT == null && other.idMOVEMENT != null) || (this.idMOVEMENT != null && !this.idMOVEMENT.equals(other.idMOVEMENT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Movement[ idMOVEMENT=" + idMOVEMENT + " ]";
    }
    
}
