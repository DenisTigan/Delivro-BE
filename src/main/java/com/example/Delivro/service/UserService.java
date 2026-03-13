package com.example.Delivro.service;


import com.example.Delivro.dto.LoginRequest;
import com.example.Delivro.dto.RegisterRequest;
import com.example.Delivro.model.ERole;
import com.example.Delivro.model.Role;
import com.example.Delivro.model.User;
import com.example.Delivro.repository.RoleRepository;
import com.example.Delivro.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerClient(RegisterRequest request){
        if(userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Eroare: Acest email exista deja!");
        }
        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.firstName(),
                request.lastName(),
                request.phone()
        );

        Role clientRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                .orElseThrow(() -> new RuntimeException("Eroare: Rolul ROLE_CLIENT nu a fost gasit in sistem!"));

        user.addRole(clientRole);

        return userRepository.save(user);
    }

    public String loginClient(LoginRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Eroare: Email-ul sau parola sunt incorecte"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new RuntimeException("Eroare:Email-ul sau parola incorrecta");
        }
        return "Logare cu succes! Bine ai venit, "+user.getFirstName()+ "!";
    }
}
