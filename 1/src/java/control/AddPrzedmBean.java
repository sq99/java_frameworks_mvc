/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "AddPrzedmiot")
@RequestScoped
public class AddPrzedmBean implements Serializable{

    private static Logger logger = Logger.getLogger(".control.AddPrzedmBean");
    @EJB
    private DataBean db;
    
    private String nazwaNewP;
    
    /**
     * Creates a new instance of AddPrzedmBean
     */
    public AddPrzedmBean() {
    }

    /**
     * @return the nazwaNewP
     */
    public String getNazwaNewP() {
        return nazwaNewP;
    }

    /**
     * @param nazwaNewP the nazwaNewP to set
     */
    public void setNazwaNewP(String nazwaNewP) {
        this.nazwaNewP = nazwaNewP;
    }
    
    public void addNewPrzedmiot()
    {
        try
        {
            db.addPrzedmiot(nazwaNewP);
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie dodac przedmiotu");
            e.printStackTrace();
        }
        
    }
    
}
