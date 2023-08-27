package com.backpacking.tourist.tourist.repository;

import com.backpacking.tourist.tourist.domain.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TouristRepository extends JpaRepository<Tourist, Long> {

    Optional<Tourist> findSecuredUserByEmail(String email);

    Optional<Tourist> findById(long touristId);

    Optional<Tourist> findByEmail(String email);

    boolean existsTouristByEmail(String email);


}
