/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Prog.Aprendiz2
 */
@Entity
@Table(name = "pruebas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pruebas.findAll", query = "SELECT p FROM Pruebas p")
    , @NamedQuery(name = "Pruebas.findByIdPruebas", query = "SELECT p FROM Pruebas p WHERE p.idPruebas = :idPruebas")
    , @NamedQuery(name = "Pruebas.findByNombre", query = "SELECT p FROM Pruebas p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Pruebas.findByEstado", query = "SELECT p FROM Pruebas p WHERE p.estado = :estado")})
public class Pruebas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pruebas")
    private Integer idPruebas;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Integer estado;

    public Pruebas() {
    }

    public Pruebas(Integer idPruebas) {
        this.idPruebas = idPruebas;
    }

    public Integer getIdPruebas() {
        return idPruebas;
    }

    public void setIdPruebas(Integer idPruebas) {
        this.idPruebas = idPruebas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPruebas != null ? idPruebas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pruebas)) {
            return false;
        }
        Pruebas other = (Pruebas) object;
        if ((this.idPruebas == null && other.idPruebas != null) || (this.idPruebas != null && !this.idPruebas.equals(other.idPruebas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Pruebas[ idPruebas=" + idPruebas + " ]";
    }
    
}
