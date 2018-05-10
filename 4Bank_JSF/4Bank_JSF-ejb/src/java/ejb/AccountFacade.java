/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.Account;
import persistence.User;

/**
 *
 * @author sjuradoq
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "4Bank_APP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    public Account queryAccountById(String id){
        Query q = this.em.createNamedQuery("Account.findByIdACCOUNT");
        q.setParameter("idACCOUNT", id);
        try{
            return (Account)q.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    public List<Account> queryAllAccountsOfUser(User selectedUser) {
        return this.em.createQuery("SELECT a FROM Account a WHERE a.idUSER = :idUSER")
                .setParameter("idUSER", selectedUser)
                .getResultList();
    }
    
}
