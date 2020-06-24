package com.projetofinal.endpoints;

import com.projetofinal.data.Authority;
import com.projetofinal.data.User;
import com.projetofinal.data.UserRoleName;
import com.projetofinal.repository.UserRepository;
import com.projetofinal.security.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
public class AuthenticateController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    MyUserDetailService myUserDetailService;
    @Autowired
    UserRepository userRepository;

    //######################################################################DOCUMENTAÇÃO######################################################################
    @ApiOperation(value = "Envia um token de autenticação para o usuário da aplicação.")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Usuario não encontrado. Provavelmente usuario ainda não foi cadastrado ou informou as credenciais erradas."),
                @ApiResponse(code = 200, message = "Usuario encontrado e token gerado.", response = AuthenticationResponse.class)
    })
    //########################################################################################################################################################
    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> autenticarUsuario(@RequestBody User user) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword()
            ));

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDetails userDetails = myUserDetailService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        long expirationTime =  jwtUtil.extractExpiration(token).getTime();
        return ResponseEntity.ok(new AuthenticationResponse(token, expirationTime));

    }

    //######################################################################DOCUMENTAÇÃO######################################################################
    @ApiOperation(value = "Cadastra um novo usuário.", notes = "Nome do usuario tem que ser único. Se usuario já for cadastrado codigo http 409 é retornado.")
    @ApiResponses(value = {@ApiResponse(code = 409, message = "Ususario já existe. Cliente deve informar um outro nome de usuario"),
            @ApiResponse(code = 201, message = "Usuário criado com sucesso.", response = User.class)
    })
    //########################################################################################################################################################
    @PostMapping(value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> cadastrarUsuario(@RequestBody User user) {
        user.setActive(true);
        user.setAuthorityList(List.of(new Authority(UserRoleName.ROLE_USER)));

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        try {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //######################################################################DOCUMENTAÇÃO######################################################################
    @ApiOperation(value = "Exclui um usuário.")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Usuario não foi encontrado."),
                           @ApiResponse(code = 204, message = "Usuario foi deletado com sucesso.")
    })
    //########################################################################################################################################################
    @DeleteMapping(value = "/excluir", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity excluirUsuario(@RequestBody User user) {

        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
