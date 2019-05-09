package drzazga.daniel.geodezja.repositories;

import drzazga.daniel.geodezja.model.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface PartRepository extends JpaRepository<Part,Long> {

    void updateNameAndPricePart(Long id, String name, BigDecimal price);

    @Query(value = "SELECT * FROM part p WHERE p.name LIKE %:param% OR p.price LIKE %:param", nativeQuery = true)
    Page<Part> findAllSearch(@Param("param") String param, Pageable pageable);

}
