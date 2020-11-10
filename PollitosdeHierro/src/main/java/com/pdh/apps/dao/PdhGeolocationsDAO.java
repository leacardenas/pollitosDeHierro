/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.dao;

import com.pdh.apps.exception.ExceptionPollitosDeHierro;
import com.pdh.apps.model.PdhGeolocations;
import java.io.Serializable;
import java.util.List;
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
public class PdhGeolocationsDAO extends AbstractFacade<PdhGeolocations> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PdhGeolocationsDAO.class);
    private static final String ERROR_CREATING = "Error creando la localización";
    private static final String ERROR_EDITING = "Error editando la localización";
    private static final String ERROR_DELETING = "Error borrando la localización";
    private static final String ERROR_RETRIEVING = "Error recuerpando la localización";
    private static final String ERROR_CODE_IN_USE = "Error, este nombre ya está en uso. Por favor use otro";

    @PersistenceContext
    private EntityManager em;

    public PdhGeolocationsDAO() {
        super(PdhGeolocations.class);

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void create(PdhGeolocations obj) throws ExceptionPollitosDeHierro {
        try {
            super.persist(obj);
        } catch (PersistenceException e) {
            LOGGER.error((ERROR_CREATING + e));
            throw new ExceptionPollitosDeHierro((ERROR_CREATING + e));
        }
    }

    public void edit(PdhGeolocations obj) throws ExceptionPollitosDeHierro {
        try {
            super.merge(obj);
        } catch (PersistenceException e) {
            LOGGER.error((ERROR_EDITING + e));
            throw new ExceptionPollitosDeHierro((ERROR_EDITING + e));
        }
    }

    public void delete(PdhGeolocations obj) throws ExceptionPollitosDeHierro {
        try {
            super.remove(obj);
        } catch (PersistenceException e) {
            LOGGER.error((ERROR_DELETING + e));
            throw new ExceptionPollitosDeHierro((ERROR_DELETING + e));
        }
    }

    public void refresh() throws PersistenceException {
        super.refresh();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean validateCode(String code, Integer id, String action) throws ExceptionPollitosDeHierro {
        Query q;
        try {
            if ("create".equals(action)) {
                q = em.createQuery("Select obj from PdhGeolocations obj where obj.pdhGeolocationsName = :code")
                        .setParameter("code", code);
            } else {
                q = em.createQuery("Select obj from PdhGeolocations obj where obj.pdhGeolocationsName = :code and obj.pdhGeolocationsId != :id")
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
