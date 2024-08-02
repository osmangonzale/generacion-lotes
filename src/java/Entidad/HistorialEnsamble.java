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
@Table(name = "historial_ensamble")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialEnsamble.findAll", query = "SELECT h FROM HistorialEnsamble h")
    , @NamedQuery(name = "HistorialEnsamble.findByIdHistorial", query = "SELECT h FROM HistorialEnsamble h WHERE h.idHistorial = :idHistorial")
    , @NamedQuery(name = "HistorialEnsamble.findByOrigen", query = "SELECT h FROM HistorialEnsamble h WHERE h.origen = :origen")
    , @NamedQuery(name = "HistorialEnsamble.findById", query = "SELECT h FROM HistorialEnsamble h WHERE h.id = :id")
    , @NamedQuery(name = "HistorialEnsamble.findByResponsablePrestamo", query = "SELECT h FROM HistorialEnsamble h WHERE h.responsablePrestamo = :responsablePrestamo")
    , @NamedQuery(name = "HistorialEnsamble.findByFechaPrestamo", query = "SELECT h FROM HistorialEnsamble h WHERE h.fechaPrestamo = :fechaPrestamo")
    , @NamedQuery(name = "HistorialEnsamble.findByResponsableDevolucion", query = "SELECT h FROM HistorialEnsamble h WHERE h.responsableDevolucion = :responsableDevolucion")
    , @NamedQuery(name = "HistorialEnsamble.findByFechaDevolucion", query = "SELECT h FROM HistorialEnsamble h WHERE h.fechaDevolucion = :fechaDevolucion")
    , @NamedQuery(name = "HistorialEnsamble.findByEstado", query = "SELECT h FROM HistorialEnsamble h WHERE h.estado = :estado")})
public class HistorialEnsamble implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial")
    private Integer idHistorial;
    @Column(name = "origen")
    private Integer origen;
    @Column(name = "id")
    private Integer id;
    @Column(name = "responsable_prestamo")
    private String responsablePrestamo;
    @Column(name = "fecha_prestamo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrestamo;
    @Lob
    @Column(name = "descripcion_prestamo")
    private String descripcionPrestamo;
    @Column(name = "responsable_devolucion")
    private String responsableDevolucion;
    @Column(name = "fecha_devolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;
    @Lob
    @Column(name = "descripcion_devolucion")
    private String descripcionDevolucion;
    @Column(name = "estado")
    private Integer estado;

    public HistorialEnsamble() {
    }

    public HistorialEnsamble(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponsablePrestamo() {
        return responsablePrestamo;
    }

    public void setResponsablePrestamo(String responsablePrestamo) {
        this.responsablePrestamo = responsablePrestamo;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getDescripcionPrestamo() {
        return descripcionPrestamo;
    }

    public void setDescripcionPrestamo(String descripcionPrestamo) {
        this.descripcionPrestamo = descripcionPrestamo;
    }

    public String getResponsableDevolucion() {
        return responsableDevolucion;
    }

    public void setResponsableDevolucion(String responsableDevolucion) {
        this.responsableDevolucion = responsableDevolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getDescripcionDevolucion() {
        return descripcionDevolucion;
    }

    public void setDescripcionDevolucion(String descripcionDevolucion) {
        this.descripcionDevolucion = descripcionDevolucion;
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
        hash += (idHistorial != null ? idHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialEnsamble)) {
            return false;
        }
        HistorialEnsamble other = (HistorialEnsamble) object;
        if ((this.idHistorial == null && other.idHistorial != null) || (this.idHistorial != null && !this.idHistorial.equals(other.idHistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.HistorialEnsamble[ idHistorial=" + idHistorial + " ]";
    }
    
}
