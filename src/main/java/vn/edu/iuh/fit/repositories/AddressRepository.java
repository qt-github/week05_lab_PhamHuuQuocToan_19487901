package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.module.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}