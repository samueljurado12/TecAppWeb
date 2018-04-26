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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sjuradoq
 */
@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findByIdUSER", query = "SELECT e FROM Employee e WHERE e.idUSER = :idUSER")
    , @NamedQuery(name = "Employee.findByIsEmployee", query = "SELECT e FROM Employee e WHERE e.isEmployee = :isEmployee")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUSER")
    private Integer idUSER;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isEmployee")
    private boolean isEmployee;
    @JoinColumn(name = "idUSER", referencedColumnName = "idUSER", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Employee() {
    }

    public Employee(Integer idUSER) {
        this.idUSER = idUSER;
    }

    public Employee(Integer idUSER, boolean isEmployee) {
        this.idUSER = idUSER;
        this.isEmployee = isEmployee;
    }

    public Integer getIdUSER() {
        return idUSER;
    }

    public void setIdUSER(Integer idUSER) {
        this.idUSER = idUSER;
    }

    public boolean getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUSER != null ? idUSER.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.idUSER == null && other.idUSER != null) || (this.idUSER != null && !this.idUSER.equals(other.idUSER))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Employee[ idUSER=" + idUSER + " ]";
    }
    
}
