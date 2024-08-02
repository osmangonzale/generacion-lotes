package Entidad;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-08-02T08:26:03")
@StaticMetamodel(Parametros.class)
public class Parametros_ { 

    public static volatile SingularAttribute<Parametros, Integer> idParametro;
    public static volatile SingularAttribute<Parametros, String> descripcion;
    public static volatile SingularAttribute<Parametros, Integer> estado;
    public static volatile SingularAttribute<Parametros, String> usuarioRegistro;
    public static volatile SingularAttribute<Parametros, Date> fechaRegistro;
    public static volatile SingularAttribute<Parametros, String> categoria;
    public static volatile SingularAttribute<Parametros, String> valor;

}