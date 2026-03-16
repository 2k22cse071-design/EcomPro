package com.yourname.ecommerce.repository;

import com.yourname.ecommerce.entity.User;
import com.yourname.ecommerce.entity.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    List<User> findByRole(Role role);
}