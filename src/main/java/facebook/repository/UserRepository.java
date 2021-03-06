package facebook.repository;

import facebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //Optional<User> findById(Long id);

    List<User> findAllByFirstNameIgnoreCaseAndSecondNameIgnoreCase(String firstName, String secondName);

    List<User> findAllByFirstNameIgnoreCaseAndSecondNameNotIgnoreCase(String firstName, String secondName);

    List<User> findAllBySecondNameIgnoreCaseAndFirstNameNotIgnoreCase(String secondName, String firstName);

    List<User> findAllByFirstNameIgnoreCaseOrSecondNameIgnoreCase(String firstName, String secondName);

    Optional<User> findById(Long id);

}
