/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.dao;

import com.pdh.apps.exception.ExceptionPollitosDeHierro;
import com.pdh.apps.model.PdhRole;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author leaca
 */
@Stateless
public class PdhRoleDAO extends AbstractFacade<PdhRole> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PdhRoleDAO.class);
    private static final String ERROR_CREATING = "Error creando el rol";
    private static final String ERROR_EDITING = "Error editando el rol";
    private static final String ERROR_DELETING = "Error borrando el rol";
    private static final String ERROR_RETRIEVING = "Error recuerpando el rol";
    private static final String ERROR_CODE_IN_USE = "Error, este nombre ya está en uso. Por favor use otro";

    @PersistenceContext
    private EntityManager em;

    public PdhRoleDAO() {
        super(PdhRole.class);

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void create(PdhRole obj) throws ExceptionPollitosDeHierro {
        try {
            super.persist(obj);
        } catch (PersistenceException e) {
            LOGGER.error((ERROR_CREATING + e));
            throw new ExceptionPollitosDeHierro((ERROR_CREATING + e));
        }
    }

    public void edit(PdhRole obj) throws ExceptionPollitosDeHierro {
        try {
            super.merge(obj);
        } catch (PersistenceException e) {
            LOGGER.error((ERROR_EDITING + e));
            throw new ExceptionPollitosDeHierro((ERROR_EDITING + e));
        }
    }

    public void delete(PdhRole obj) throws ExceptionPollitosDeHierro {
        try {
            super.remove(obj);
        } catch (PersistenceException e) {
            LOGGER.error((ERROR_DELETING + e));
            throw new ExceptionPollitosDeHierro((ERROR_DELETING + e));
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean validateCode(String code, Integer id, String action) throws ExceptionPollitosDeHierro {
        Query q;
        try {
            if ("create".equals(action)) {
                q = em.createQuery("Select obj from PdhRole obj where obj.pdhRoleName = :code")
                        .setParameter("code", code);
            } else {
                q = em.createQuery("Select obj from PdhRole obj where obj.pdhRoleName = :code and obj.pdhRoleId != :id")
                        .setParameter("code", code)
                        .setParameter("id", id);
            }
            return q.getResultList().isEmpty();
        } catch (Exception e) {
            LOGGER.error((ERROR_CODE_IN_USE + e));
            throw new ExceptionPollitosDeHierro((ERROR_CODE_IN_USE + e));
        }
    }
}
