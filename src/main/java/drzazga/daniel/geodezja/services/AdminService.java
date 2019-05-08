package drzazga.daniel.geodezja.services;

import drzazga.daniel.geodezja.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<User> findAll(Pageable pageable);
}
