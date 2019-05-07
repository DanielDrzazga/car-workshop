package drzazga.daniel.geodezja.repositories;

import drzazga.daniel.geodezja.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository <User, Long>{

    boolean existsByEmail(String email);

}
