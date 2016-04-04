/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author serq9_000
 */
@Entity
@Table(name = "PRZEDMIOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Przedmiot.findAll", query = "SELECT p FROM Przedmiot p"),
    @NamedQuery(name = "Przedmiot.findById", query = "SELECT p FROM Przedmiot p WHERE p.id = :id"),
    @NamedQuery(name = "Przedmiot.findByNazwa", query = "SELECT p FROM Przedmiot p WHERE p.nazwa = :nazwa"),
    @NamedQuery(name = "Przedmiot.findAllBezWlasc", query = "SELECT p FROM Przedmiot p WHERE p.idPracownik IS NULL"),
    @NamedQuery(name = "Przedmiot.findAllZWlasc", query = "SELECT p FROM Przedmiot p WHERE p.idPracownik IS NOT NULL")})
public class Przedmiot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAZWA")
    private String nazwa;
    @ManyToMany(mappedBy = "przedmiotList")
    private List<Zastosowanie> zastosowanieList;
    @JoinColumn(name = "ID_PRACOWNIK", referencedColumnName = "ID")
    @ManyToOne
    private Pracownik idPracownik;

    public Przedmiot() {
    }

    public Przedmiot(Integer id) {
        this.id = id;
    }

    public Przedmiot(Integer id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public List<Zastosowanie> getZastosowanieList() {
        return zastosowanieList;
    }

    public void setZastosowanieList(List<Zastosowanie> zastosowanieList) {
        this.zastosowanieList = zastosowanieList;
    }

    public Pracownik getIdPracownik() {
        return idPracownik;
    }

    public void setIdPracownik(Pracownik idPracownik) {
        this.idPracownik = idPracownik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Przedmiot)) {
            return false;
        }
        Przedmiot other = (Przedmiot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Przedmiot[ id=" + id + " ]";
    }
    
}
