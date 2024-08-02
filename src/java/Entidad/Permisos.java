/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Prog.Aprendiz2
 */
@Entity
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p")
    , @NamedQuery(name = "Permisos.findByIdPermisos", query = "SELECT p FROM Permisos p WHERE p.idPermisos = :idPermisos")
    , @NamedQuery(name = "Permisos.findByModulo", query = "SELECT p FROM Permisos p WHERE p.modulo = :modulo")
    , @NamedQuery(name = "Permisos.findByOpcion", query = "SELECT p FROM Permisos p WHERE p.opcion = :opcion")
    , @NamedQuery(name = "Permisos.findByDescripcion", query = "SELECT p FROM Permisos p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Permisos.findByEstado", query = "SELECT p FROM Permisos p WHERE p.estado = :estado")
    , @NamedQuery(name = "Permisos.findByUsuarioRegistro", query = "SELECT p FROM Permisos p WHERE p.usuarioRegistro = :usuarioRegistro")
    , @NamedQuery(name = "Permisos.findByFechaRegistro", query = "SELECT p FROM Permisos p WHERE p.fechaRegistro = :fechaRegistro")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_permisos")
    private Integer idPermisos;
    @Column(name = "modulo")
    private String modulo;
    @Column(name = "opcion")
    private String opcion;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    public Permisos() {
    }

    public Permisos(Integer idPermisos) {
        this.idPermisos = idPermisos;
    }

    public Integer getIdPermisos() {
        return idPermisos;
    }

    public void setIdPermisos(Integer idPermisos) {
        this.idPermisos = idPermisos;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermisos != null ? idPermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idPermisos == null && other.idPermisos != null) || (this.idPermisos != null && !this.idPermisos.equals(other.idPermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Permisos[ idPermisos=" + idPermisos + " ]";
    }
    
}
