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
import persistence.Users;

/**
 *
 * @author sjuradoq
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "4Bank_APP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users queryUserByUsername(String username){
        Query q = this.em.createNamedQuery("Users.findByUsername");
        q.setParameter("username", username);
        try{
            return (Users) q.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
    
}
