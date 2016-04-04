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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Pracownik;
import model.Przedmiot;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name="WyswPrzedmPrac")
@ViewScoped
public class ControlPrzedmPracBean {

    private static Logger logger = Logger.getLogger(".control.WyswPrzedmPracBean");
    
    @EJB
    private DataBean db;
    
    private Pracownik pracownik;
    
    private int pracownikID;
    
    private List<Przedmiot> PrzedmPrac;
    
//    @PostConstruct
//    public void init() {
//        PrzedmPrac = db.getPrzedmiotyPracownika(pracownikID);
//        for(Przedmiot p : PrzedmPrac) {
//            logger.info(p.getNazwa());
//        }
//    }
    
    /**
     * Creates a new instance of ControlPrzedmPracBean
     */
    public ControlPrzedmPracBean() {
    }
    
    public List<Przedmiot> getListaPrzedmPrac() {
        try
        {
            this.PrzedmPrac = db.getPrzedmiotyPracownika(pracownikID);
        }
        
        catch(Exception e)
        {
            logger.warning("Nie udalo sie pobrac przedmiotow pracownika");
            e.printStackTrace();
        }
        return PrzedmPrac;
    }

    /**
     * @return the pracownikID
     */
    public int getPracownikID() {
        return pracownikID;
    }

    /**
     * @param pracownikID the pracownikID to set
     */
    public void setPracownikID(int pracownikID) {
        this.pracownikID = pracownikID;
        this.pracownik = db.getPracownikById(pracownikID);
    }
    
    public void deletePrzedmiotPracownika(Przedmiot przedmiot) {
       
        try
        {
            db.deletePrzedmPrac(przedmiot,this.pracownik);
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie usunac przedmiotu pracownikowi");
            e.printStackTrace();
        }
    
        //db.deletePrzedmiotPracownika(przedmiot,this.pracownik);
    }
    
}
