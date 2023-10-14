package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services;

import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.module.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    void save(Address address);
    List<Address> findAll();
    Optional<Address> findById(Long id);
    boolean deleteById(Long id);
    boolean update(Address address);
}
