/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.pollitosdehierroapp.dao;

import com.pdh.pollitosdehierroapp.util.JsfUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 *
 * @author ToursysDevelopers
 */
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;
    private static final String ERROR = "Error:::";

    public AbstractFacade() {
        this.entityClass = null;
    }

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void persist(T entity) throws PersistenceException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if(constraintViolations.size() > 0){
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while(iterator.hasNext()){
                ConstraintViolation<T> cv = iterator.next();
                System.err.println(ERROR + cv.getRootBeanClass().getName()+"."+cv.getPropertyPath() + " " +cv.getMessage());
                
                JsfUtil.addErrorMessage(ERROR + cv.getRootBeanClass().getSimpleName()+"."+cv.getPropertyPath() + " " +cv.getMessage());
            }
        }else{
            getEntityManager().persist(entity);
        }
    }

    public void merge(T entity) throws PersistenceException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if(constraintViolations.size() > 0){
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while(iterator.hasNext()){
                ConstraintViolation<T> cv = iterator.next();
                System.err.println(ERROR + cv.getRootBeanClass().getName()+"."+cv.getPropertyPath() + " " +cv.getMessage());
                
                JsfUtil.addErrorMessage(ERROR + cv.getRootBeanClass().getSimpleName()+"."+cv.getPropertyPath() + " " +cv.getMessage());
            }
        }else{
            getEntityManager().merge(entity);
        }
    }

    public void remove(T entity) throws PersistenceException {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) throws PersistenceException {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() throws PersistenceException {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public void refresh() throws PersistenceException {
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }

}
