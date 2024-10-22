package pe.edu.cibertec.app_server_exm2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.app_server_exm2.model.Usuario;
import pe.edu.cibertec.app_server_exm2.repository.UsuarioRepository;


@RequiredArgsConstructor
@Service
public class DetalleUsuarioServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username){
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Sr:" + username + "No existe"));
        return org.springframework.security.core.userdetails.User.withUsername(usuario.getNomusuario())
                .password(usuario.getPassword())
                .build();
    }
    public void saveUsuario(String username, String password) {
        Usuario usuario = new Usuario();
        usuario.setNomusuario(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuarioRepository.save(usuario);
    }
}
