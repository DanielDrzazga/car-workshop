package drzazga.daniel.geodezja.repositories;

import drzazga.daniel.geodezja.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkRepository extends JpaRepository<Work,Long> {

    @Query(value = "SELECT * FROM work w WHERE w.name LIKE %:param%", nativeQuery = true)
    Page<Work> findAllSearch(@Param("param") String param, Pageable pageable);

}
