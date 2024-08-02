package Entidad;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-08-02T08:26:03")
@StaticMetamodel(HistorialEnsamble.class)
public class HistorialEnsamble_ { 

    public static volatile SingularAttribute<HistorialEnsamble, String> descripcionPrestamo;
    public static volatile SingularAttribute<HistorialEnsamble, Integer> estado;
    public static volatile SingularAttribute<HistorialEnsamble, Date> fechaDevolucion;
    public static volatile SingularAttribute<HistorialEnsamble, String> responsablePrestamo;
    public static volatile SingularAttribute<HistorialEnsamble, Date> fechaPrestamo;
    public static volatile SingularAttribute<HistorialEnsamble, String> responsableDevolucion;
    public static volatile SingularAttribute<HistorialEnsamble, Integer> origen;
    public static volatile SingularAttribute<HistorialEnsamble, Integer> id;
    public static volatile SingularAttribute<HistorialEnsamble, String> descripcionDevolucion;
    public static volatile SingularAttribute<HistorialEnsamble, Integer> idHistorial;

}