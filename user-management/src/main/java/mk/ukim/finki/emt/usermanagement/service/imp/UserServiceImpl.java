package mk.ukim.finki.emt.usermanagement.service.imp;


import mk.ukim.finki.emt.usermanagement.auth.JwtHelper;
import mk.ukim.finki.emt.usermanagement.service.forms.dto.req.UserRequestDto;
import mk.ukim.finki.emt.usermanagement.service.forms.dto.resp.UserResponseDto;
import mk.ukim.finki.emt.usermanagement.domain.enitiy.User;
import mk.ukim.finki.emt.usermanagement.domain.exceptions.UserAlreadyExistsException;
import mk.ukim.finki.emt.usermanagement.domain.repository.UserRepository;
import mk.ukim.finki.emt.usermanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        return user;
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        List<User> userEnitiys = userRepo.findAll();
        List<UserResponseDto> userResponseDtoList = userEnitiys.stream().map(user->this.userEntityToUserRespDto(user)).collect(Collectors.toList());
        return userResponseDtoList;


    }
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        Optional<User> foundUser = this.userRepo.findByUsername(userRequestDto.getUsername());
        if (foundUser.isEmpty() && Objects.equals(userRequestDto.getPassword(), userRequestDto.getRepeatPassword())) {
            User user = new User(userRequestDto.getName(),
                    userRequestDto.getEmail(),
                    userRequestDto.getUsername(),
                    passwordEncoder.encode(userRequestDto.getPassword()),
                    userRequestDto.getDriverLicenceNumber(),
                    userRequestDto.getPhoneNumber(),
                    userRequestDto.getAddress());
            User createdUser = userRepo.save(user);
            return this.userEntityToUserRespDto(createdUser);
        } else {
            throw new UserAlreadyExistsException("User with email " + userRequestDto.getUsername() + " already exists");
        }
    }

    @Override
    public UserResponseDto getUserByToken(String token) {
        String username = this.jwtHelper.getUsernameFromToken(token);
        Optional<User> foundUser = this.userRepo.findByUsername(username);
        if (foundUser.isPresent()) {
            return this.userEntityToUserRespDto(foundUser.get());
        }
        else throw new UsernameNotFoundException("User not found");
    }

    public UserResponseDto userEntityToUserRespDto(User user){
        UserResponseDto userRespDto = this.modelMapper.map(user,UserResponseDto.class);
        return userRespDto;
    }


}
