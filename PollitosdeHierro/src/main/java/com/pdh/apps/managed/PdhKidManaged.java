/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.managed;

import com.pdh.apps.dao.PdhKidDAO;
import com.pdh.apps.exception.ExceptionPollitosDeHierro;
import com.pdh.apps.model.PdhKid;
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
public class PdhKidManaged implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PdhKidManaged.class);
    private static final String CREATED = "Creado exitosamente";
    private static final String EDITED = "Editado exitosamente";
    private static final String DELETED = "Borrado exitosamente";
    private static final String ERROR = "Ha pasado un error";
    private static final String DUPLICATED = "Error, ya existe este niño";
    
    private List<PdhKid> filteredPdhKid;

    private PdhKid selectedPdhKid;

    @EJB
    private PdhKidDAO pdhKidDAO;

    @EJB
    private PdhKid pdhKid;

    @EJB
    private PdhKid requestedPdhKidById;

    @Inject
    private UserLoginManaged userLoginManaged;

    private List<PdhKid> pdhKidsList;

    public PdhKidManaged() {
        /**
         * Creates a new instance of PdhKidManaged
         */
    }

    @PostConstruct
    public void init() {
        pdhKid = new PdhKid();
    }

    public void create() {
        pdhKidsList = null;
        try {
            if (pdhKidDAO.validateCode(pdhKid.getPdhKidName(), pdhKid.getPdhKidId(), "create")) {
                pdhKid.setPdhKidCreatedBy(userLoginManaged.getUser());
                pdhKid.setPdhKidCreatedDate(getCurrentDate());
                pdhKidDAO.create(pdhKid);
                JsfUtil.addSuccessMessage(CREATED);
                JsfUtil.redirect("../Pollitos-De-Hierro-Ninos/");
                pdhKid = new PdhKid();
            } else {
                JsfUtil.addErrorMessage(DUPLICATED);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);

        }
    }

    public void edit() {
        pdhKidsList = null;
        try {
            if (pdhKidDAO.validateCode(requestedPdhKidById.getPdhKidName(), requestedPdhKidById.getPdhKidId(), "edit")) {
                requestedPdhKidById.setPdhKidUpdatedBy(userLoginManaged.getUser());
                requestedPdhKidById.setPdhKidUpdatedDate(getCurrentDate());
                pdhKidDAO.edit(requestedPdhKidById);
                JsfUtil.addSuccessMessage(EDITED);
                JsfUtil.redirect("../Pollitos-De-Hierro-Ninos-Editar/?id=" + requestedPdhKidById.getPdhKidId());
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
        pdhKidsList = null;
        try {
            pdhKidDAO.delete(requestedPdhKidById);
            pdhKidDAO.refresh();
            JsfUtil.addSuccessMessage(DELETED);
            JsfUtil.executeRequest("delete-dlg");
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
    }

    /**
     * @return the pdhKids
     */
    public List<PdhKid> getPdhKids() {
        try {
            if (pdhKidsList == null) {
                pdhKidsList = pdhKidDAO.findAll();
            }
            return pdhKidsList;
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
        return null;
    }

    public List<PdhKid> getFilteredPdhKid() {
        return filteredPdhKid;
    }

    public void setFilteredPdhKid(List<PdhKid> filteredPdhKid) {
        this.filteredPdhKid = filteredPdhKid;
    }

    public PdhKid getSelectedPdhKid() {
        return selectedPdhKid;
    }

    public void setSelectedPdhKid(PdhKid selectedPdhKid) {
        this.selectedPdhKid = selectedPdhKid;
    }

    public PdhKid getPdhKid() {
        return pdhKid;
    }

    public void setPdhKid(PdhKid pdhKid) {
        this.pdhKid = pdhKid;
    }

    /**
     * @return the requestedPdhKidById
     */
    public PdhKid getRequestedPdhKidById() {
        String id = JsfUtil.getRequestParameter("id");
        if (!StringUtils.isEmpty(id)) {
            requestedPdhKidById = pdhKidDAO.find(Integer.parseInt(id));
        }
        return requestedPdhKidById;
    }

    /**
     * @param requestedPdhKidById the requestedPdhKidById to set
     */
    public void setRequestedPdhKidById(PdhKid requestedPdhKidById) {
        this.requestedPdhKidById = requestedPdhKidById;
    }

    public List<PdhKid> getPdhKidList() {
        return pdhKidsList;
    }

    public void setPdhKidList(List<PdhKid> pdhKidsList) {
        this.pdhKidsList = pdhKidsList;
    }

    public Date getCurrentDate() {
        return userLoginManaged.getCurrentDate();
    }
}
