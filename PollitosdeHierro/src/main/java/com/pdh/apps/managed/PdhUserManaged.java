/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.managed;

import com.pdh.apps.dao.PdhUsersDAO;
import com.pdh.apps.model.PdhUser;
import com.pdh.apps.util.JsfUtil;
import java.io.Serializable;
import java.util.Date;

import java.util.List;
import java.util.TimeZone;
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
public class PdhUserManaged implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PdhUserManaged.class);
    private static final String CREATED = "Creado exitosamente";
    private static final String EDITED = "Editado exitosamente";
    private static final String DELETED = "Borrado exitosamente";
    private static final String ERROR = "Ha pasado un error";
    private static final String DUPLICATED = "Error, ya existe está localización";
    
    private List<PdhUser> filteredPdhUser;

    private PdhUser selectedPdhUser;

    @EJB
    private PdhUsersDAO pdhUserDAO;

    @EJB
    private PdhUser pdhUser;

    @EJB
    private PdhUser requestedPdhUserById;

    @Inject
    private UserLoginManaged userLoginManaged;

    private List<PdhUser> pdhUsersList;
    
    private String[] timeZoneIDs;

    public PdhUserManaged() {
        /**
         * Creates a new instance of PdhUserManaged
         */
    }

    @PostConstruct
    public void init() {
        pdhUser = new PdhUser();
        timeZoneIDs = TimeZone.getAvailableIDs();
    }

    public void create() {
        pdhUsersList = null;
        try {
            if (pdhUserDAO.validateCode(pdhUser.getPdhUserName(), pdhUser.getPdhUserId(), "create")) {
                pdhUser.setPdhUserCreatedBy(userLoginManaged.getUser());
                pdhUser.setPdhUserCreatedDate(getCurrentDate());
                pdhUserDAO.create(pdhUser);
                JsfUtil.addSuccessMessage(CREATED);
                JsfUtil.executeRequest("create-dlg");
                pdhUser = new PdhUser();
            } else {
                JsfUtil.addErrorMessage(DUPLICATED);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);

        }
    }

    public void edit() {
        pdhUsersList = null;
        try {
            if (pdhUserDAO.validateCode(requestedPdhUserById.getPdhUserName(), requestedPdhUserById.getPdhUserId(), "edit")) {
                requestedPdhUserById.setPdhUserUpdatedBy(userLoginManaged.getUser());
                requestedPdhUserById.setPdhUserUpdatedDate(getCurrentDate());
                pdhUserDAO.edit(requestedPdhUserById);
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
        pdhUsersList = null;
        try {
            pdhUserDAO.delete(requestedPdhUserById);
            pdhUserDAO.refresh();
            JsfUtil.addSuccessMessage(DELETED);
            JsfUtil.executeRequest("delete-dlg");
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
    }

    /**
     * @return the pdhUsers
     */
    public List<PdhUser> getPdhUsers() {
        try {
            if (pdhUsersList == null) {
                pdhUsersList = pdhUserDAO.findAll();
            }
            return pdhUsersList;
        } catch (Exception e) {
            LOGGER.error(e);
            JsfUtil.addErrorMessage(ERROR + e);
        }
        return null;
    }

    public List<PdhUser> getFilteredPdhUser() {
        return filteredPdhUser;
    }

    public void setFilteredPdhUser(List<PdhUser> filteredPdhUser) {
        this.filteredPdhUser = filteredPdhUser;
    }

    public PdhUser getSelectedPdhUser() {
        return selectedPdhUser;
    }

    public void setSelectedPdhUser(PdhUser selectedPdhUser) {
        this.selectedPdhUser = selectedPdhUser;
    }

    public PdhUser getPdhUser() {
        return pdhUser;
    }

    public void setPdhUser(PdhUser pdhUser) {
        this.pdhUser = pdhUser;
    }

    /**
     * @return the requestedPdhUserById
     */
    public PdhUser getRequestedPdhUserById() {
        String id = JsfUtil.getRequestParameter("id");
        if (!StringUtils.isEmpty(id)) {
            requestedPdhUserById = pdhUserDAO.find(Integer.parseInt(id));
        }
        return requestedPdhUserById;
    }

    /**
     * @param requestedPdhUserById the requestedPdhUserById to set
     */
    public void setRequestedPdhUserById(PdhUser requestedPdhUserById) {
        this.requestedPdhUserById = requestedPdhUserById;
    }

    public List<PdhUser> getPdhUserList() {
        return pdhUsersList;
    }

    public void setPdhUserList(List<PdhUser> pdhUsersList) {
        this.pdhUsersList = pdhUsersList;
    }

    public Date getCurrentDate() {
        return userLoginManaged.getCurrentDate();
    }

    public String[] getTimeZoneIDs() {
        return timeZoneIDs;
    }

    public void setTimeZoneIDs(String[] timeZoneIDs) {
        this.timeZoneIDs = timeZoneIDs;
    }
    
    public void isHeadquarterManager(PdhUser user){
        user.setPdhUserIsSponsor(Boolean.FALSE);
        user.setPdhUserIsVoluntary(Boolean.FALSE);
    }
    
    public void isSponsor(PdhUser user){
        user.setPdhUserIsHeadquarterManager(Boolean.FALSE);
        user.setPdhUserIsVoluntary(Boolean.FALSE);
    }
    
    public void isVoluntary(PdhUser user){
        user.setPdhUserIsSponsor(Boolean.FALSE);
        user.setPdhUserIsHeadquarterManager(Boolean.FALSE);
    }
    
    public void isAdmin(PdhUser user){
        user.setPdhUserIsIt(Boolean.FALSE);
    }
}
