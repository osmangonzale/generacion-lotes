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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor")
    , @NamedQuery(name = "Proveedor.findByCodigoRef", query = "SELECT p FROM Proveedor p WHERE p.codigoRef = :codigoRef")
    , @NamedQuery(name = "Proveedor.findByProveedor", query = "SELECT p FROM Proveedor p WHERE p.proveedor = :proveedor")
    , @NamedQuery(name = "Proveedor.findByNombreRef", query = "SELECT p FROM Proveedor p WHERE p.nombreRef = :nombreRef")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Column(name = "codigo_ref")
    private String codigoRef;
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "nombre_ref")
    private String nombreRef;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getCodigoRef() {
        return codigoRef;
    }

    public void setCodigoRef(String codigoRef) {
        this.codigoRef = codigoRef;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNombreRef() {
        return nombreRef;
    }

    public void setNombreRef(String nombreRef) {
        this.nombreRef = nombreRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Proveedor[ idProveedor=" + idProveedor + " ]";
    }
    
}
