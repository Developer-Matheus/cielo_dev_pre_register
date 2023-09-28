package com.cielo.clients_pre_register;

import com.cielo.clients_pre_register.models.ClientPfModel;
import com.cielo.clients_pre_register.repositories.ClientPfRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientPfControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    ClientPfRepository clientPfRepository;

    ClientPfModel client;

    @BeforeEach
    public void setUp(){
        client = new ClientPfModel(
                UUID.randomUUID(),
                "Matheus de Oliveira",
                "matheusdeoliveiracastanho@gmail.com",
                1344,
                "53789655007");
    }

    @Test
    public void clientPfTestGetAll() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk());
    }

    @Test
    public void clientPfTestGetById() throws Exception{

        client = new ClientPfModel(
                UUID.randomUUID(),
                "Matheus de Oliveira",
                "matheusdeoliveiracastanho@gmail.com",
                1344,
                "53789655007");

        when(clientPfRepository.findById(client.getIdClient()))
                .thenReturn(Optional.ofNullable(client));

        clientPfRepository.findById(client.getIdClient());

        verify(clientPfRepository).findById(client.getIdClient());
        verifyNoMoreInteractions(clientPfRepository);
    }

    @Test
    public void clientPfTestSave() throws Exception {
        client = new ClientPfModel(
                UUID.randomUUID(),
                "Matheus de Oliveira",
                "matheusdeoliveiracastanho@gmail.com",
                1344,
                "45312728039");

        mockMvc.perform(post("/clientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());
    }

    @Test
    public void clientPfTestUpdate() throws Exception {

        ResultActions result =  mockMvc.perform(post("/clientes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());

        String responseBody = result.andReturn().getResponse().getContentAsString();

        ClientPfModel createdCliente = objectMapper.readValue(responseBody, ClientPfModel.class);

        UUID clientId = createdCliente.getIdClient();

        when(clientPfRepository.findById(createdCliente.getIdClient()))
                .thenReturn(Optional.ofNullable(createdCliente));

        createdCliente.setName("Matheus da Silva Jr");
        createdCliente.setMcc(1324);

        mockMvc.perform(put("/clientes/{id}",clientId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createdCliente)))
                .andExpect(status().isOk());

    }

    @Test
    public void clientPfTestDelete() throws Exception {

        ResultActions postResult = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());

        String responseBody = postResult.andReturn().getResponse().getContentAsString();


        ClientPfModel createdCliente = objectMapper.readValue(responseBody, ClientPfModel.class);


        UUID idCliente = createdCliente.getIdClient();

        mockMvc.perform(delete("/clientes/{id}", idCliente))
                .andExpect(status().isOk());


    }


}
