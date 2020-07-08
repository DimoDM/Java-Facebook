package facebook.service.implementation;

import facebook.entity.User;
import facebook.repository.UserRepository;
import facebook.service.contract.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    @Autowired
    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User goToProfile(Long id) {
        return userRepository.findById(id).get();
    }
}
