package pe.edu.cibertec.examenfinal_CesarPeriche.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.examenfinal_CesarPeriche.model.Usuario;
import pe.edu.cibertec.examenfinal_CesarPeriche.service.UsuarioService;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // Obtener un pedido por ID (que ahora llama a metodoRemoto como parte del proceso)
    @GetMapping("/{id}")
    public Usuario getUser(@PathVariable Integer id) {
        return usuarioService.getUserById(id);
    }

    // Probar la llamada al metodoRemoto directamente si deseas
    @GetMapping("/remoto")
    public String metodoRemoto() {
        return usuarioService.metodoRemoto();
    }
}
