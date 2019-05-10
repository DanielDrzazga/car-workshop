package drzazga.daniel.geodezja.repositories;

import drzazga.daniel.geodezja.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.email= :email")
    void updateUserPassword(@Param("newPassword") String password, @Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.firstName = :newName, u.lastName = :newLastName, u.email = :newEmail WHERE u.id= :id")
    void updateUserProfile(@Param("newName") String newName, @Param("newLastName") String newLastName,
                                  @Param("newEmail") String newEmail, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE user_role r SET r.role_id = :roleId WHERE r.user_id= :id", nativeQuery = true)
    void updateRoleUser(@Param("roleId") Long nrRoli, @Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.active = :intActive WHERE u.id = :id")
    void updateActivationUser(@Param("intActive")int active, @Param("id") Long id);

    @Query(value = "SELECT * FROM user u WHERE u.first_name LIKE %:param% OR u.last_name LIKE %:param% OR email LIKE %:param%", nativeQuery = true)
    Page<User> findAllSearch(@Param("param") String param, Pageable pageable);

    @Modifying
    @Query("UPDATE User u SET u.active = :activeParam WHERE u.activationCode = :activationCode")
    void updateActivation(@Param("activeParam") int activeParam, @Param("activationCode") String activationCode);

}
