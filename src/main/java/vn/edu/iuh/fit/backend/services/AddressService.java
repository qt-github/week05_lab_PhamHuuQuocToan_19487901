package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.backend.module.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    void save(Address address);
    List<Address> findAll();
    Optional<Address> findById(Long id);
    boolean deleteById(Long id);
    boolean update(Address address);
}
