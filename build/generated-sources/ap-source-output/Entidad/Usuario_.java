package Entidad;

import Entidad.Rol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-10-04T11:38:41")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile SingularAttribute<Usuario, Integer> codigo;
    public static volatile SingularAttribute<Usuario, Short> estado;
    public static volatile SingularAttribute<Usuario, Rol> idRol;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile SingularAttribute<Usuario, String> usuarioRegistro;
    public static volatile SingularAttribute<Usuario, Date> fechaRegistro;
    public static volatile SingularAttribute<Usuario, String> tipoProcesos;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, Integer> documento;
    public static volatile SingularAttribute<Usuario, String> user;
    public static volatile SingularAttribute<Usuario, String> nombres;

}