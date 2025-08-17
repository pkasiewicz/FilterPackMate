package pl.pkasiewicz.filterpackmate.infrastructure.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pkasiewicz.filterpackmate.domain.user.UserFacade;
import pl.pkasiewicz.filterpackmate.domain.user.dto.RegisterUserDto;
import pl.pkasiewicz.filterpackmate.domain.user.dto.RegistrationResultDto;
import pl.pkasiewicz.filterpackmate.infrastructure.security.jwt.JwtAuthenticationFacade;
import pl.pkasiewicz.filterpackmate.infrastructure.user.controller.dto.JwtResponseDto;
import pl.pkasiewicz.filterpackmate.infrastructure.user.controller.dto.TokenRequestDto;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "User Management", description = "Operations related to user registration and authentication")
public class UserController {

    private final UserFacade userFacade;
    private final JwtAuthenticationFacade jwtAuthenticationFacade;
    private final PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    @Operation(summary = "Register a new user",
            description = "Creates a new user account with the provided username and password.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User successfully registered",
                            content = @Content(schema = @Schema(implementation = RegistrationResultDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    public ResponseEntity<RegistrationResultDto> registerUser(
            @Parameter(description = "User registration details", required = true)
            @Valid @RequestBody RegisterUserDto registerUserDto) {

        String encodedPassword = bCryptPasswordEncoder.encode(registerUserDto.password());
        RegistrationResultDto registeredUser = userFacade.registerUser(
                new RegisterUserDto(registerUserDto.username(), encodedPassword)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/token")
    @Operation(summary = "Authenticate user and generate JWT token",
            description = "Authenticates the user and returns a JWT token for subsequent API calls.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Authentication successful",
                            content = @Content(schema = @Schema(implementation = JwtResponseDto.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication failed")
            })
    public ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(
            @Parameter(description = "Username and password for authentication", required = true)
            @Valid @RequestBody TokenRequestDto tokenRequest) {

        final JwtResponseDto jwtResponse = jwtAuthenticationFacade.authenticateAndGenerateToken(tokenRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}
