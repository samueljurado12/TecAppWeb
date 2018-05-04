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
import persistence.User;

/**
 *
 * @author sjuradoq
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "4Bank_APP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User queryUserByUsername(String username) {
        Query q = this.em.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);
        try{
            return (User)q.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
    
    public void deleteUserByID(int id){
        User toDelete = this.find(id);
        remove(toDelete);
    }

    public List<User> findAllNotEmployee() {
        return this.em.createQuery("SELECT u FROM User u WHERE u.isEmployee <> true")
                .getResultList();
    }
    
}
