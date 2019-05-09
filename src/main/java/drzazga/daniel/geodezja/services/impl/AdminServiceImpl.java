package drzazga.daniel.geodezja.services.impl;

import drzazga.daniel.geodezja.Dtos.UserDto;
import drzazga.daniel.geodezja.Dtos.UserFileDto;
import drzazga.daniel.geodezja.Dtos.UserUpdateDto;
import drzazga.daniel.geodezja.model.Role;
import drzazga.daniel.geodezja.model.User;
import drzazga.daniel.geodezja.repositories.RoleRepository;
import drzazga.daniel.geodezja.repositories.UserRepository;
import drzazga.daniel.geodezja.services.AdminService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MapperFacade mapperFacade;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository, MapperFacade mapperFacade, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapperFacade = mapperFacade;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public void saveAll(List<UserFileDto> userList) {
        List<User> users = new ArrayList<>();

        for (UserFileDto userFileDto : userList) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findById(userFileDto.getNrRoli()).get());
            User user = mapperFacade.map(userFileDto,User.class);
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(userFileDto.getPassword()));
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}