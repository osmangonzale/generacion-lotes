/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Prog.Aprendiz2
 */
@Entity
@Table(name = "procesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procesos.findAll", query = "SELECT p FROM Procesos p")
    , @NamedQuery(name = "Procesos.findByIdProcesos", query = "SELECT p FROM Procesos p WHERE p.idProcesos = :idProcesos")
    , @NamedQuery(name = "Procesos.findByNombre", query = "SELECT p FROM Procesos p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Procesos.findBySigla", query = "SELECT p FROM Procesos p WHERE p.sigla = :sigla")
    , @NamedQuery(name = "Procesos.findByEstado", query = "SELECT p FROM Procesos p WHERE p.estado = :estado")
    , @NamedQuery(name = "Procesos.findByActual", query = "SELECT p FROM Procesos p WHERE p.actual = :actual")
    , @NamedQuery(name = "Procesos.findByFechaRegistro", query = "SELECT p FROM Procesos p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Procesos.findByUsuarioRegistro", query = "SELECT p FROM Procesos p WHERE p.usuarioRegistro = :usuarioRegistro")
    , @NamedQuery(name = "Procesos.findByTipoProceso", query = "SELECT p FROM Procesos p WHERE p.tipoProceso = :tipoProceso")})
public class Procesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_procesos")
    private Integer idProcesos;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "sigla")
    private String sigla;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "actual")
    private String actual;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Column(name = "tipo_proceso")
    private String tipoProceso;
    @OneToMany(mappedBy = "proceso")
    private Collection<Inyeccion> inyeccionCollection;
    @OneToMany(mappedBy = "idProceso")
    private Collection<ControlConsecutivos> controlConsecutivosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proceso")
    private Collection<Ensambles> ensamblesCollection;

    public Procesos() {
    }

    public Procesos(Integer idProcesos) {
        this.idProcesos = idProcesos;
    }

    public Integer getIdProcesos() {
        return idProcesos;
    }

    public void setIdProcesos(Integer idProcesos) {
        this.idProcesos = idProcesos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
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

    public String getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    @XmlTransient
    public Collection<Inyeccion> getInyeccionCollection() {
        return inyeccionCollection;
    }

    public void setInyeccionCollection(Collection<Inyeccion> inyeccionCollection) {
        this.inyeccionCollection = inyeccionCollection;
    }

    @XmlTransient
    public Collection<ControlConsecutivos> getControlConsecutivosCollection() {
        return controlConsecutivosCollection;
    }

    public void setControlConsecutivosCollection(Collection<ControlConsecutivos> controlConsecutivosCollection) {
        this.controlConsecutivosCollection = controlConsecutivosCollection;
    }

    @XmlTransient
    public Collection<Ensambles> getEnsamblesCollection() {
        return ensamblesCollection;
    }

    public void setEnsamblesCollection(Collection<Ensambles> ensamblesCollection) {
        this.ensamblesCollection = ensamblesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcesos != null ? idProcesos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesos)) {
            return false;
        }
        Procesos other = (Procesos) object;
        if ((this.idProcesos == null && other.idProcesos != null) || (this.idProcesos != null && !this.idProcesos.equals(other.idProcesos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Procesos[ idProcesos=" + idProcesos + " ]";
    }
    
}
