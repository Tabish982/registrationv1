package com.api.controller;

import com.api.dto.RegistrationDto;
import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/registration")

public class RegistrationController {


    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistration() {
        List<RegistrationDto> all =  registrationService.getAllRegistration();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRegistration(@Valid @RequestBody RegistrationDto registrationDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        RegistrationDto reg = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(reg, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegistration(@PathVariable long id){

        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Registration deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDto> updateRegistration(
            @PathVariable long id,
            @RequestBody RegistrationDto registrationDto
    ){
        RegistrationDto registrationDto1 = registrationService.updateRegistration(id, registrationDto);
        return new ResponseEntity<>(registrationDto1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(@PathVariable long id){
        RegistrationDto dto = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




}
