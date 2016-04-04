/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Zastosowanie;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name="WyswZast")
@RequestScoped
public class ControlZastBean implements Serializable {
    
    private static Logger logger = Logger.getLogger(".control.ControlBean");
    
    @EJB
    private DataBean db;
    
    private String hello;
    private List<Zastosowanie> Zast;

    /**
     * Creates a new instance of ControlZast
     */
    public ControlZastBean() {
    }
    
    public List<Zastosowanie> getZast() {
        try
        {
            this.Zast = db.getZast();
        }
        
        catch(Exception e)
        {
            logger.warning("Nie udalo sie pobrac pracownikow");
            e.printStackTrace();
        }
        return Zast;
    }
    
}
