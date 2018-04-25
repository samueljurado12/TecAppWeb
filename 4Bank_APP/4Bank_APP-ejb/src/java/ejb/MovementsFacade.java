/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.time.LocalDateTime;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.Movements;

/**
 *
 * @author sjuradoq
 */
@Stateless
public class MovementsFacade extends AbstractFacade<Movements> {

    @PersistenceContext(unitName = "4Bank_APP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovementsFacade() {
        super(Movements.class);
    }
    
    public void createMovement(int userID, int senderAccountID, int receiverID, 
            String concept, float amount, float newBalance, LocalDateTime date){
        Query q = this.em.createNativeQuery("INSERT INTO MOVEMENTS (idUSERS, idACCOUNT, "
                + "idUSERS_receptor, concept, amount, new_balance, date) VALUES "
                + "( :idUSERS, :idACCOUNT, :idUSERS_receptor, :concept, :amount, :new_balance, :date);");
        q.setParameter("idUSERS", userID);
        q.setParameter("idACCOUNT", senderAccountID);
        q.setParameter("idUSERS_receptor", receiverID);
        q.setParameter("concept", concept);
        q.setParameter("amount", amount);
        q.setParameter("new_balance", newBalance);
        q.setParameter("date", date);

        
        
        q.executeUpdate();
    }
    
}
