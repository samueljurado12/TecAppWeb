/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

}
