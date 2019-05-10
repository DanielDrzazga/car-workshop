package drzazga.daniel.geodezja.services;

import drzazga.daniel.geodezja.Dtos.PartDto;
import drzazga.daniel.geodezja.model.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartService {

    Page<Part> findAll(Pageable pageable);

    PartDto findById(Long id);

    void updatePart(PartDto partDto);

    Page<Part> findAllSearch(String searchWord, Pageable pageable);

    void deletePartById(Long id);

    void savePart(PartDto partDto);
}
