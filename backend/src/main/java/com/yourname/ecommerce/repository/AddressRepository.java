package com.yourname.ecommerce.repository;

import com.yourname.ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
    List<Address> findByUser_Id(Long userId);
}