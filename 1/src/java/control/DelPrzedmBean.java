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
import model.Przedmiot;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "DeletePrzedmiot")
@RequestScoped
public class DelPrzedmBean {
    private static Logger logger = Logger.getLogger(".control.DelPrzedmBean");
    @EJB
    private DataBean db;
    

    /**
     * Creates a new instance of DeleteBean
     */
    public DelPrzedmBean() {
    }
    
    
     
    
    public void delPrzedm(Przedmiot przedmiot)
    {
        try
        {
            db.deletePrzedmiot(przedmiot);
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie usunac przedmiotu");
            e.printStackTrace();
        }
    }
    
}
