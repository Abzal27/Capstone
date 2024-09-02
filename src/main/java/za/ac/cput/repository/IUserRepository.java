package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Integer> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    @Override
    Optional<User> findById(Integer integer);
    List<User> findAll();
}
