package drzazga.daniel.geodezja.repositories;

import drzazga.daniel.geodezja.model.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartRepository extends JpaRepository<Part,Long> {

    @Query(value = "SELECT * FROM part p WHERE p.name LIKE %:param%", nativeQuery = true)
    Page<Part> findAllSearch(@Param("param") String param, Pageable pageable);

//    @Modifying
//    @Query("UPDATE Part p SET p.name = :name, p.price = :price WHERE p.id= :id")
//    void updateNameAndPricePart(@Param("id") Long id, @Param("name") String name, @Param("price") BigDecimal price);

}
