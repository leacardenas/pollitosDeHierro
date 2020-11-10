/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.managed;

import com.pdh.apps.dao.PdhPersonDAO;
import com.pdh.apps.exception.ExceptionPollitosDeHierro;
import com.pdh.apps.model.PdhPerson;
import com.pdh.apps.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author leaca
 */
@Named
@ViewScoped
public class PdhPersonManaged implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PdhPersonManaged.class);
    private static final String CREATED = "Creado exitosamente";
    private static final String EDITED = "Editado exitosamente";
    private static final String DELETED = "Borrado exitosamente";
    private static final String ERROR = "Ha pasado un error";
    private static final String DUPLICATED = "Error, ya existe está localización";
    
    private List<PdhPerson> filteredPdhPerson;

    private PdhPerson selectedPdhPerson;

    @EJB
    private PdhPersonDAO pdhPersonDAO;

    @EJB
    private PdhPerson pdhPerson;

    @EJB
    private PdhPerson requestedPdhPersonById;

    @Inject
    private UserLoginManaged userLoginManaged;

    private List<PdhPerson> pdhPersonsList;

    public PdhPersonManaged() {
        /**
         * Creates a new instance of PdhPersonManaged
         */
    }

    @PostConstruct
    public void init() {
        pdhPerson = new PdhPerson();
    }

    public void create() {
        pdhPersonsList = null;
        try {
            if (pdhPersonDAO.validateCode(pdhPerson.getPdhPersonName(), pdhPerson.getPdhPersonId(), "create")) {
                pdhPerson.setPdhPersonCreatedBy(userLoginManaged.getUser());
                pdhPerson.setPdhPersonCreatedDate(getCurrentDate());
                pdhPersonDAO.create(pdhPerson);
                JsfUtil.addSuccessMessage(CREATED);
                JsfUtil.executeRequest("create-dlg");
                pdhPerson = new PdhPerson();
            } else {
                JsfUtil.addErrorMessage(DUPLICATED);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);

        }
    }

    public void edit() {
        pdhPersonsList = null;
        try {
            if (pdhPersonDAO.validateCode(requestedPdhPersonById.getPdhPersonName(), requestedPdhPersonById.getPdhPersonId(), "edit")) {
                requestedPdhPersonById.setPdhPersonUpdatedBy(userLoginManaged.getUser());
                requestedPdhPersonById.setPdhPersonUpdatedDate(getCurrentDate());
                pdhPersonDAO.edit(requestedPdhPersonById);
                JsfUtil.addSuccessMessage(EDITED);
                JsfUtil.executeRequest("edit-dlg");
            } else {
                JsfUtil.addErrorMessage(DUPLICATED);
            }

        } catch (Exception e) {
            LOGGER.error(e);
            if (e.getCause() instanceof ConstraintViolationException) {
                JsfUtil.addErrorMessage(DUPLICATED);
            } else {
                JsfUtil.addErrorMessage(ERROR + e);
            }
        }
    }

    public void delete() {
        pdhPersonsList = null;
        try {
            pdhPersonDAO.delete(requestedPdhPersonById);
            pdhPersonDAO.refresh();
            JsfUtil.addSuccessMessage(DELETED);
            JsfUtil.executeRequest("delete-dlg");
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
    }

    /**
     * @return the pdhPersons
     */
    public List<PdhPerson> getPdhPersons() {
        try {
            if (pdhPersonsList == null) {
                pdhPersonsList = pdhPersonDAO.findAll();
            }
            return pdhPersonsList;
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
        return null;
    }

    public List<PdhPerson> getFilteredPdhPerson() {
        return filteredPdhPerson;
    }

    public void setFilteredPdhPerson(List<PdhPerson> filteredPdhPerson) {
        this.filteredPdhPerson = filteredPdhPerson;
    }

    public PdhPerson getSelectedPdhPerson() {
        return selectedPdhPerson;
    }

    public void setSelectedPdhPerson(PdhPerson selectedPdhPerson) {
        this.selectedPdhPerson = selectedPdhPerson;
    }

    public PdhPerson getPdhPerson() {
        return pdhPerson;
    }

    public void setPdhPerson(PdhPerson pdhPerson) {
        this.pdhPerson = pdhPerson;
    }

    /**
     * @return the requestedPdhPersonById
     */
    public PdhPerson getRequestedPdhPersonById() {
        String id = JsfUtil.getRequestParameter("id");
        if (!StringUtils.isEmpty(id)) {
            requestedPdhPersonById = pdhPersonDAO.find(Integer.parseInt(id));
        }
        return requestedPdhPersonById;
    }

    /**
     * @param requestedPdhPersonById the requestedPdhPersonById to set
     */
    public void setRequestedPdhPersonById(PdhPerson requestedPdhPersonById) {
        this.requestedPdhPersonById = requestedPdhPersonById;
    }

    public List<PdhPerson> getPdhPersonList() {
        return pdhPersonsList;
    }

    public void setPdhPersonList(List<PdhPerson> pdhPersonsList) {
        this.pdhPersonsList = pdhPersonsList;
    }

    public Date getCurrentDate() {
        return userLoginManaged.getCurrentDate();
    }
}
