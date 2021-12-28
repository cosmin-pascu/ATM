package ATM.demo.service;

import ATM.demo.domain.User;
import ATM.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> checkUserCredentials(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}
