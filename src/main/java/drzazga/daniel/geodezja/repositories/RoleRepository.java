package drzazga.daniel.geodezja.repositories;

import drzazga.daniel.geodezja.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Role findByRole(String role);

}
