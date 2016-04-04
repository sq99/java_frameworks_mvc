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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author serq9_000
 */
@Entity
@Table(name = "ZASTOSOWANIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zastosowanie.findAll", query = "SELECT z FROM Zastosowanie z"),
    @NamedQuery(name = "Zastosowanie.findById", query = "SELECT z FROM Zastosowanie z WHERE z.id = :id"),
    @NamedQuery(name = "Zastosowanie.findByNazwa", query = "SELECT z FROM Zastosowanie z WHERE z.nazwa = :nazwa")})
public class Zastosowanie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NAZWA")
    private String nazwa;
    @JoinTable(name = "PRZEDMIOT_ZASTOSOWANIE", joinColumns = {
        @JoinColumn(name = "ID_ZASTOSOWANIE", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PRZEDMIOT", referencedColumnName = "ID")})
    @ManyToMany
    private List<Przedmiot> przedmiotList;

    public Zastosowanie() {
    }

    public Zastosowanie(Integer id) {
        this.id = id;
    }
    
    public Zastosowanie(Integer id, String nazwa) {
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
        if (!(object instanceof Zastosowanie)) {
            return false;
        }
        Zastosowanie other = (Zastosowanie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Zastosowanie[ id=" + id + " ]";
    }
    
}
