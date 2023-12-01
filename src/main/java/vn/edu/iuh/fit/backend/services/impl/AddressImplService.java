package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.services.AddressService;

@Component
public class AddressImplService implements AddressService {

    public AddressImplService(AddressRepository repo) {
    }


}
