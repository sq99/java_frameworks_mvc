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
import model.Zastosowanie;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
/**
 *
 * @author serq9_000
 */
@ManagedBean(name="AddZastPrzedmPrac")
@RequestScoped
public class AddZastPrzedmPracBean {

    private static Logger logger = Logger.getLogger(".control.AddZastPrzedmPracBean");
    @EJB
    private DataBean db;
    
    
    private int przedmID;
    
    private Zastosowanie wybranyZast;
    private List<Przedmiot> ZWlasc;// = db.getPrzedmiotyBezWlasc();
    //private List<Przedmiot> PrzedmPrac = db.getPrzedmiotyPracownika(pracownikID);
    private Pracownik pracownik;
    private Przedmiot przedmiot;
    private List<Zastosowanie> dostepne;
    
    /**
     * Creates a new instance of AddZastPrzedmPracBean
     */
    public AddZastPrzedmPracBean() {
    }
    
    @PostConstruct
    public void init() {
        setZWlasc(db.getPrzedmiotyZWlasc());
        for(Przedmiot p : getZWlasc()) {
            logger.info(p.getNazwa());
        }   
        setDostepne(db.getZastWolne(przedmID));
        for(Zastosowanie z : getDostepne()) {
            logger.info(z.getNazwa());
        
        }
        //pracownik = db.getPracownikById(this.pracownikID);
    }

    /**
     * @return the ZWlasc
     */
    public List<Przedmiot> getZWlasc() {
        return ZWlasc;
    }

    /**
     * @param ZWlasc the ZWlasc to set
     */
    public void setZWlasc(List<Przedmiot> ZWlasc) {
        this.ZWlasc = ZWlasc;
    }

    /**
     * @return the przedmID
     */
    public int getPrzedmID() {
        return przedmID;
    }

    /**
     * @param przedmID the przedmID to set
     */
    public void setPrzedmID(int przedmID) {
        this.przedmID = przedmID;
        this.przedmiot = db.getPrzedmiotById(przedmID);
    }

    /**
     * @return the wybranyZast
     */
    public Zastosowanie getWybranyZast() {
        return wybranyZast;
    }

    /**
     * @param wybranyZast the wybranyZast to set
     */
    public void setWybranyZast(Zastosowanie wybranyZast) {
        this.wybranyZast = wybranyZast;
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
     * @return the przedmiot
     */
    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    /**
     * @param przedmiot the przedmiot to set
     */
    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }
    
    public void addZastPrzedmPrac(Zastosowanie zast)
    {
        try
        {
                logger.info("id zast:" +zast.getId());
                logger.info("id przedm:" +this.getPrzedmID());
                db.addZastPrzedm(zast, przedmiot);
            
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie dodac zastosowania do przedmiotu");
            e.printStackTrace();
        }
    }

    /**
     * @return the dostepne
     */
    public List<Zastosowanie> getDostepne() {
        return dostepne;
    }

    /**
     * @param dostepne the dostepne to set
     */
    public void setDostepne(List<Zastosowanie> dostepne) {
        this.dostepne = dostepne;
    }
    
}
