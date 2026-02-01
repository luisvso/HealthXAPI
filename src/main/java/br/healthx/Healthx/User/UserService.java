package br.healthx.Healthx.User;

import br.healthx.Healthx.psychologist.dto.PsychologistRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User creteUser(PsychologistRequestDTO dto) {

        if (this.userRepository.findByLogin(dto.login()).isPresent())
            throw new RuntimeException("This user dont exist");

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User userlogin = new User(dto.login(), encryptedPassword, dto.role());

        return userRepository.save(userlogin);
    }
}
