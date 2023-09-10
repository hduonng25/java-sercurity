package lab.base.controller;

import lab.base.entity.User;
import lab.base.model.request.auth.LoginRequestDTO;
import lab.base.model.response.JwtAuthenticationResponse;
import lab.base.model.response.auth.LoginResponseDTO;
import lab.base.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginRequestDTO loginRequestDTO) {
        logger.info("Request: {}", loginRequestDTO);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        logger.info("Response: {}", new JwtAuthenticationResponse(jwt).getAccessToken());
        LoginResponseDTO responseDTO = new LoginResponseDTO(jwt, "Bearer");
        return ResponseEntity.ok(responseDTO);

    }
}

