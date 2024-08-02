package Entidad;

import Entidad.ControlConsecutivos;
import Entidad.Ensambles;
import Entidad.Inyeccion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-08-02T08:26:03")
@StaticMetamodel(Procesos.class)
public class Procesos_ { 

    public static volatile SingularAttribute<Procesos, String> actual;
    public static volatile SingularAttribute<Procesos, Integer> estado;
    public static volatile CollectionAttribute<Procesos, Inyeccion> inyeccionCollection;
    public static volatile SingularAttribute<Procesos, String> sigla;
    public static volatile CollectionAttribute<Procesos, Ensambles> ensamblesCollection;
    public static volatile SingularAttribute<Procesos, String> usuarioRegistro;
    public static volatile SingularAttribute<Procesos, Date> fechaRegistro;
    public static volatile SingularAttribute<Procesos, Integer> idProcesos;
    public static volatile SingularAttribute<Procesos, String> nombre;
    public static volatile SingularAttribute<Procesos, String> tipoProceso;
    public static volatile CollectionAttribute<Procesos, ControlConsecutivos> controlConsecutivosCollection;

}