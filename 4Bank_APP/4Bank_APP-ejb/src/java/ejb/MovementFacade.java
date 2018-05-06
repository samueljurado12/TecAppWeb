/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.Account;
import persistence.Movement;

/**
 *
 * @author sjuradoq
 */
@Stateless
public class MovementFacade extends AbstractFacade<Movement> {

    @PersistenceContext(unitName = "4Bank_APP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovementFacade() {
        super(Movement.class);
    }
    
    
    public List<Movement> queryAllMovementsFromAndToAccount(Account account) {
        Query q = this.em.createQuery("SELECT m FROM Movement m WHERE m.idACCOUNT = :idACCOUNT "
                + "OR m.idACCOUNTreceptor = :idACCOUNT ORDER BY m.date DESC");
        q.setParameter("idACCOUNT", account);
        return q.getResultList();
    }
    
    public List<Movement> AllMovementsOrderByConcept(Account account){
        Query q = this.em.createQuery("select * from movement m where m.idACCOUNT = :idACCOUNT order by m.concept");
        q.setParameter("idACCOUNT", account);
        return q.getResultList();
    }
    
    public List<Movement> AllMovementsOrderByDate(Account account){
        Query q = this.em.createQuery("select * from movement m order by m.date");
        q.setParameter("idACCOUNT", account);
        return q.getResultList();
    }
    
    public List<Movement> AllMovementsOrderByAmount(Account account){
        Query q = this.em.createQuery("select * from movement m where m.idACCOUNT = :idACCOUNT order by m.amount");
        q.setParameter("idACCOUNT", account);
        return q.getResultList();
    }
}
