/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.pollitosdehierroapp.dao;

import com.pdh.pollitosdehierroapp.entity.PdhKid;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
/**
 *
 * @author lea
 */
@Stateless
public class KidDAO extends AbstractFacade<PdhKid> implements Serializable {
    
    private static final String ERROR_CREATING = "Error creando el registro";
    private static final String ERROR_EDITING = "Error editando el registro";
    private static final String ERROR_DELETING = "Error borrando el registro";
    private static final String ERROR_RETRIEVING = "Error recuperando datos";
    private static final String CODE_IN_USE = "Error, el código utilizado ya esta en uso";

    @PersistenceContext
    private EntityManager em;

    public KidDAO() {
        super(PdhKid.class);

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void create(PdhKid obj) {
        try {
            super.persist(obj);
        } catch (PersistenceException e) {
            e.printStackTrace();
            
        }
    }

    public void edit(PdhKid obj) {
        try {
            super.merge(obj);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    public void delete(PdhKid obj) {
        try {
            super.remove(obj);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public PdhKid findObj(Integer id) throws PersistenceException {
        return super.find(id);
    }

}
