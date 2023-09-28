package com.cielo.clients_pre_register.controllers;

import com.cielo.clients_pre_register.dtos.ClientPfDto;
import com.cielo.clients_pre_register.models.ClientPfModel;
import com.cielo.clients_pre_register.repositories.ClientPfRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ClientPfController {

    @Autowired
    ClientPfRepository clientPfRepository;

    @PostMapping("/clientes")
    public ResponseEntity<ClientPfModel>saveClient(@RequestBody @Valid ClientPfDto clientPfDto){
        var clientModel = new ClientPfModel();
        BeanUtils.copyProperties(clientPfDto, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientPfRepository.save(clientModel));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClientPfModel>> getAllClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientPfRepository.findAll());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value="id")UUID id){
        Optional<ClientPfModel> clientPfO = clientPfRepository.findById(id);
        if (clientPfO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientPfO.get());
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid ClientPfDto clientPfDto){
        Optional<ClientPfModel> clientPfO = clientPfRepository.findById(id);
        if (clientPfO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        var clientModel  = clientPfO.get();
        BeanUtils.copyProperties(clientPfDto, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientPfRepository.save(clientModel));
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value="id") UUID id){
        Optional<ClientPfModel> clientPfO = clientPfRepository.findById(id);
        if (clientPfO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        clientPfRepository.delete(clientPfO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted succesfully.");

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String , String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}
