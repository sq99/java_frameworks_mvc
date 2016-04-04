/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Pracownik;
import model.Zastosowanie;
import model.Przedmiot;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name="DelZastPrzedmPrac")
@RequestScoped
public class DelZastPrzedmPracBean {

    
    private static Logger logger = Logger.getLogger(".control.ControlBean");
    
    @EJB
    private DataBean db;
    
    private String hello;
    private List<Zastosowanie> Zast;
    
    private Przedmiot przedmiot;
    
    private int przedmiotID;
    
    /**
     * Creates a new instance of DelZastPrzedmPracBean
     */
    public DelZastPrzedmPracBean() {
    }
    
    public void deleteZastPrzedmPrac(int zastID, int przedmID) {
       
        try
        {
            db.deleteZastPrzedmPrac(db.getZastById(zastID), db.getPrzedmiotById(przedmID));
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie usunac zastosowania z przedmiotu");
            e.printStackTrace();
        }
    
        //db.deletePrzedmiotPracownika(przedmiot,this.pracownik);
    }

    /**
     * @return the Zast
     */
    public List<Zastosowanie> getZast() {
        return Zast;
    }

    /**
     * @param Zast the Zast to set
     */
    public void setZast(List<Zastosowanie> Zast) {
        this.Zast = Zast;
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
        this.przedmiot = db.getPrzedmiotById(przedmiotID);
    }
    
}
