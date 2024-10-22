package pe.edu.cibertec.examenfinal_CesarPeriche.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdUsuario;

    private String NomUsuario;
    private String Contrasena;
    private String CorreoUsu;
}
