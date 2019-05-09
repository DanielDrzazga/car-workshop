package drzazga.daniel.geodezja.services.impl;

import drzazga.daniel.geodezja.Dtos.UserDto;
import drzazga.daniel.geodezja.Dtos.UserUpdateDto;
import drzazga.daniel.geodezja.model.User;
import drzazga.daniel.geodezja.repositories.UserRepository;
import drzazga.daniel.geodezja.services.AdminService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
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

    @Override
    public UserDto findUserById(Long id) {
        return mapperFacade.map(userRepository.findById(id).get(),UserDto.class);
    }

    @Override
    public UserUpdateDto findUserToUpdateById(Long id) {
        return mapperFacade.map(userRepository.findById(id).get(),UserUpdateDto.class);
    }

    @Override
    public void updateUser(Long id, Long nrRoli, int activity) {
        userRepository.updateActivationUser(activity, id);
        userRepository.updateRoleUser(nrRoli, id);
    }

    @Override
    public Page<User> findAllSearch(String param, Pageable pageable) {
        Page<User> userList = userRepository.findAllSearch(param, pageable);
        return userList;
    }

}