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
@Table(name = "ensambles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ensambles.findAll", query = "SELECT e FROM Ensambles e")
    , @NamedQuery(name = "Ensambles.findByIdEnsambles", query = "SELECT e FROM Ensambles e WHERE e.idEnsambles = :idEnsambles")
    , @NamedQuery(name = "Ensambles.findByConsecutivo", query = "SELECT e FROM Ensambles e WHERE e.consecutivo = :consecutivo")
    , @NamedQuery(name = "Ensambles.findByNombre", query = "SELECT e FROM Ensambles e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Ensambles.findByFecha", query = "SELECT e FROM Ensambles e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "Ensambles.findByContramuestras", query = "SELECT e FROM Ensambles e WHERE e.contramuestras = :contramuestras")
    , @NamedQuery(name = "Ensambles.findByFechaRegistro", query = "SELECT e FROM Ensambles e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Ensambles.findByUsuarioRegistro", query = "SELECT e FROM Ensambles e WHERE e.usuarioRegistro = :usuarioRegistro")})
public class Ensambles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ensambles")
    private Integer idEnsambles;
    @Column(name = "consecutivo")
    private String consecutivo;
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Column(name = "lotes_inyeccion")
    private String lotesInyeccion;
    @Lob
    @Column(name = "lotes_materiaprima")
    private String lotesMateriaprima;
    @Lob
    @Column(name = "lotes_importados")
    private String lotesImportados;
    @Lob
    @Column(name = "lote_ensamble")
    private String loteEnsamble;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "contramuestras")
    private Integer contramuestras;
    @Lob
    @Column(name = "prestamo")
    private String prestamo;
    @Lob
    @Column(name = "responsable_recibido")
    private String responsableRecibido;
    @Lob
    @Column(name = "estado")
    private String estado;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @Lob
    @Column(name = "Justificacion_no_recibido")
    private String justificacionnorecibido;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @JoinColumn(name = "proceso", referencedColumnName = "id_procesos")
    @ManyToOne(optional = false)
    private Procesos proceso;

    public Ensambles() {
    }

    public Ensambles(Integer idEnsambles) {
        this.idEnsambles = idEnsambles;
    }

    public Integer getIdEnsambles() {
        return idEnsambles;
    }

    public void setIdEnsambles(Integer idEnsambles) {
        this.idEnsambles = idEnsambles;
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

    public String getLotesInyeccion() {
        return lotesInyeccion;
    }

    public void setLotesInyeccion(String lotesInyeccion) {
        this.lotesInyeccion = lotesInyeccion;
    }

    public String getLotesMateriaprima() {
        return lotesMateriaprima;
    }

    public void setLotesMateriaprima(String lotesMateriaprima) {
        this.lotesMateriaprima = lotesMateriaprima;
    }

    public String getLotesImportados() {
        return lotesImportados;
    }

    public void setLotesImportados(String lotesImportados) {
        this.lotesImportados = lotesImportados;
    }

    public String getLoteEnsamble() {
        return loteEnsamble;
    }

    public void setLoteEnsamble(String loteEnsamble) {
        this.loteEnsamble = loteEnsamble;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getContramuestras() {
        return contramuestras;
    }

    public void setContramuestras(Integer contramuestras) {
        this.contramuestras = contramuestras;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getJustificacionnorecibido() {
        return justificacionnorecibido;
    }

    public void setJustificacionnorecibido(String justificacionnorecibido) {
        this.justificacionnorecibido = justificacionnorecibido;
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

    public Procesos getProceso() {
        return proceso;
    }

    public void setProceso(Procesos proceso) {
        this.proceso = proceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnsambles != null ? idEnsambles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ensambles)) {
            return false;
        }
        Ensambles other = (Ensambles) object;
        if ((this.idEnsambles == null && other.idEnsambles != null) || (this.idEnsambles != null && !this.idEnsambles.equals(other.idEnsambles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Ensambles[ idEnsambles=" + idEnsambles + " ]";
    }
    
}
