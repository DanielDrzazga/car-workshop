package drzazga.daniel.geodezja.services;

import drzazga.daniel.geodezja.Dtos.UserDto;
import drzazga.daniel.geodezja.Dtos.UserFileDto;
import drzazga.daniel.geodezja.Dtos.UserUpdateDto;
import drzazga.daniel.geodezja.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

    Page<User> findAll(Pageable pageable);

    UserDto findUserById(Long id);

    void updateUser(Long id, Long nrRoli, int activity);

    UserUpdateDto findUserToUpdateById(Long id);

    Page<User> findAllSearch(String param, Pageable pageable);

    void saveAll(List<UserFileDto> userList);
}
