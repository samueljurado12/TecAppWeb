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
    , @NamedQuery(name = "Employee.findByIdUSERS", query = "SELECT e FROM Employee e WHERE e.idUSERS = :idUSERS")
    , @NamedQuery(name = "Employee.findByIsEmployee", query = "SELECT e FROM Employee e WHERE e.isEmployee = :isEmployee")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUSERS")
    private Integer idUSERS;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isEmployee")
    private boolean isEmployee;
    @JoinColumn(name = "idUSERS", referencedColumnName = "idUSERS", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;

    public Employee() {
    }

    public Employee(Integer idUSERS) {
        this.idUSERS = idUSERS;
    }

    public Employee(Integer idUSERS, boolean isEmployee) {
        this.idUSERS = idUSERS;
        this.isEmployee = isEmployee;
    }

    public Integer getIdUSERS() {
        return idUSERS;
    }

    public void setIdUSERS(Integer idUSERS) {
        this.idUSERS = idUSERS;
    }

    public boolean getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUSERS != null ? idUSERS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.idUSERS == null && other.idUSERS != null) || (this.idUSERS != null && !this.idUSERS.equals(other.idUSERS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Employee[ idUSERS=" + idUSERS + " ]";
    }
    
}
