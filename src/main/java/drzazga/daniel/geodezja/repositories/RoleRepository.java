package drzazga.daniel.geodezja.repositories;

import drzazga.daniel.geodezja.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);

}
