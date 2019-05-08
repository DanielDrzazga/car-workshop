package drzazga.daniel.geodezja.services.impl;

import drzazga.daniel.geodezja.model.User;
import drzazga.daniel.geodezja.repositories.UserRepository;
import drzazga.daniel.geodezja.services.AdminService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, MapperFacade mapperFacade) {
        this.userRepository = userRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}