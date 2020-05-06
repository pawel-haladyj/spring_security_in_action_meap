package pl.haladyj.SpringSecurity_CH5_WebApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.haladyj.SpringSecurity_CH5_WebApplication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
