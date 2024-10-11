package Entidad;

import Entidad.Procesos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-10-04T11:38:41")
@StaticMetamodel(ControlConsecutivos.class)
public class ControlConsecutivos_ { 

    public static volatile SingularAttribute<ControlConsecutivos, Integer> estado;
    public static volatile SingularAttribute<ControlConsecutivos, String> lote;
    public static volatile SingularAttribute<ControlConsecutivos, Date> fechaRegistro;
    public static volatile SingularAttribute<ControlConsecutivos, Integer> aplica;
    public static volatile SingularAttribute<ControlConsecutivos, String> prueba;
    public static volatile SingularAttribute<ControlConsecutivos, String> nombre;
    public static volatile SingularAttribute<ControlConsecutivos, String> consecutivo;
    public static volatile SingularAttribute<ControlConsecutivos, Date> fecha;
    public static volatile SingularAttribute<ControlConsecutivos, String> ultimoActualizado;
    public static volatile SingularAttribute<ControlConsecutivos, String> usuarioRegistro;
    public static volatile SingularAttribute<ControlConsecutivos, String> observaciones;
    public static volatile SingularAttribute<ControlConsecutivos, Integer> idControlConsecutivos;
    public static volatile SingularAttribute<ControlConsecutivos, Integer> entrega;
    public static volatile SingularAttribute<ControlConsecutivos, Procesos> idProceso;

}