package drzazga.daniel.geodezja.services.impl;

import drzazga.daniel.geodezja.Dtos.PartDto;
import drzazga.daniel.geodezja.model.Part;
import drzazga.daniel.geodezja.repositories.PartRepository;
import drzazga.daniel.geodezja.services.PartService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, MapperFacade mapperFacade) {
        this.partRepository = partRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public Page<Part> findAll(Pageable pageable) {
        return partRepository.findAll(pageable);
    }

    @Override
    public PartDto findById(Long id) {
        return mapperFacade.map(partRepository.findById(id).get(),PartDto.class);
    }

    @Override
    public void updatePart(PartDto partDto) {
        partRepository.save(mapperFacade.map(partDto,Part.class));
    }

    @Override
    public Page<Part> findAllSearch(String param, Pageable pageable) {
        Page<Part> partPage = partRepository.findAllSearch(param,pageable);
        return partPage;
    }

    @Override
    public void deletePartById(Long id) {
        partRepository.deleteById(id);
    }

    @Override
    public void savePart(PartDto partDto) {
        partRepository.save(mapperFacade.map(partDto, Part.class));
    }
}
