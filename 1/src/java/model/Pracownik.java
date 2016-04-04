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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "PRACOWNIK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pracownik.findAll", query = "SELECT p FROM Pracownik p"),
    @NamedQuery(name = "Pracownik.findById", query = "SELECT p FROM Pracownik p WHERE p.id = :id"),
    @NamedQuery(name = "Pracownik.findByImie", query = "SELECT p FROM Pracownik p WHERE p.imie = :imie"),
    @NamedQuery(name = "Pracownik.findByNazwisko", query = "SELECT p FROM Pracownik p WHERE p.nazwisko = :nazwisko"),
    @NamedQuery(name = "Pracownik.findByAll", query = "SELECT p FROM Pracownik p WHERE p.imie = :imie AND p.nazwisko = :nazwisko AND p.id = :id")})
public class Pracownik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IMIE")
    private String imie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAZWISKO")
    private String nazwisko;
    @OneToMany(mappedBy = "idPracownik")
    private List<Przedmiot> przedmiotList;

    public Pracownik() {
    }

    public Pracownik(Integer id) {
        this.id = id;
    }

    public Pracownik(Integer id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @XmlTransient
    public List<Przedmiot> getPrzedmiotList() {
        return przedmiotList;
    }

    public void setPrzedmiotList(List<Przedmiot> przedmiotList) {
        this.przedmiotList = przedmiotList;
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
        if (!(object instanceof Pracownik)) {
            return false;
        }
        Pracownik other = (Pracownik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pracownik[ id=" + id + " ]";
    }
    
}
