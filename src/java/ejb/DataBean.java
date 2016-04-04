/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Pracownik;
import model.Przedmiot;
import model.Zastosowanie;

/**
 *
 * @author serq9_000
 */
@Stateful
public class DataBean {

    private static Logger logger = Logger.getLogger(".ejb.DataBean");
    
@PersistenceContext
private EntityManager em;

public List<Pracownik> getPracownicy(){

    List<Pracownik> pracownicy = null;
    try{
    pracownicy = (List<Pracownik>) em.createNamedQuery("Pracownik.findAll").getResultList(); 
    logger.info("Odczytano pracownikow");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return pracownicy;
}

public Pracownik getPracownik(int id, String imie, String nazwisko){

    Pracownik Znaleziony = null;
    try{
    Znaleziony = (Pracownik) em.createNamedQuery("Pracownik.findById").getParameter("ID"); 
    logger.info("Znaleziono pracownika: "+imie +" " +nazwisko);
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return Znaleziony;
}

public void addPracownik(String imie, String nazwisko)
{
        try
        {
            Pracownik Dodawany = new Pracownik(Integer.valueOf(1), imie, nazwisko);
            logger.info("Dodaje uzytkownika: "+ imie + " " + nazwisko);
            em.persist(Dodawany);
            logger.info("Dodano uzytkownika: "+ imie + " " + nazwisko);
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public void deletePracownik(Pracownik pracownik)
{
        try
        {
            logger.info("Usuwam uzytkownika: "+pracownik.getImie() +" " +pracownik.getNazwisko());
            Pracownik Usuwany = em.find(Pracownik.class, pracownik.getId());
            em.remove(Usuwany);
            logger.info("Usunieto uzytkownika: "+pracownik.getImie() +" " +pracownik.getNazwisko());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public void addPrzedmiot(String nazwa)
{
        try
        {
            Przedmiot Nowy = new Przedmiot(Integer.valueOf(1), nazwa);
            logger.info("Dodaje przedmiot: "+ nazwa);
            em.persist(Nowy);
            logger.info("Dodano przedmiot: "+ nazwa);
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public List<Przedmiot> getPrzedmioty(){

    List<Przedmiot> przedmioty = null;
    try{
    przedmioty = (List<Przedmiot>) em.createNamedQuery("Przedmiot.findAll").getResultList(); 
    logger.info("Odczytano przedmioty" + przedmioty.get(0).getNazwa());
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return przedmioty;
}

public List<Przedmiot> getPrzedmiotyBezWlasc(){

    List<Przedmiot> przedmioty = null;
    try{
    przedmioty = (List<Przedmiot>) em.createNamedQuery("Przedmiot.findAllBezWlasc").getResultList(); 
    logger.info("Odczytano przedmioty bez wlasciciela");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return przedmioty;
}

 public List<Przedmiot> getPrzedmiotyPracownika(int id) {
        
     List<Przedmiot> przedmioty = null;
        Pracownik pracownik = null;
        try {
            
            pracownik = (Pracownik) em.createNamedQuery("Pracownik.findById").setParameter("id", id).getSingleResult();
            przedmioty = (List<Przedmiot>)pracownik.getPrzedmiotList();
            
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        return przedmioty;
    }

public void deletePrzedmiot(Przedmiot przedmiot)
{
        try
        {
            logger.info("Usuwam przedmiot: "+przedmiot.getNazwa());
            Przedmiot Usuwany = em.find(Przedmiot.class, przedmiot.getId());
            em.remove(Usuwany);
            logger.info("Usunieto przedmiot: "+przedmiot.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public Pracownik getPracownikById(int id) {
        Pracownik pracownik = null;
        try {
            pracownik = (Pracownik) em.createNamedQuery("Pracownik.findById")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        return pracownik;
    }

public void addPrzedm2Prac(Pracownik pracownik, Przedmiot przedmiot)
{
        try
        {
            logger.info("id prac1: "+pracownik.getId());
            logger.info("id przedm1: "+przedmiot.getId());
            pracownik = em.getReference(Pracownik.class, pracownik.getId());
            przedmiot = em.getReference(Przedmiot.class, przedmiot.getId());
            logger.info("Dodaje pracownikowi "+pracownik.getImie() +" " +pracownik.getNazwisko() +" przedmiot :" +przedmiot.getNazwa());
            //Pracownik Znaleziony = em.find(Pracownik.class, pracownik.getId());
            //Przedmiot Znaleziony2 = em.find(Przedmiot.class, przedmiot.getId());
            logger.info("id prac: "+pracownik.getId());
            logger.info("id przedm: "+przedmiot.getId());
            przedmiot.setIdPracownik(pracownik);
            pracownik.getPrzedmiotList().add(przedmiot);
            logger.info("Dodano pracownikowi "+pracownik.getImie() +" " +pracownik.getNazwisko() +" przedmiot :" +przedmiot.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

    public void deletePrzedmPrac(Przedmiot przedmiot, Pracownik pracownik)
    {
        try
        {
            Pracownik Usuwany = em.find(Pracownik.class, pracownik.getId());
            Przedmiot Usuwany2 = em.find(Przedmiot.class, przedmiot.getId());
            logger.info("Usuwam pracownikowi "+pracownik.getImie() +" " +pracownik.getNazwisko() +" przedmiot :" +przedmiot.getNazwa());
            em.remove(Usuwany2);
            logger.info("Usunalem pracownikowi "+pracownik.getImie() +" " +pracownik.getNazwisko() +" przedmiot :" +przedmiot.getNazwa());
        }    
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }       
    }
    
    public void addNewZast(String nazwaNewZ)
    {
        try
        {
            Zastosowanie dod = new Zastosowanie(Integer.valueOf(1), nazwaNewZ);
            logger.info("Dodaje zastosowanie: "+ nazwaNewZ);
            em.persist(dod);
            logger.info("Dodano zastosowanie: "+ nazwaNewZ);
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
    }
    
 public List<Zastosowanie> getZast(){

    List<Zastosowanie> zast = null;
    try{
    zast = (List<Zastosowanie>) em.createNamedQuery("Zastosowanie.findAll").getResultList(); 
    logger.info("Odczytano zastosowania");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return zast;
}
    
public void deleteZastosowanie(Zastosowanie zastosowanie)
{
        try
        {
            logger.info("Usuwam zastosowanie: "+zastosowanie.getNazwa());
            Zastosowanie Usuwane = em.find(Zastosowanie.class, zastosowanie.getId());
            em.remove(Usuwane);
            logger.info("Usunieto zastosowanie: "+zastosowanie.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public List<Przedmiot> getPrzedmiotyZWlasc(){

    List<Przedmiot> przedmioty = null;
    try{
    przedmioty = (List<Przedmiot>) em.createNamedQuery("Przedmiot.findAllZWlasc").getResultList(); 
    logger.info("Odczytano przedmioty z wlascicielem");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return przedmioty;
}

public Przedmiot getPrzedmiotById(int id) {
        Przedmiot przedmiot = null;
        try {
            przedmiot = (Przedmiot) em.createNamedQuery("Przedmiot.findById")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        return przedmiot;
    }

public void addZastPrzedm(Zastosowanie zast, Przedmiot przedm)
{
    
    try
        {
            logger.info("id przedm1: "+przedm.getId());
            logger.info("id zast11: "+zast.getId());
            przedm = em.getReference(Przedmiot.class, przedm.getId());
            zast = em.getReference(Zastosowanie.class, zast.getId());
            logger.info("Dodaje przedmiotowi "+przedm.getNazwa() +" zastosowanie :" +zast.getNazwa());
            //Pracownik Znaleziony = em.find(Pracownik.class, pracownik.getId());
            //Przedmiot Znaleziony2 = em.find(Przedmiot.class, przedmiot.getId());
            logger.info("id przedm: "+przedm.getId());
            logger.info("id zast1: "+zast.getId());
            if(!zast.getPrzedmiotList().contains(przedm) && !przedm.getZastosowanieList().contains(zast)){
            
                przedm.getZastosowanieList().add(zast);
                zast.getPrzedmiotList().add(przedm);
            }
            //przedmiot.setIdPracownik(pracownik);
            //pracownik.getPrzedmiotCollection().add(przedmiot);
            logger.info("Dodaje przedmiotowi "+przedm.getNazwa() +" zastosowanie :" +zast.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
} 

public List<Zastosowanie> getZastWolne(int przedmiotID){

    List<Zastosowanie> wolne = null;
    
    try{
      Query q = em.createNativeQuery("SELECT z.* FROM Zastosowanie z where z.id not in "
                + "(SELECT pz.id_zastosowanie from przedmiot_zastosowanie pz where pz.id_przedmiot=?1)", Zastosowanie.class);
      q.setParameter(1,przedmiotID);
      wolne = (List<Zastosowanie>)q.getResultList();
      logger.info("Odczytano wolne zastosowania");
      
    }
    catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
    return wolne;
}

public void deleteZastPrzedmPrac(Zastosowanie zast, Przedmiot przedm){

    try
        {
            logger.info("Usuwam zastosowanie: "+zast.getNazwa()+"z przedmiotu: "+przedm.getNazwa());
            Zastosowanie Usuwane = em.find(Zastosowanie.class, zast.getId());
            Przedmiot doUsun = em.find(Przedmiot.class, przedm.getId());
            Usuwane.getPrzedmiotList().remove(doUsun);
            doUsun.getZastosowanieList().remove(Usuwane);
            logger.info("Usunieto zastosowanie: "+zast.getNazwa()+"z przedmiotu: "+przedm.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
    
}

//public List<Zastosowanie> getZastPrzedm(int przedmiotID){
//    List<Zastosowanie> przedm = null;
//    try{
//      Query q = em.createNativeQuery("SELECT z.* FROM Zastosowanie z where z.id in "
//                + "(SELECT pz.id_zastosowanie from przedmiot_zastosowanie pz where pz.id_przedmiot=?1)", Zastosowanie.class);
//      q.setParameter(1,przedmiotID);
//      przedm = (List<Zastosowanie>)q.getResultList();
//      
//    }
//    catch(Exception e)
//        {
//            throw new EJBException(e.getMessage());
//        }
//    return przedm;
//}

public List<Zastosowanie> getZastPrzedm(int id) {
        List <Zastosowanie> zastosowania = null;
        Przedmiot przedmiot = null;
        try {
            przedmiot = em.find(Przedmiot.class, id);
            zastosowania = przedmiot.getZastosowanieList();
            logger.info("Odczytano zastosowania przedmiotu");
        } catch(Exception e) {
            throw new EJBException(e.getMessage());   
        }
        
        return zastosowania;
    }

public Zastosowanie getZastById(int id) {
        Zastosowanie zastosowanie = null;
        try {
            zastosowanie = (Zastosowanie) em.createNamedQuery("Zastosowanie.findById")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        return zastosowanie;
    }

//public void addZastPrzedm(Zastosowanie zast, Przedmiot przedm)
//{
}
