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
import javax.persistence.Lob;
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
@Table(name = "verificacion_recepcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VerificacionRecepcion.findAll", query = "SELECT v FROM VerificacionRecepcion v")
    , @NamedQuery(name = "VerificacionRecepcion.findByIdVerificacionRecepcion", query = "SELECT v FROM VerificacionRecepcion v WHERE v.idVerificacionRecepcion = :idVerificacionRecepcion")
    , @NamedQuery(name = "VerificacionRecepcion.findByObservacion", query = "SELECT v FROM VerificacionRecepcion v WHERE v.observacion = :observacion")
    , @NamedQuery(name = "VerificacionRecepcion.findByTendencia", query = "SELECT v FROM VerificacionRecepcion v WHERE v.tendencia = :tendencia")
    , @NamedQuery(name = "VerificacionRecepcion.findByEstadoVerificacionRecepcion", query = "SELECT v FROM VerificacionRecepcion v WHERE v.estadoVerificacionRecepcion = :estadoVerificacionRecepcion")
    , @NamedQuery(name = "VerificacionRecepcion.findByUsuarioRegistro", query = "SELECT v FROM VerificacionRecepcion v WHERE v.usuarioRegistro = :usuarioRegistro")
    , @NamedQuery(name = "VerificacionRecepcion.findByFechaRegistro", query = "SELECT v FROM VerificacionRecepcion v WHERE v.fechaRegistro = :fechaRegistro")})
public class VerificacionRecepcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_verificacion_recepcion")
    private Integer idVerificacionRecepcion;
    @Basic(optional = false)
    @Lob
    @Column(name = "enunciado")
    private String enunciado;
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "tendencia")
    private int tendencia;
    @Column(name = "estado_verificacion_recepcion")
    private Integer estadoVerificacionRecepcion;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    public VerificacionRecepcion() {
    }

    public VerificacionRecepcion(Integer idVerificacionRecepcion) {
        this.idVerificacionRecepcion = idVerificacionRecepcion;
    }

    public VerificacionRecepcion(Integer idVerificacionRecepcion, String enunciado, int tendencia, Date fechaRegistro) {
        this.idVerificacionRecepcion = idVerificacionRecepcion;
        this.enunciado = enunciado;
        this.tendencia = tendencia;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdVerificacionRecepcion() {
        return idVerificacionRecepcion;
    }

    public void setIdVerificacionRecepcion(Integer idVerificacionRecepcion) {
        this.idVerificacionRecepcion = idVerificacionRecepcion;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getTendencia() {
        return tendencia;
    }

    public void setTendencia(int tendencia) {
        this.tendencia = tendencia;
    }

    public Integer getEstadoVerificacionRecepcion() {
        return estadoVerificacionRecepcion;
    }

    public void setEstadoVerificacionRecepcion(Integer estadoVerificacionRecepcion) {
        this.estadoVerificacionRecepcion = estadoVerificacionRecepcion;
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
        hash += (idVerificacionRecepcion != null ? idVerificacionRecepcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VerificacionRecepcion)) {
            return false;
        }
        VerificacionRecepcion other = (VerificacionRecepcion) object;
        if ((this.idVerificacionRecepcion == null && other.idVerificacionRecepcion != null) || (this.idVerificacionRecepcion != null && !this.idVerificacionRecepcion.equals(other.idVerificacionRecepcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.VerificacionRecepcion[ idVerificacionRecepcion=" + idVerificacionRecepcion + " ]";
    }
    
}
