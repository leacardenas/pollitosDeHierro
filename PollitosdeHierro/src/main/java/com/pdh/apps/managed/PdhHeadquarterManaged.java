/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.managed;

import com.pdh.apps.dao.PdhHeadquartersDAO;
import com.pdh.apps.model.PdhHeadquarter;
import com.pdh.apps.util.JsfUtil;
import java.io.Serializable;
import java.util.Date;

import java.util.List;
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
public class PdhHeadquarterManaged implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PdhHeadquarterManaged.class);
    private static final String CREATED = "Creado exitosamente";
    private static final String EDITED = "Editado exitosamente";
    private static final String DELETED = "Borrado exitosamente";
    private static final String ERROR = "Ha pasado un error";
    private static final String DUPLICATED = "Error, ya existe está localización";
    
    private List<PdhHeadquarter> filteredPdhHeadquarter;

    private PdhHeadquarter selectedPdhHeadquarter;

    @EJB
    private PdhHeadquartersDAO pdhHeadquarterDAO;

    @EJB
    private PdhHeadquarter pdhHeadquarter;

    @EJB
    private PdhHeadquarter requestedPdhHeadquarterById;

    @Inject
    private UserLoginManaged userLoginManaged;

    private List<PdhHeadquarter> pdhHeadquartersList;

    public PdhHeadquarterManaged() {
        /**
         * Creates a new instance of PdhHeadquarterManaged
         */
    }

    @PostConstruct
    public void init() {
        pdhHeadquarter = new PdhHeadquarter();
    }

    public void create() {
        pdhHeadquartersList = null;
        try {
            if (pdhHeadquarterDAO.validateCode(pdhHeadquarter.getPdhHeadquarterName(), pdhHeadquarter.getPdhHeadquarterId(), "create")) {
                pdhHeadquarter.setPdhHeadquarterCreatedBy(userLoginManaged.getUser());
                pdhHeadquarter.setPdhHeadquarterCreatedDate(getCurrentDate());
                pdhHeadquarterDAO.create(pdhHeadquarter);
                JsfUtil.addSuccessMessage(CREATED);
                JsfUtil.executeRequest("create-dlg");
                pdhHeadquarter = new PdhHeadquarter();
            } else {
                JsfUtil.addErrorMessage(DUPLICATED);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);

        }
    }

    public void edit() {
        pdhHeadquartersList = null;
        try {
            if (pdhHeadquarterDAO.validateCode(requestedPdhHeadquarterById.getPdhHeadquarterName(), requestedPdhHeadquarterById.getPdhHeadquarterId(), "edit")) {
                requestedPdhHeadquarterById.setPdhHeadquarterUpdatedBy(userLoginManaged.getUser());
                requestedPdhHeadquarterById.setPdhHeadquarterUpdatedDate(getCurrentDate());
                pdhHeadquarterDAO.edit(requestedPdhHeadquarterById);
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
        pdhHeadquartersList = null;
        try {
            pdhHeadquarterDAO.delete(requestedPdhHeadquarterById);
            pdhHeadquarterDAO.refresh();
            JsfUtil.addSuccessMessage(DELETED);
            JsfUtil.executeRequest("delete-dlg");
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
    }

    /**
     * @return the pdhHeadquarters
     */
    public List<PdhHeadquarter> getPdhHeadquarters() {
        try {
            if (pdhHeadquartersList == null) {
                pdhHeadquartersList = pdhHeadquarterDAO.findAll();
            }
            return pdhHeadquartersList;
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
        return null;
    }

    public List<PdhHeadquarter> getFilteredPdhHeadquarter() {
        return filteredPdhHeadquarter;
    }

    public void setFilteredPdhHeadquarter(List<PdhHeadquarter> filteredPdhHeadquarter) {
        this.filteredPdhHeadquarter = filteredPdhHeadquarter;
    }

    public PdhHeadquarter getSelectedPdhHeadquarter() {
        return selectedPdhHeadquarter;
    }

    public void setSelectedPdhHeadquarter(PdhHeadquarter selectedPdhHeadquarter) {
        this.selectedPdhHeadquarter = selectedPdhHeadquarter;
    }

    public PdhHeadquarter getPdhHeadquarter() {
        return pdhHeadquarter;
    }

    public void setPdhHeadquarter(PdhHeadquarter pdhHeadquarter) {
        this.pdhHeadquarter = pdhHeadquarter;
    }

    /**
     * @return the requestedPdhHeadquarterById
     */
    public PdhHeadquarter getRequestedPdhHeadquarterById() {
        String id = JsfUtil.getRequestParameter("id");
        if (!StringUtils.isEmpty(id)) {
            requestedPdhHeadquarterById = pdhHeadquarterDAO.find(Integer.parseInt(id));
        }
        return requestedPdhHeadquarterById;
    }

    /**
     * @param requestedPdhHeadquarterById the requestedPdhHeadquarterById to set
     */
    public void setRequestedPdhHeadquarterById(PdhHeadquarter requestedPdhHeadquarterById) {
        this.requestedPdhHeadquarterById = requestedPdhHeadquarterById;
    }

    public List<PdhHeadquarter> getPdhHeadquarterList() {
        return pdhHeadquartersList;
    }

    public void setPdhHeadquarterList(List<PdhHeadquarter> pdhHeadquartersList) {
        this.pdhHeadquartersList = pdhHeadquartersList;
    }

    public Date getCurrentDate() {
        return userLoginManaged.getCurrentDate();
    }
}
