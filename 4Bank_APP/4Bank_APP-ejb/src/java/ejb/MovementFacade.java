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

    public List<Movement> FindAllMovementsByAccount(Account idACCOUNT) {
//       Query q = this.em.createQuery("SELECT m FROM Movement m WHERE (m.idACCOUNT = :idACCOUNT or m.idACCOUNT_receptor= :idACCOUNT) "
            Query q = this.em.createQuery("SELECT m FROM Movement m WHERE m.idACCOUNT = :idACCOUNT  "  );            
//            + "ORDER BY m.date;");
       q.setParameter("idACCOUNT", idACCOUNT);
       return q.getResultList();
    }
    
}
