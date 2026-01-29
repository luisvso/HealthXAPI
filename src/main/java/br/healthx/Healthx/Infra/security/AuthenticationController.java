package br.healthx.Healthx.Infra.security;

import br.healthx.Healthx.User.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "Authentication", description = "Authentication to garantee the API security")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Login for Authorize user", description = "After register a user, you can login passing the username and password registered, and then a token will be generated. This token will be used on all your requests from now on with this user")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    // @PostMapping("/register")
    // public ResponseEntity register(@RequestBody @Valid RegisterDTO dto) {
    // if(this.userRepository.findByLogin(dto.login()) != null) return
    // ResponseEntity.badRequest().build();
    // if (this.userRepository.findByLogin(dto.login()).isPresent())
    // return ResponseEntity.badRequest().build();

    // String encryptedPassword = new
    // BCryptPasswordEncoder().encode(dto.password());
    // User user = new User(dto.login(), encryptedPassword, dto.role());

    // this.userRepository.save(user);

    // return ResponseEntity.ok().build();
    // }
}
