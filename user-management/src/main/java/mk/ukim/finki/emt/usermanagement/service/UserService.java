package mk.ukim.finki.emt.usermanagement.service;

import mk.ukim.finki.emt.usermanagement.service.forms.dto.req.UserRequestDto;
import mk.ukim.finki.emt.usermanagement.service.forms.dto.resp.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserResponseDto> getAllUser();
    public UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserByToken(String token);

}
