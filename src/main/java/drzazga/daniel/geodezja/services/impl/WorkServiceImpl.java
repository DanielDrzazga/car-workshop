package drzazga.daniel.geodezja.services.impl;

import drzazga.daniel.geodezja.Dtos.WorkDto;
import drzazga.daniel.geodezja.model.Work;
import drzazga.daniel.geodezja.repositories.WorkRepository;
import drzazga.daniel.geodezja.services.WorkService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public WorkServiceImpl(WorkRepository workRepository, MapperFacade mapperFacade) {
        this.workRepository = workRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public void saveWork(WorkDto workDto) {
        workRepository.save(mapperFacade.map(workDto,Work.class));
    }

    @Override
    public Page<Work> findAll(Pageable pageable) {
        return workRepository.findAll(pageable);
    }

    @Override
    public WorkDto findById(Long id) {
        return mapperFacade.map(workRepository.findById(id).get(),WorkDto.class);
    }

    @Override
    public void updatePart(WorkDto workDto) {
        workRepository.save(mapperFacade.map(workDto,Work.class));
    }

    @Override
    public Page<Work> findAllSearch(String param, Pageable pageable) {
        Page<Work> workPage = workRepository.findAllSearch(param,pageable);
        return workPage;
    }

    @Override
    public void deleteWorkById(Long id) {
        workRepository.deleteById(id);
    }
}
