package br.healthx.Healthx.User;

import br.healthx.Healthx.psychologist.dto.PsychologistRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User creteUser(PsychologistRequestDTO dto){
        if(this.userRepository.findByLogin(dto.user().getLogin()).isPresent()) throw  new RuntimeException("This user dont exist");

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.user().getPassword());
        User user = new User(dto.user().getLogin(), encryptedPassword, dto.user().getRole());

        return userRepository.save(user);
    }
}
