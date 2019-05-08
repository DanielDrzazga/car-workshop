package drzazga.daniel.geodezja.repositories;

import drzazga.daniel.geodezja.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.email= :email")
    void updateUserPassword(@Param("newPassword") String password, @Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.firstName = :newName, u.lastName = :newLastName, u.email = :newEmail WHERE u.id= :id")
    void updateUserProfile(@Param("newName") String newName, @Param("newLastName") String newLastName,
                                  @Param("newEmail") String newEmail, @Param("id") Long id);


}
