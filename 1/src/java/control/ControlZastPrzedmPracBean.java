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
import model.Zastosowanie;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name="ControlZastPrzedmPrac")
@ViewScoped
public class ControlZastPrzedmPracBean implements Serializable{

    private static Logger logger = Logger.getLogger(".control.ControlZastPrzedmPracBean");
    
    private int przedmiotID;
    private int pracownikID;
    
    private List<Zastosowanie> zastWolne;
    private List<Zastosowanie> zastPrzedm;
    
    private Przedmiot przedmiot;
    private Pracownik pracownik;
    
    @EJB
    private DataBean db;
    /**
     * Creates a new instance of ControlZastPrzedmPracBean
     */
    public ControlZastPrzedmPracBean() {
    }
    
    @PostConstruct
    public void init() {
//        zastWolne = db.getZastWolne(przedmiotID);
//        for(Zastosowanie z : zastWolne) {
//            logger.info(z.getNazwa());
//        }
//        
//        zastPrzedm = db.getZastPrzedm(przedmiotID);
//        for(Zastosowanie z : zastPrzedm) {
//            logger.info(z.getNazwa());
//        }
        
        //pracownik = db.getPracownikById(this.pracownikID);
    }

    /**
     * @return the przedmiotID
     */
    public int getPrzedmiotID() {
        return przedmiotID;
    }

    /**
     * @param przedmiotID the przedmiotID to set
     */
    public void setPrzedmiotID(int przedmiotID) {
        this.przedmiotID = przedmiotID;
        this.setPrzedmiot(db.getPrzedmiotById(przedmiotID));
    }
   

    /**
     * @return the zastWolne
     */
    public List<Zastosowanie> getZastWolne(int przID) {
        zastWolne = db.getZastWolne(przID);
        return zastWolne;
    }

    /**
     * @param zastWolne the zastWolne to set
     */
    public void setZastWolne(List<Zastosowanie> zastWolne) {
        this.zastWolne = zastWolne;
    }

    /**
     * @param przID
     * @return the zastPrzedm
     */
    public List<Zastosowanie> getZastPrzedm(int przID) {
        zastPrzedm = db.getZastPrzedm(przID);
        return zastPrzedm;
    }

    /**
     * @param zastPrzedm the zastPrzedm to set
     */
    public void setZastPrzedm(List<Zastosowanie> zastPrzedm) {
        this.zastPrzedm = zastPrzedm;
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
        this.setPracownik(db.getPracownikById(pracownikID));
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
    
}
