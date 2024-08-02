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
@Table(name = "control_consecutivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlConsecutivos.findAll", query = "SELECT c FROM ControlConsecutivos c")
    , @NamedQuery(name = "ControlConsecutivos.findByIdControlConsecutivos", query = "SELECT c FROM ControlConsecutivos c WHERE c.idControlConsecutivos = :idControlConsecutivos")
    , @NamedQuery(name = "ControlConsecutivos.findByConsecutivo", query = "SELECT c FROM ControlConsecutivos c WHERE c.consecutivo = :consecutivo")
    , @NamedQuery(name = "ControlConsecutivos.findByNombre", query = "SELECT c FROM ControlConsecutivos c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "ControlConsecutivos.findByLote", query = "SELECT c FROM ControlConsecutivos c WHERE c.lote = :lote")
    , @NamedQuery(name = "ControlConsecutivos.findByFecha", query = "SELECT c FROM ControlConsecutivos c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "ControlConsecutivos.findByAplica", query = "SELECT c FROM ControlConsecutivos c WHERE c.aplica = :aplica")
    , @NamedQuery(name = "ControlConsecutivos.findByEntrega", query = "SELECT c FROM ControlConsecutivos c WHERE c.entrega = :entrega")
    , @NamedQuery(name = "ControlConsecutivos.findByEstado", query = "SELECT c FROM ControlConsecutivos c WHERE c.estado = :estado")
    , @NamedQuery(name = "ControlConsecutivos.findByFechaRegistro", query = "SELECT c FROM ControlConsecutivos c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ControlConsecutivos.findByUsuarioRegistro", query = "SELECT c FROM ControlConsecutivos c WHERE c.usuarioRegistro = :usuarioRegistro")
    , @NamedQuery(name = "ControlConsecutivos.findByUltimoActualizado", query = "SELECT c FROM ControlConsecutivos c WHERE c.ultimoActualizado = :ultimoActualizado")
    , @NamedQuery(name = "ControlConsecutivos.findByPrueba", query = "SELECT c FROM ControlConsecutivos c WHERE c.prueba = :prueba")})
public class ControlConsecutivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_control_consecutivos")
    private Integer idControlConsecutivos;
    @Column(name = "consecutivo")
    private String consecutivo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "lote")
    private String lote;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "aplica")
    private Integer aplica;
    @Column(name = "entrega")
    private Integer entrega;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Column(name = "ultimo_actualizado")
    private String ultimoActualizado;
    @Column(name = "prueba")
    private String prueba;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_procesos")
    @ManyToOne
    private Procesos idProceso;

    public ControlConsecutivos() {
    }

    public ControlConsecutivos(Integer idControlConsecutivos) {
        this.idControlConsecutivos = idControlConsecutivos;
    }

    public Integer getIdControlConsecutivos() {
        return idControlConsecutivos;
    }

    public void setIdControlConsecutivos(Integer idControlConsecutivos) {
        this.idControlConsecutivos = idControlConsecutivos;
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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getAplica() {
        return aplica;
    }

    public void setAplica(Integer aplica) {
        this.aplica = aplica;
    }

    public Integer getEntrega() {
        return entrega;
    }

    public void setEntrega(Integer entrega) {
        this.entrega = entrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public String getUltimoActualizado() {
        return ultimoActualizado;
    }

    public void setUltimoActualizado(String ultimoActualizado) {
        this.ultimoActualizado = ultimoActualizado;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public Procesos getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Procesos idProceso) {
        this.idProceso = idProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControlConsecutivos != null ? idControlConsecutivos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlConsecutivos)) {
            return false;
        }
        ControlConsecutivos other = (ControlConsecutivos) object;
        if ((this.idControlConsecutivos == null && other.idControlConsecutivos != null) || (this.idControlConsecutivos != null && !this.idControlConsecutivos.equals(other.idControlConsecutivos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.ControlConsecutivos[ idControlConsecutivos=" + idControlConsecutivos + " ]";
    }
    
}
