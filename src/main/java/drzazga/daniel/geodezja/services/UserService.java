package drzazga.daniel.geodezja.services;

import drzazga.daniel.geodezja.Dtos.UserDto;
import drzazga.daniel.geodezja.Dtos.UserPasswordChangeDto;
import drzazga.daniel.geodezja.Dtos.UserRegistrationDto;

import java.util.List;

public interface UserService {

    boolean isEmailExist(String email);

    UserDto findByEmail(String email);

    UserPasswordChangeDto findByEmailPassChanger(String email);

    void saveUser(UserRegistrationDto userRegistrationDto);

    void updateUserPassword(String newPassword, String email);

    void updateUserProfile(String newName, String newLastName, String newEmail, Long id);

    void updateUserActivation(int activeCode, String activationCode);

    List<UserDto> findAllCustomers();
}
