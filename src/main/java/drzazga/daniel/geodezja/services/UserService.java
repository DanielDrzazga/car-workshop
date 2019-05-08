package drzazga.daniel.geodezja.services;

import drzazga.daniel.geodezja.Dtos.UserDto;
import drzazga.daniel.geodezja.Dtos.UserRegistrationDto;

public interface UserService {

    boolean isEmailExist(String email);

    UserDto findByEmail(String email);

    void saveUser(UserRegistrationDto userRegistrationDto);

}
