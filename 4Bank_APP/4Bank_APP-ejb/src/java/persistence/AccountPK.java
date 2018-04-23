/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sjuradoq
 */
@Embeddable
public class AccountPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idACCOUNT")
    private int idACCOUNT;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUSERS")
    private int idUSERS;

    public AccountPK() {
    }

    public AccountPK(int idACCOUNT, int idUSERS) {
        this.idACCOUNT = idACCOUNT;
        this.idUSERS = idUSERS;
    }

    public int getIdACCOUNT() {
        return idACCOUNT;
    }

    public void setIdACCOUNT(int idACCOUNT) {
        this.idACCOUNT = idACCOUNT;
    }

    public int getIdUSERS() {
        return idUSERS;
    }

    public void setIdUSERS(int idUSERS) {
        this.idUSERS = idUSERS;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idACCOUNT;
        hash += (int) idUSERS;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountPK)) {
            return false;
        }
        AccountPK other = (AccountPK) object;
        if (this.idACCOUNT != other.idACCOUNT) {
            return false;
        }
        if (this.idUSERS != other.idUSERS) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.AccountPK[ idACCOUNT=" + idACCOUNT + ", idUSERS=" + idUSERS + " ]";
    }
    
}
