package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.module.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}