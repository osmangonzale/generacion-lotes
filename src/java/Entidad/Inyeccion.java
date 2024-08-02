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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "inyeccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inyeccion.findAll", query = "SELECT i FROM Inyeccion i")
    , @NamedQuery(name = "Inyeccion.findByIdControlInyeccion", query = "SELECT i FROM Inyeccion i WHERE i.idControlInyeccion = :idControlInyeccion")
    , @NamedQuery(name = "Inyeccion.findByConsecutivo", query = "SELECT i FROM Inyeccion i WHERE i.consecutivo = :consecutivo")
    , @NamedQuery(name = "Inyeccion.findByNombre", query = "SELECT i FROM Inyeccion i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Inyeccion.findByLoteC", query = "SELECT i FROM Inyeccion i WHERE i.loteC = :loteC")
    , @NamedQuery(name = "Inyeccion.findByLoteP", query = "SELECT i FROM Inyeccion i WHERE i.loteP = :loteP")
    , @NamedQuery(name = "Inyeccion.findByLotePrincipal", query = "SELECT i FROM Inyeccion i WHERE i.lotePrincipal = :lotePrincipal")
    , @NamedQuery(name = "Inyeccion.findByFecha", query = "SELECT i FROM Inyeccion i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "Inyeccion.findByMolde", query = "SELECT i FROM Inyeccion i WHERE i.molde = :molde")
    , @NamedQuery(name = "Inyeccion.findByContramuestras", query = "SELECT i FROM Inyeccion i WHERE i.contramuestras = :contramuestras")
    , @NamedQuery(name = "Inyeccion.findByResponsable", query = "SELECT i FROM Inyeccion i WHERE i.responsable = :responsable")
    , @NamedQuery(name = "Inyeccion.findByEstado", query = "SELECT i FROM Inyeccion i WHERE i.estado = :estado")
    , @NamedQuery(name = "Inyeccion.findByFechaRegistro", query = "SELECT i FROM Inyeccion i WHERE i.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Inyeccion.findByUsuarioRegistro", query = "SELECT i FROM Inyeccion i WHERE i.usuarioRegistro = :usuarioRegistro")})
public class Inyeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_control_inyeccion")
    private Integer idControlInyeccion;
    @Column(name = "consecutivo")
    private String consecutivo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "lote_c")
    private String loteC;
    @Column(name = "lote_p")
    private String loteP;
    @Column(name = "lote_principal")
    private String lotePrincipal;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "molde")
    private Integer molde;
    @Column(name = "contramuestras")
    private Integer contramuestras;
    @Column(name = "responsable")
    private String responsable;
    @Lob
    @Column(name = "prestamo")
    private String prestamo;
    @Lob
    @Column(name = "responsable_recibido")
    private String responsableRecibido;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Lob
    @Column(name = "Justificacion_no_recibido")
    private String justificacionnorecibido;
    @JoinColumn(name = "proceso", referencedColumnName = "id_procesos")
    @ManyToOne
    private Procesos proceso;

    public Inyeccion() {
    }

    public Inyeccion(Integer idControlInyeccion) {
        this.idControlInyeccion = idControlInyeccion;
    }

    public Integer getIdControlInyeccion() {
        return idControlInyeccion;
    }

    public void setIdControlInyeccion(Integer idControlInyeccion) {
        this.idControlInyeccion = idControlInyeccion;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLoteC() {
        return loteC;
    }

    public void setLoteC(String loteC) {
        this.loteC = loteC;
    }

    public String getLoteP() {
        return loteP;
    }

    public void setLoteP(String loteP) {
        this.loteP = loteP;
    }

    public String getLotePrincipal() {
        return lotePrincipal;
    }

    public void setLotePrincipal(String lotePrincipal) {
        this.lotePrincipal = lotePrincipal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getMolde() {
        return molde;
    }

    public void setMolde(Integer molde) {
        this.molde = molde;
    }

    public Integer getContramuestras() {
        return contramuestras;
    }

    public void setContramuestras(Integer contramuestras) {
        this.contramuestras = contramuestras;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }

    public String getResponsableRecibido() {
        return responsableRecibido;
    }

    public void setResponsableRecibido(String responsableRecibido) {
        this.responsableRecibido = responsableRecibido;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getJustificacionnorecibido() {
        return justificacionnorecibido;
    }

    public void setJustificacionnorecibido(String justificacionnorecibido) {
        this.justificacionnorecibido = justificacionnorecibido;
    }

    public Procesos getProceso() {
        return proceso;
    }

    public void setProceso(Procesos proceso) {
        this.proceso = proceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControlInyeccion != null ? idControlInyeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inyeccion)) {
            return false;
        }
        Inyeccion other = (Inyeccion) object;
        if ((this.idControlInyeccion == null && other.idControlInyeccion != null) || (this.idControlInyeccion != null && !this.idControlInyeccion.equals(other.idControlInyeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Inyeccion[ idControlInyeccion=" + idControlInyeccion + " ]";
    }
    
}
