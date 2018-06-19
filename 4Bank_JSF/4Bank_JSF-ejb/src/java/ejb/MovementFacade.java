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

    public List<Movement> AllMovementsSearchByConcept(Account account, String pattern) {
        Query q = this.em.createQuery("SELECT m FROM Movement m WHERE m.concept LIKE :pattern AND "
                + "(m.idACCOUNT = :idACCOUNT OR m.idACCOUNTreceptor = :idACCOUNT) ORDER BY m.date DESC");
        q.setParameter("idACCOUNT", account);
        q.setParameter("pattern", "%" + pattern + "%");
        return q.getResultList();
    }

    public List<Movement> AllMovementsSearchByConceptAndEntity(Account account, String entity, String concept) {
        Query q = this.em.createQuery("SELECT m FROM Movement m WHERE m.concept LIKE :concept AND "
                + "(m.idACCOUNT = :idACCOUNT OR m.idACCOUNTreceptor = :idACCOUNT)"
                + " AND ((m.idACCOUNTreceptor.idUSER.name"
                + " LIKE :pattern OR m.idACCOUNTreceptor.idUSER.surname LIKE :pattern) AND m.idACCOUNT ="
                + " :idACCOUNT) OR (m.idACCOUNTreceptor = :idACCOUNT AND (m.idACCOUNT.idUSER.name LIKE "
                + ":pattern OR m.idACCOUNT.idUSER.surname LIKE :pattern)) ORDER BY m.date DESC")
                .setParameter("idACCOUNT", account)
                .setParameter("concept", "%" + concept + "%")
                .setParameter("pattern", "%" + entity + "%");
        return q.getResultList();
    }

    public List<Movement> AllMovementsSearchByEntity(Account account, String pattern) {
        Query q = this.em.createQuery("SELECT m FROM Movement m WHERE ((m.idACCOUNTreceptor.idUSER.name"
                + " LIKE :pattern OR m.idACCOUNTreceptor.idUSER.surname LIKE :pattern) AND m.idACCOUNT ="
                + " :idACCOUNT) OR (m.idACCOUNTreceptor = :idACCOUNT AND (m.idACCOUNT.idUSER.name LIKE "
                + ":pattern OR m.idACCOUNT.idUSER.surname LIKE :pattern)) ORDER BY m.date DESC");
        q.setParameter("idACCOUNT", account);

        q.setParameter("pattern", "%" + pattern + "%");
        return q.getResultList();
    }
}
