package todo.todolist.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import todo.todolist.dto.user.UserLoginRequest;
import todo.todolist.dto.user.UserLoginResponse;
import todo.todolist.service.user.UserService;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserLoginResponse authenticate(UserLoginRequest requestDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Long userId = userService.findUserIdByEmail(userDetails.getUsername());

        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponse(token, userId);
    }
}
