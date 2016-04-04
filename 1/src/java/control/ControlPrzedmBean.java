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
import model.Przedmiot;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "Controler2")
@RequestScoped
public class ControlPrzedmBean implements Serializable {

    private static Logger logger = Logger.getLogger(".control.ControlPrzedmPracBean");
    
    @EJB
    private DataBean db;
    
    private String hello;
    private List<Przedmiot> ListaPrzedm;
    private List<Przedmiot> ListaPrzedmBezWlasc;
    /**
     * Creates a new instance of ControlPrzedmBean
     */
    public ControlPrzedmBean() {
    }
    
    public List<Przedmiot> getListaPrzedm() {
        try
        {
            this.ListaPrzedm = db.getPrzedmioty();
        }
        
        catch(Exception e)
        {
            logger.warning("Nie udalo sie pobrac przedmiotow");
            e.printStackTrace();
        }
        return ListaPrzedm;
    }
    
    public List<Przedmiot> getListaPrzedmBezWlasc() {
        try
        {
            this.ListaPrzedmBezWlasc = db.getPrzedmiotyBezWlasc();
        }
        
        catch(Exception e)
        {
            logger.warning("Nie udalo sie pobrac przedmiotow");
            e.printStackTrace();
        }
        return ListaPrzedm;
    }
    
}
