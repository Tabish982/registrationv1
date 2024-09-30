package com.api.service;

import com.api.dto.RegistrationDto;
import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {


    private RegistrationRepository registrationRepository;

    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }


    public List<RegistrationDto> getAllRegistration() {
        List<Registration> all = registrationRepository.findAll();
        List<RegistrationDto> registrationDto = all.stream().map(e->entityToDto(e)).collect(Collectors.toList());
        return registrationDto;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        Registration registration = dtoToEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto registrationDto1 = entityToDto(registration);
        return registrationDto1;
    }

    public void deleteRegistration(long id) {
        if(!registrationRepository.existsById(id)){
            throw new ResourceNotFoundException("Id not found");
        }
        registrationRepository.deleteById(id);
    }


    public RegistrationDto updateRegistration(long id, RegistrationDto registrationDto) {
        Optional<Registration> byId = registrationRepository.findById(id);
        Registration registration1 = byId.get();
   //    Registration registration = dtoToEntity(registrationDto);
        registration1.setName(registrationDto.getName());
        registration1.setEmail(registrationDto.getEmail());
        registration1.setMobile(registrationDto.getMobile());
       Registration save = registrationRepository.save(registration1);
        return entityToDto(save);
    }

    Registration dtoToEntity(RegistrationDto registrationDto){
        Registration registration = modelMapper.map(registrationDto, Registration.class);
//        Registration registration = new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());
        return registration;
    }


    RegistrationDto entityToDto(Registration registration){
        RegistrationDto registrationDto = modelMapper.map(registration, RegistrationDto.class);
//        RegistrationDto dto = new RegistrationDto();
//        dto.setName(registration.getName());
//        dto.setEmail(registration.getEmail());
//        dto.setMobile(registration.getMobile());
        return registrationDto;
    }



    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Record not found"));
        return entityToDto(registration);
    }

}
