package com.cielo.clients_pre_register.controllers;


import com.cielo.clients_pre_register.dtos.ClientPjDto;
import com.cielo.clients_pre_register.models.ClientPjModel;
import com.cielo.clients_pre_register.repositories.ClientPjRepository;
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
public class ClientPjController {

    @Autowired
    ClientPjRepository clientPjRepository;

    @PostMapping("/clientes/pj")
    public ResponseEntity<Object>saveClient(@RequestBody @Valid ClientPjDto clientPjDto){
        var clientModel = new ClientPjModel();
        BeanUtils.copyProperties(clientPjDto, clientModel);

        Optional<ClientPjModel> existingClient = clientPjRepository.findByCnpj(clientModel.getCnpj());

        if (existingClient.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Client is already registered.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(clientPjRepository.save(clientModel));
    }


    @GetMapping("/clientes/pj")
    public ResponseEntity<List<ClientPjModel>> getAllClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientPjRepository.findAll());
    }

    @GetMapping("/clientes/pj/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value="id")UUID id){
        Optional<ClientPjModel> clientPjO = clientPjRepository.findById(id);
        if (clientPjO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientPjO.get());
    }

    @PutMapping("/clientes/pj/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid ClientPjDto clientPjDto){
        Optional<ClientPjModel> clientPjO = clientPjRepository.findById(id);
        if (clientPjO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        var clientModel  = clientPjO.get();
        BeanUtils.copyProperties(clientPjDto, clientModel);

        return ResponseEntity.status(HttpStatus.OK).body(clientPjRepository.save(clientModel));
    }

    @DeleteMapping("/clientes/pj/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value="id") UUID id){
        Optional<ClientPjModel> clientPjO = clientPjRepository.findById(id);
        if (clientPjO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        clientPjRepository.delete(clientPjO.get());
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
