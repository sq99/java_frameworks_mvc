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
@ManagedBean(name="AddZast")
@RequestScoped
public class AddZastBean implements Serializable{

    private static Logger logger = Logger.getLogger(".control.AddZastBean");
    @EJB
    private DataBean db;
    
    private String nazwaNewZ;

    
    /**
     * Creates a new instance of AddZastBean
     */
    public AddZastBean() {
    }

    /**
     * @return the nazwaNewZ
     */
    public String getNazwaNewZ() {
        return nazwaNewZ;
    }

    /**
     * @param nazwaNewZ the nazwaNewZ to set
     */
    public void setNazwaNewZ(String nazwaNewZ) {
        this.nazwaNewZ = nazwaNewZ;
    }
    
    public void addNewZastosowanie()
    {
        try
        {
            db.addNewZast(nazwaNewZ);
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie dodac nowego zastosowania");
            e.printStackTrace();
        }
    }
    
}
