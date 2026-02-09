/*
 * package com.dhatvibs.repository;
 * 
 * import com.dhatvibs.entity.Rider; import
 * org.springframework.data.jpa.repository.JpaRepository;
 * 
 * public interface RiderRepository extends JpaRepository<Rider, Long> { }
 */ 

package com.dhatvibs.repository;

import com.dhatvibs.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiderRepository extends JpaRepository<Rider, Long> {

    Optional<Rider> findByPhone(String phone);
}
