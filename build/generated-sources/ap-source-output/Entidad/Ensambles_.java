package Entidad;

import Entidad.Procesos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-10-04T11:38:41")
@StaticMetamodel(Ensambles.class)
public class Ensambles_ { 

    public static volatile SingularAttribute<Ensambles, Integer> idEnsambles;
    public static volatile SingularAttribute<Ensambles, String> estado;
    public static volatile SingularAttribute<Ensambles, String> responsableRecibido;
    public static volatile SingularAttribute<Ensambles, Date> fechaRegistro;
    public static volatile SingularAttribute<Ensambles, Procesos> proceso;
    public static volatile SingularAttribute<Ensambles, String> nombre;
    public static volatile SingularAttribute<Ensambles, Integer> contramuestras;
    public static volatile SingularAttribute<Ensambles, String> consecutivo;
    public static volatile SingularAttribute<Ensambles, String> loteEnsamble;
    public static volatile SingularAttribute<Ensambles, Date> fecha;
    public static volatile SingularAttribute<Ensambles, String> prestamo;
    public static volatile SingularAttribute<Ensambles, String> lotesInyeccion;
    public static volatile SingularAttribute<Ensambles, String> usuarioRegistro;
    public static volatile SingularAttribute<Ensambles, String> observaciones;
    public static volatile SingularAttribute<Ensambles, String> justificacionnorecibido;
    public static volatile SingularAttribute<Ensambles, String> lotesImportados;
    public static volatile SingularAttribute<Ensambles, String> lotesMateriaprima;

}