package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services.impl;

import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.module.Address;
import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.repositories.AddressRepository;
import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services.AddressService;

import java.util.List;
import java.util.Optional;

public class AddressImplService implements AddressService {
    private final AddressRepository repo;

    public AddressImplService(AddressRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(Address address) {
        repo.save(address);
    }

    @Override
    public List<Address> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Address> address = repo.findById(id);
        if (address.isPresent()) {
            repo.delete(address.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Address address) {
        Optional<Address> addressOptional = repo.findById(address.getId());
        if (addressOptional.isPresent()) {
            repo.save(address);
            return true;
        }
        return false;
    }
}
