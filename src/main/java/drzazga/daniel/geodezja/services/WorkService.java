package drzazga.daniel.geodezja.services;

import drzazga.daniel.geodezja.Dtos.WorkDto;
import drzazga.daniel.geodezja.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkService {
    void saveWork(WorkDto workDto);

    Page<Work> findAll(Pageable pageable);

    WorkDto findById(Long id);

    void updatePart(WorkDto workDto);

    Page<Work> findAllSearch(String param, Pageable pageable);

    void deleteWorkById(Long id);
}
