/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Pracownik;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "DeletePracownik")
@RequestScoped
public class DelPracBean {
    private static Logger logger = Logger.getLogger(".control.DelPracBean");
    @EJB
    private DataBean db;
    

    /**
     * Creates a new instance of DeleteBean
     */
    public DelPracBean() {
    }
    
    
     
    
    public void delPracownik(Pracownik pracownik)
    {
        try
        {
            db.deletePracownik(pracownik);
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie usunac pracownika");
            e.printStackTrace();
        }
    }
    
}
