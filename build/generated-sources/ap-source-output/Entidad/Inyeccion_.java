package Entidad;

import Entidad.Procesos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-10-04T11:38:41")
@StaticMetamodel(Inyeccion.class)
public class Inyeccion_ { 

    public static volatile SingularAttribute<Inyeccion, String> lotePrincipal;
    public static volatile SingularAttribute<Inyeccion, Integer> estado;
    public static volatile SingularAttribute<Inyeccion, String> responsable;
    public static volatile SingularAttribute<Inyeccion, String> responsableRecibido;
    public static volatile SingularAttribute<Inyeccion, Date> fechaRegistro;
    public static volatile SingularAttribute<Inyeccion, Procesos> proceso;
    public static volatile SingularAttribute<Inyeccion, String> loteC;
    public static volatile SingularAttribute<Inyeccion, Integer> idControlInyeccion;
    public static volatile SingularAttribute<Inyeccion, String> nombre;
    public static volatile SingularAttribute<Inyeccion, Integer> contramuestras;
    public static volatile SingularAttribute<Inyeccion, String> consecutivo;
    public static volatile SingularAttribute<Inyeccion, Date> fecha;
    public static volatile SingularAttribute<Inyeccion, String> prestamo;
    public static volatile SingularAttribute<Inyeccion, String> usuarioRegistro;
    public static volatile SingularAttribute<Inyeccion, String> observaciones;
    public static volatile SingularAttribute<Inyeccion, String> justificacionnorecibido;
    public static volatile SingularAttribute<Inyeccion, String> loteP;
    public static volatile SingularAttribute<Inyeccion, Integer> molde;

}