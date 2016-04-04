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
import model.Pracownik;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "Controler1")
@RequestScoped
public class ControlPracBean implements Serializable {
    
    private static Logger logger = Logger.getLogger(".control.ControlPracBean");
    
    @EJB
    private DataBean db;
    
    private String hello;
    private List<Pracownik> Zatrudnieni;
    

    /**
     * Creates a new instance of ControlBean
    */

    
    public ControlPracBean() {
    }

    /**
     * @return the hello
     */
    public String getHello() {
        return "Hello!";
    }

    /**
     * @param hello the hello to set
     */
    public void setHello(String hello) {
        this.hello = hello;
    }

    /**
     * @return the Zatrudnieni
     */
    public List<Pracownik> getZatrudnieni() {
        try
        {
            this.Zatrudnieni = db.getPracownicy();
        }
        
        catch(Exception e)
        {
            logger.warning("Nie udalo sie pobrac pracownikow");
            e.printStackTrace();
        }
        return Zatrudnieni;
    }
    
}
