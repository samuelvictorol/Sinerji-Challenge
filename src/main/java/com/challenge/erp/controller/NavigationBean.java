package com.challenge.erp.controller;

import javax.inject.Named;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
public class NavigationBean {

    public void redirectToHome()  throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getFlash().setKeepMessages(true);
        externalContext.redirect(externalContext.getRequestContextPath() + "/Home.xhtml");
    }
}
