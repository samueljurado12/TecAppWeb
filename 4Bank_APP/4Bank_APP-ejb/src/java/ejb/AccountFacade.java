/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

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

    public Account queryAccountById(int senderAccountNumber) {
        Query q = this.em.createNamedQuery("findByIdACCOUNT");
        q.setParameter("idACCOUNT", senderAccountNumber);
        try{
            return (Account)q.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
    
}
