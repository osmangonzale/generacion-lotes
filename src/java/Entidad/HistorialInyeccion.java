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
@Table(name = "historial_inyeccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialInyeccion.findAll", query = "SELECT h FROM HistorialInyeccion h")
    , @NamedQuery(name = "HistorialInyeccion.findByIdHistorialIny", query = "SELECT h FROM HistorialInyeccion h WHERE h.idHistorialIny = :idHistorialIny")
    , @NamedQuery(name = "HistorialInyeccion.findByOrigenIny", query = "SELECT h FROM HistorialInyeccion h WHERE h.origenIny = :origenIny")
    , @NamedQuery(name = "HistorialInyeccion.findByIdOIny", query = "SELECT h FROM HistorialInyeccion h WHERE h.idOIny = :idOIny")
    , @NamedQuery(name = "HistorialInyeccion.findByResponsablePrestamoIny", query = "SELECT h FROM HistorialInyeccion h WHERE h.responsablePrestamoIny = :responsablePrestamoIny")
    , @NamedQuery(name = "HistorialInyeccion.findByFechaPrestamoIny", query = "SELECT h FROM HistorialInyeccion h WHERE h.fechaPrestamoIny = :fechaPrestamoIny")
    , @NamedQuery(name = "HistorialInyeccion.findByResponsableDevolucionIny", query = "SELECT h FROM HistorialInyeccion h WHERE h.responsableDevolucionIny = :responsableDevolucionIny")
    , @NamedQuery(name = "HistorialInyeccion.findByFechaDevolucionIny", query = "SELECT h FROM HistorialInyeccion h WHERE h.fechaDevolucionIny = :fechaDevolucionIny")
    , @NamedQuery(name = "HistorialInyeccion.findByEstadoIny", query = "SELECT h FROM HistorialInyeccion h WHERE h.estadoIny = :estadoIny")})
public class HistorialInyeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial_iny")
    private Integer idHistorialIny;
    @Column(name = "origen_iny")
    private Integer origenIny;
    @Column(name = "id_o_iny")
    private Integer idOIny;
    @Column(name = "responsable_prestamo_iny")
    private String responsablePrestamoIny;
    @Column(name = "fecha_prestamo_iny")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrestamoIny;
    @Lob
    @Column(name = "descripcion_prestamo_iny")
    private String descripcionPrestamoIny;
    @Column(name = "responsable_devolucion_iny")
    private String responsableDevolucionIny;
    @Column(name = "fecha_devolucion_iny")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucionIny;
    @Lob
    @Column(name = "descripcion_devolucion_iny")
    private String descripcionDevolucionIny;
    @Column(name = "estado_iny")
    private Integer estadoIny;

    public HistorialInyeccion() {
    }

    public HistorialInyeccion(Integer idHistorialIny) {
        this.idHistorialIny = idHistorialIny;
    }

    public Integer getIdHistorialIny() {
        return idHistorialIny;
    }

    public void setIdHistorialIny(Integer idHistorialIny) {
        this.idHistorialIny = idHistorialIny;
    }

    public Integer getOrigenIny() {
        return origenIny;
    }

    public void setOrigenIny(Integer origenIny) {
        this.origenIny = origenIny;
    }

    public Integer getIdOIny() {
        return idOIny;
    }

    public void setIdOIny(Integer idOIny) {
        this.idOIny = idOIny;
    }

    public String getResponsablePrestamoIny() {
        return responsablePrestamoIny;
    }

    public void setResponsablePrestamoIny(String responsablePrestamoIny) {
        this.responsablePrestamoIny = responsablePrestamoIny;
    }

    public Date getFechaPrestamoIny() {
        return fechaPrestamoIny;
    }

    public void setFechaPrestamoIny(Date fechaPrestamoIny) {
        this.fechaPrestamoIny = fechaPrestamoIny;
    }

    public String getDescripcionPrestamoIny() {
        return descripcionPrestamoIny;
    }

    public void setDescripcionPrestamoIny(String descripcionPrestamoIny) {
        this.descripcionPrestamoIny = descripcionPrestamoIny;
    }

    public String getResponsableDevolucionIny() {
        return responsableDevolucionIny;
    }

    public void setResponsableDevolucionIny(String responsableDevolucionIny) {
        this.responsableDevolucionIny = responsableDevolucionIny;
    }

    public Date getFechaDevolucionIny() {
        return fechaDevolucionIny;
    }

    public void setFechaDevolucionIny(Date fechaDevolucionIny) {
        this.fechaDevolucionIny = fechaDevolucionIny;
    }

    public String getDescripcionDevolucionIny() {
        return descripcionDevolucionIny;
    }

    public void setDescripcionDevolucionIny(String descripcionDevolucionIny) {
        this.descripcionDevolucionIny = descripcionDevolucionIny;
    }

    public Integer getEstadoIny() {
        return estadoIny;
    }

    public void setEstadoIny(Integer estadoIny) {
        this.estadoIny = estadoIny;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialIny != null ? idHistorialIny.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialInyeccion)) {
            return false;
        }
        HistorialInyeccion other = (HistorialInyeccion) object;
        if ((this.idHistorialIny == null && other.idHistorialIny != null) || (this.idHistorialIny != null && !this.idHistorialIny.equals(other.idHistorialIny))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.HistorialInyeccion[ idHistorialIny=" + idHistorialIny + " ]";
    }
    
}
