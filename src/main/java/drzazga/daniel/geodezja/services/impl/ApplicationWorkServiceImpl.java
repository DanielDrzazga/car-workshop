package drzazga.daniel.geodezja.services.impl;

import drzazga.daniel.geodezja.Dtos.ApplicationWorkRegistrationDto;
import drzazga.daniel.geodezja.Dtos.UserDto;
import drzazga.daniel.geodezja.model.ApplicationWork;
import drzazga.daniel.geodezja.repositories.ApplicationWorkRepository;
import drzazga.daniel.geodezja.repositories.UserRepository;
import drzazga.daniel.geodezja.services.ApplicationWorkService;
import drzazga.daniel.geodezja.utilities.UserUtilities;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@Service
public class ApplicationWorkServiceImpl implements ApplicationWorkService {

    private final ApplicationWorkRepository applicationWorkRepository;
    private final UserRepository userRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public ApplicationWorkServiceImpl(ApplicationWorkRepository applicationWorkRepository, UserRepository userRepository, MapperFacade mapperFacade) {
        this.applicationWorkRepository = applicationWorkRepository;
        this.userRepository = userRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public void saveApplication(ApplicationWorkRegistrationDto applicationWorkRegistrationDto) {

        applicationWorkRegistrationDto.setCustomer(mapperFacade.map(userRepository.findByEmail(applicationWorkRegistrationDto.getCustomrrId()),UserDto.class));

        String loggedUser = UserUtilities.getLoggedUser();
        applicationWorkRegistrationDto.setEmployee(mapperFacade.map(userRepository.findByEmail(loggedUser), UserDto.class));

        Calendar calendar = Calendar.getInstance();
        Date startDate = new java.sql.Date(calendar.getTime().getTime());
        applicationWorkRegistrationDto.setStartDate(startDate);

        applicationWorkRegistrationDto.setStatus("IN_REPAIR");
        applicationWorkRepository.save(mapperFacade.map(applicationWorkRegistrationDto, ApplicationWork.class));
    }

}
