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
@Table(name = "recepcion_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecepcionMaterial.findAll", query = "SELECT r FROM RecepcionMaterial r")
    , @NamedQuery(name = "RecepcionMaterial.findByIdRecepcionMaterial", query = "SELECT r FROM RecepcionMaterial r WHERE r.idRecepcionMaterial = :idRecepcionMaterial")
    , @NamedQuery(name = "RecepcionMaterial.findByClasificacion", query = "SELECT r FROM RecepcionMaterial r WHERE r.clasificacion = :clasificacion")
    , @NamedQuery(name = "RecepcionMaterial.findByFechaRecepcion", query = "SELECT r FROM RecepcionMaterial r WHERE r.fechaRecepcion = :fechaRecepcion")
    , @NamedQuery(name = "RecepcionMaterial.findByHoraLlegada", query = "SELECT r FROM RecepcionMaterial r WHERE r.horaLlegada = :horaLlegada")
    , @NamedQuery(name = "RecepcionMaterial.findByInicioDescargue", query = "SELECT r FROM RecepcionMaterial r WHERE r.inicioDescargue = :inicioDescargue")
    , @NamedQuery(name = "RecepcionMaterial.findByTerminoDescargue", query = "SELECT r FROM RecepcionMaterial r WHERE r.terminoDescargue = :terminoDescargue")
    , @NamedQuery(name = "RecepcionMaterial.findByCodigo", query = "SELECT r FROM RecepcionMaterial r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "RecepcionMaterial.findByReferencia", query = "SELECT r FROM RecepcionMaterial r WHERE r.referencia = :referencia")
    , @NamedQuery(name = "RecepcionMaterial.findByLote", query = "SELECT r FROM RecepcionMaterial r WHERE r.lote = :lote")
    , @NamedQuery(name = "RecepcionMaterial.findByConsecutivo", query = "SELECT r FROM RecepcionMaterial r WHERE r.consecutivo = :consecutivo")
    , @NamedQuery(name = "RecepcionMaterial.findByCadenaCantidad", query = "SELECT r FROM RecepcionMaterial r WHERE r.cadenaCantidad = :cadenaCantidad")
    , @NamedQuery(name = "RecepcionMaterial.findByCantidad", query = "SELECT r FROM RecepcionMaterial r WHERE r.cantidad = :cantidad")
    , @NamedQuery(name = "RecepcionMaterial.findByUnidadMedidaCantidad", query = "SELECT r FROM RecepcionMaterial r WHERE r.unidadMedidaCantidad = :unidadMedidaCantidad")
    , @NamedQuery(name = "RecepcionMaterial.findByProveedor", query = "SELECT r FROM RecepcionMaterial r WHERE r.proveedor = :proveedor")
    , @NamedQuery(name = "RecepcionMaterial.findByRefProveedor", query = "SELECT r FROM RecepcionMaterial r WHERE r.refProveedor = :refProveedor")
    , @NamedQuery(name = "RecepcionMaterial.findByResultadoVerificacion", query = "SELECT r FROM RecepcionMaterial r WHERE r.resultadoVerificacion = :resultadoVerificacion")
    , @NamedQuery(name = "RecepcionMaterial.findByEstadoEmpaque", query = "SELECT r FROM RecepcionMaterial r WHERE r.estadoEmpaque = :estadoEmpaque")
    , @NamedQuery(name = "RecepcionMaterial.findByEstadoNucleos", query = "SELECT r FROM RecepcionMaterial r WHERE r.estadoNucleos = :estadoNucleos")
    , @NamedQuery(name = "RecepcionMaterial.findByPesajeAleatorio", query = "SELECT r FROM RecepcionMaterial r WHERE r.pesajeAleatorio = :pesajeAleatorio")
    , @NamedQuery(name = "RecepcionMaterial.findByUnidadMedidaPesaje", query = "SELECT r FROM RecepcionMaterial r WHERE r.unidadMedidaPesaje = :unidadMedidaPesaje")
    , @NamedQuery(name = "RecepcionMaterial.findByFinalizaFueraHorario", query = "SELECT r FROM RecepcionMaterial r WHERE r.finalizaFueraHorario = :finalizaFueraHorario")
    , @NamedQuery(name = "RecepcionMaterial.findByRepesaje", query = "SELECT r FROM RecepcionMaterial r WHERE r.repesaje = :repesaje")
    , @NamedQuery(name = "RecepcionMaterial.findByFirmaTransporte", query = "SELECT r FROM RecepcionMaterial r WHERE r.firmaTransporte = :firmaTransporte")
    , @NamedQuery(name = "RecepcionMaterial.findByFirmaVerifica", query = "SELECT r FROM RecepcionMaterial r WHERE r.firmaVerifica = :firmaVerifica")
    , @NamedQuery(name = "RecepcionMaterial.findByFirmaJa", query = "SELECT r FROM RecepcionMaterial r WHERE r.firmaJa = :firmaJa")
    , @NamedQuery(name = "RecepcionMaterial.findByFirmaDc", query = "SELECT r FROM RecepcionMaterial r WHERE r.firmaDc = :firmaDc")
    , @NamedQuery(name = "RecepcionMaterial.findByEstadoRecepcion", query = "SELECT r FROM RecepcionMaterial r WHERE r.estadoRecepcion = :estadoRecepcion")
    , @NamedQuery(name = "RecepcionMaterial.findByMuestras", query = "SELECT r FROM RecepcionMaterial r WHERE r.muestras = :muestras")
    , @NamedQuery(name = "RecepcionMaterial.findByNoMaestro", query = "SELECT r FROM RecepcionMaterial r WHERE r.noMaestro = :noMaestro")
    , @NamedQuery(name = "RecepcionMaterial.findByUsuarioRegistro", query = "SELECT r FROM RecepcionMaterial r WHERE r.usuarioRegistro = :usuarioRegistro")
    , @NamedQuery(name = "RecepcionMaterial.findByFechaRegistro", query = "SELECT r FROM RecepcionMaterial r WHERE r.fechaRegistro = :fechaRegistro")})
public class RecepcionMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recepcion_material")
    private Integer idRecepcionMaterial;
    @Basic(optional = false)
    @Column(name = "clasificacion")
    private int clasificacion;
    @Basic(optional = false)
    @Column(name = "fecha_recepcion")
    @Temporal(TemporalType.DATE)
    private Date fechaRecepcion;
    @Column(name = "hora_llegada")
    @Temporal(TemporalType.TIME)
    private Date horaLlegada;
    @Column(name = "inicio_descargue")
    @Temporal(TemporalType.TIME)
    private Date inicioDescargue;
    @Column(name = "termino_descargue")
    @Temporal(TemporalType.TIME)
    private Date terminoDescargue;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "lote")
    private String lote;
    @Column(name = "consecutivo")
    private Integer consecutivo;
    @Column(name = "cadenaCantidad")
    private String cadenaCantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "unidad_medida_cantidad")
    private String unidadMedidaCantidad;
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "ref_proveedor")
    private String refProveedor;
    @Lob
    @Column(name = "varificacion")
    private String varificacion;
    @Column(name = "resultado_verificacion")
    private Integer resultadoVerificacion;
    @Column(name = "estado_empaque")
    private Integer estadoEmpaque;
    @Column(name = "estado_nucleos")
    private Integer estadoNucleos;
    @Column(name = "pesaje_aleatorio")
    private Double pesajeAleatorio;
    @Column(name = "unidad_medida_pesaje")
    private String unidadMedidaPesaje;
    @Column(name = "finaliza_fuera_horario")
    private Integer finalizaFueraHorario;
    @Column(name = "repesaje")
    private String repesaje;
    @Column(name = "firma_transporte")
    private String firmaTransporte;
    @Column(name = "firma_verifica")
    private String firmaVerifica;
    @Column(name = "firma_ja")
    private String firmaJa;
    @Column(name = "firma_dc")
    private String firmaDc;
    @Lob
    @Column(name = "formato")
    private String formato;
    @Lob
    @Column(name = "anexos")
    private String anexos;
    @Column(name = "estado_recepcion")
    private Integer estadoRecepcion;
    @Column(name = "muestras")
    private Integer muestras;
    @Column(name = "no_maestro")
    private Integer noMaestro;
    @Lob
    @Column(name = "observacion_eliminado")
    private String observacionEliminado;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Lob
    @Column(name = "prestamo")
    private String prestamo;
    @Basic(optional = false)
    @Column(name = "usuario_registro")
    private String usuarioRegistro;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    public RecepcionMaterial() {
    }

    public RecepcionMaterial(Integer idRecepcionMaterial) {
        this.idRecepcionMaterial = idRecepcionMaterial;
    }

    public RecepcionMaterial(Integer idRecepcionMaterial, int clasificacion, Date fechaRecepcion, String usuarioRegistro, Date fechaRegistro) {
        this.idRecepcionMaterial = idRecepcionMaterial;
        this.clasificacion = clasificacion;
        this.fechaRecepcion = fechaRecepcion;
        this.usuarioRegistro = usuarioRegistro;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdRecepcionMaterial() {
        return idRecepcionMaterial;
    }

    public void setIdRecepcionMaterial(Integer idRecepcionMaterial) {
        this.idRecepcionMaterial = idRecepcionMaterial;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Date getInicioDescargue() {
        return inicioDescargue;
    }

    public void setInicioDescargue(Date inicioDescargue) {
        this.inicioDescargue = inicioDescargue;
    }

    public Date getTerminoDescargue() {
        return terminoDescargue;
    }

    public void setTerminoDescargue(Date terminoDescargue) {
        this.terminoDescargue = terminoDescargue;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getCadenaCantidad() {
        return cadenaCantidad;
    }

    public void setCadenaCantidad(String cadenaCantidad) {
        this.cadenaCantidad = cadenaCantidad;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedidaCantidad() {
        return unidadMedidaCantidad;
    }

    public void setUnidadMedidaCantidad(String unidadMedidaCantidad) {
        this.unidadMedidaCantidad = unidadMedidaCantidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getRefProveedor() {
        return refProveedor;
    }

    public void setRefProveedor(String refProveedor) {
        this.refProveedor = refProveedor;
    }

    public String getVarificacion() {
        return varificacion;
    }

    public void setVarificacion(String varificacion) {
        this.varificacion = varificacion;
    }

    public Integer getResultadoVerificacion() {
        return resultadoVerificacion;
    }

    public void setResultadoVerificacion(Integer resultadoVerificacion) {
        this.resultadoVerificacion = resultadoVerificacion;
    }

    public Integer getEstadoEmpaque() {
        return estadoEmpaque;
    }

    public void setEstadoEmpaque(Integer estadoEmpaque) {
        this.estadoEmpaque = estadoEmpaque;
    }

    public Integer getEstadoNucleos() {
        return estadoNucleos;
    }

    public void setEstadoNucleos(Integer estadoNucleos) {
        this.estadoNucleos = estadoNucleos;
    }

    public Double getPesajeAleatorio() {
        return pesajeAleatorio;
    }

    public void setPesajeAleatorio(Double pesajeAleatorio) {
        this.pesajeAleatorio = pesajeAleatorio;
    }

    public String getUnidadMedidaPesaje() {
        return unidadMedidaPesaje;
    }

    public void setUnidadMedidaPesaje(String unidadMedidaPesaje) {
        this.unidadMedidaPesaje = unidadMedidaPesaje;
    }

    public Integer getFinalizaFueraHorario() {
        return finalizaFueraHorario;
    }

    public void setFinalizaFueraHorario(Integer finalizaFueraHorario) {
        this.finalizaFueraHorario = finalizaFueraHorario;
    }

    public String getRepesaje() {
        return repesaje;
    }

    public void setRepesaje(String repesaje) {
        this.repesaje = repesaje;
    }

    public String getFirmaTransporte() {
        return firmaTransporte;
    }

    public void setFirmaTransporte(String firmaTransporte) {
        this.firmaTransporte = firmaTransporte;
    }

    public String getFirmaVerifica() {
        return firmaVerifica;
    }

    public void setFirmaVerifica(String firmaVerifica) {
        this.firmaVerifica = firmaVerifica;
    }

    public String getFirmaJa() {
        return firmaJa;
    }

    public void setFirmaJa(String firmaJa) {
        this.firmaJa = firmaJa;
    }

    public String getFirmaDc() {
        return firmaDc;
    }

    public void setFirmaDc(String firmaDc) {
        this.firmaDc = firmaDc;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getAnexos() {
        return anexos;
    }

    public void setAnexos(String anexos) {
        this.anexos = anexos;
    }

    public Integer getEstadoRecepcion() {
        return estadoRecepcion;
    }

    public void setEstadoRecepcion(Integer estadoRecepcion) {
        this.estadoRecepcion = estadoRecepcion;
    }

    public Integer getMuestras() {
        return muestras;
    }

    public void setMuestras(Integer muestras) {
        this.muestras = muestras;
    }

    public Integer getNoMaestro() {
        return noMaestro;
    }

    public void setNoMaestro(Integer noMaestro) {
        this.noMaestro = noMaestro;
    }

    public String getObservacionEliminado() {
        return observacionEliminado;
    }

    public void setObservacionEliminado(String observacionEliminado) {
        this.observacionEliminado = observacionEliminado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
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
        hash += (idRecepcionMaterial != null ? idRecepcionMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecepcionMaterial)) {
            return false;
        }
        RecepcionMaterial other = (RecepcionMaterial) object;
        if ((this.idRecepcionMaterial == null && other.idRecepcionMaterial != null) || (this.idRecepcionMaterial != null && !this.idRecepcionMaterial.equals(other.idRecepcionMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.RecepcionMaterial[ idRecepcionMaterial=" + idRecepcionMaterial + " ]";
    }
    
}
