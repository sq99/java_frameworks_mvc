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
import model.Zastosowanie;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name="DelZast")
@RequestScoped
public class DelZastBean {
    
    private static Logger logger = Logger.getLogger(".control.DelPracBean");
    @EJB
    private DataBean db;

    /**
     * Creates a new instance of DelZastBean
     */
    public DelZastBean() {
    }
    
    public void delZastosowanie(Zastosowanie zastosowanie)
    {
        
        try
        {
            db.deleteZastosowanie(zastosowanie);
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie usunac pracownika");
            e.printStackTrace();
        }
    }
    
}
