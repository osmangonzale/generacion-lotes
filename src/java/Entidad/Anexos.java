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
@Table(name = "anexos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anexos.findAll", query = "SELECT a FROM Anexos a")
    , @NamedQuery(name = "Anexos.findByIdAnexos", query = "SELECT a FROM Anexos a WHERE a.idAnexos = :idAnexos")
    , @NamedQuery(name = "Anexos.findByIdRegistro", query = "SELECT a FROM Anexos a WHERE a.idRegistro = :idRegistro")
    , @NamedQuery(name = "Anexos.findByRegistro", query = "SELECT a FROM Anexos a WHERE a.registro = :registro")
    , @NamedQuery(name = "Anexos.findByDescripcion", query = "SELECT a FROM Anexos a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Anexos.findByFechaRegistro", query = "SELECT a FROM Anexos a WHERE a.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Anexos.findByUsuarioRegistro", query = "SELECT a FROM Anexos a WHERE a.usuarioRegistro = :usuarioRegistro")
    , @NamedQuery(name = "Anexos.findByDownload", query = "SELECT a FROM Anexos a WHERE a.download = :download")})
public class Anexos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_anexos")
    private Integer idAnexos;
    @Column(name = "id_registro")
    private Integer idRegistro;
    @Column(name = "registro")
    private String registro;
    @Lob
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Column(name = "download")
    private Integer download;

    public Anexos() {
    }

    public Anexos(Integer idAnexos) {
        this.idAnexos = idAnexos;
    }

    public Integer getIdAnexos() {
        return idAnexos;
    }

    public void setIdAnexos(Integer idAnexos) {
        this.idAnexos = idAnexos;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnexos != null ? idAnexos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anexos)) {
            return false;
        }
        Anexos other = (Anexos) object;
        if ((this.idAnexos == null && other.idAnexos != null) || (this.idAnexos != null && !this.idAnexos.equals(other.idAnexos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Anexos[ idAnexos=" + idAnexos + " ]";
    }
    
}
