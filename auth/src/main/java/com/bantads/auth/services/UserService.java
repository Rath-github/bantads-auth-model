package com.bantads.auth.services;

import com.bantads.auth.dtos.DadosAuthDto;
import com.bantads.auth.dtos.DadosEditDto;
import com.bantads.auth.dtos.DadosNovoClienteDto;
import com.bantads.auth.dtos.UserResponseDto;
import com.bantads.auth.exeptions.RoleNaoPermitidaException;
import com.bantads.auth.exeptions.UsuarioJaExisteException;
import com.bantads.auth.models.User;
import com.bantads.auth.repositories.UserRepository;
import com.bantads.auth.roles.Roles;
import com.bantads.auth.util.EnviarEmail;
import com.bantads.auth.util.GeraSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EnviarEmail email;

    public UserResponseDto novoCliente(DadosNovoClienteDto user) throws UsuarioJaExisteException, RoleNaoPermitidaException {
        User existUser = userRepository.findByUsername(user.username());

        if(existUser != null){
            throw new UsuarioJaExisteException("Usuario ja existe!");
        }
        User novoUsuario = new User();
        novoUsuario.setUserRole(Roles.CLIENT);
        String senha = GeraSenha.generatePassword();
        novoUsuario.setPassword(passwordEncoder.encode(senha));
        novoUsuario.setUsername(user.username());
        User createdUser = userRepository.save(novoUsuario);

        try {
            String mensagem = "Temos uma boa noticia sua conta no Bantads foi aprovada! Aproveite todos os beneficios acessando a sua conta com o username cadastrado e a sua senha: \n\n" + senha +
                    "\n\n\nAgora voce faz parte do maior banco do SEPT!";
            email.sendEmail(user.email(), "Aprovação da sua conta BanTads!", mensagem);
        } catch (Exception e) {
            System.err.println("Erro ao enviar email: " + e.getMessage());
        }
        return new UserResponseDto(createdUser.getId(), createdUser.getUsername(), "ROLE_" + createdUser.getUserRole().getRole());
    }

    public UserResponseDto create(DadosAuthDto user) throws UsuarioJaExisteException, RoleNaoPermitidaException {
        User existUser = userRepository.findByUsername(user.username());
        if(existUser != null){
            throw new UsuarioJaExisteException("Usuario ja existe!");
        }

        User novoUsuario = new User();
        switch (user.role()){
            case 2:
                novoUsuario.setUserRole(Roles.GERENTE);
                break;
            case 3:
                novoUsuario.setUserRole(Roles.ADMIN);
                break;
            default:
                throw new RoleNaoPermitidaException("Role nao permitida!");

        }
        novoUsuario.setUsername(user.username());
        novoUsuario.setPassword(passwordEncoder.encode(user.password()));
        User createdUser = userRepository.save(novoUsuario);
        return new UserResponseDto(createdUser.getId(), createdUser.getUsername(), "ROLE_" + createdUser.getUserRole().getRole());
    }

    public User atualizarUsuario(DadosEditDto user ){
        Optional<User> usuarioEncontrado = userRepository.findById(user.id());

        if (usuarioEncontrado.isPresent()) {
            User usuario = usuarioEncontrado.get();
            if(user.username() != null){
                usuario.setUsername(user.username());
            }
            if(user.password() != null){
                usuario.setPassword(passwordEncoder.encode(user.password()));
            }
            if(user.role() != 0){
                switch (user.role()){
                    case 1:
                        usuario.setUserRole(Roles.CLIENT);
                        break;
                    case 2:
                        usuario.setUserRole(Roles.GERENTE);
                        break;
                    case 3:
                        usuario.setUserRole(Roles.ADMIN);
                        break;
                }
            }
            return userRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario não existente! id:  " + user.username());
        }
    }

    public List<User> listar(){
        return userRepository.findAll();
    }

    public Optional<User> removeUsuario(DadosEditDto user){
        Optional<User> usuario = userRepository.findById(user.id());
        if (usuario.isPresent()){
            userRepository.deleteById(user.id());
            System.out.println("usuario removido com sucesso!");
            return usuario;
        }else {
            System.out.println("O usuario não existente!");
            return usuario;
        }
    }
}
