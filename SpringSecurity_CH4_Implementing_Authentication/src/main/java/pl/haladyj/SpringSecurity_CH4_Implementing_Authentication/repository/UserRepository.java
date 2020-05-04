package pl.haladyj.SpringSecurity_CH4_Implementing_Authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.haladyj.SpringSecurity_CH4_Implementing_Authentication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
