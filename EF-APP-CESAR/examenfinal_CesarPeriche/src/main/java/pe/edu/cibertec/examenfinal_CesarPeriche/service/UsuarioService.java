package pe.edu.cibertec.examenfinal_CesarPeriche.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.examenfinal_CesarPeriche.model.Usuario;
import pe.edu.cibertec.examenfinal_CesarPeriche.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @CircuitBreaker(name = "getUserService", fallbackMethod = "getOrderFallback")
    public Usuario getUserById(Integer id) {
        String remoteResponse = metodoRemoto();

        Optional<Usuario> UserOptional = usuarioRepository.findById(id);

        if (UserOptional.isPresent()) {
            Usuario usuario = UserOptional.get();
            usuario.setNomUsuario(usuario.getNomUsuario() + " - " + remoteResponse);
            return usuario;
        } else {
            throw new RuntimeException("Pedido no encontrado");
        }
    }

    @CircuitBreaker(name = "metodoRemoto", fallbackMethod = "fallbackMetodoRemoto")
    public String metodoRemoto() {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Error en llamada");
        }
        return "Respuesta exitosa del servicio remoto";
    }

    public Usuario getUserFallback(Integer id, Throwable throwable) {
        Usuario fallbackUser = new Usuario();
        fallbackUser.setIdUsuario(id);
        fallbackUser.setNomUsuario("Usuario no encontrado");
        fallbackUser.setContrasena("Desconocido");
        fallbackUser.setCorreoUsu("unknown@example.com");
        return fallbackUser;
    }

    public String fallbackMetodoRemoto(Throwable throwable) {
        return "Respuesta del fallback - El servicio remoto falló";
    }
}
