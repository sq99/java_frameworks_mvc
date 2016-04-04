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
@ManagedBean(name = "Przedm2Prac")
@ViewScoped
public class Przedm2PracBean {

    private static Logger logger = Logger.getLogger(".control.Przedm2PracBean");
    @EJB
    private DataBean db;
    
    
    private int pracownikID;
    
    private Przedmiot wybranyPrzedmiot;
    private List<Przedmiot> BezWlasc;// = db.getPrzedmiotyBezWlasc();
    //private List<Przedmiot> PrzedmPrac = db.getPrzedmiotyPracownika(pracownikID);
    private Pracownik pracownik;// = db.getPracownikById(this.pracownikID);;
    
    @PostConstruct
    public void init() {
        BezWlasc = db.getPrzedmiotyBezWlasc();
        for(Przedmiot p : BezWlasc) {
            logger.info(p.getNazwa());
        }
        //pracownik = db.getPracownikById(this.pracownikID);
    }
    /**
     * Creates a new instance of Przedm2PracBean
     */
    public Przedm2PracBean() {
    }
    
    public void addPrzedm2Prac(Przedmiot przedmiot)
    {
        try
        {
                logger.info("id przedm:" +przedmiot.getId());
                logger.info("id prac:" +pracownik.getId());
                db.addPrzedm2Prac(pracownik, przedmiot);
            
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie dodac przedmiotu pracownikowi");
            e.printStackTrace();
        }
    }

    /**
     * @return the BezWlasc
     */
    public List<Przedmiot> getBezWlasc() {
        return BezWlasc;
    }

    /**
     * @param BezWlasc the BezWlasc to set
     */
    public void setBezWlasc(List<Przedmiot> BezWlasc) {
        this.BezWlasc = BezWlasc;
    }

    /**
     * @return the pracownik
     */
    public Pracownik getPracownik() {
        return pracownik;
    }

    /**
     * @param pracownik the pracownik to set
     */
    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    /**
     * @return the wybranyPrzedmiot
     */
    public Przedmiot getWybranyPrzedmiot() {
        return wybranyPrzedmiot;
    }

    /**
     * @param wybranyPrzedmiot the wybranyPrzedmiot to set
     */
    public void setWybranyPrzedmiot(Przedmiot wybranyPrzedmiot) {
        this.wybranyPrzedmiot = wybranyPrzedmiot;
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
    
}
