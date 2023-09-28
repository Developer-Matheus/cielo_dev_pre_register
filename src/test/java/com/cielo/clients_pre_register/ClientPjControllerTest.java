package com.cielo.clients_pre_register;

import com.cielo.clients_pre_register.models.ClientPjModel;
import com.cielo.clients_pre_register.repositories.ClientPjRepository;
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
public class ClientPjControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    ClientPjRepository clientPjRepository;

    ClientPjModel client;

    @BeforeEach
    public void setUp(){
        client = new ClientPjModel(
                UUID.randomUUID(),
                "51149670000105",
                "Fabrica de pisos",
                1234,
                "53789655007",
                "Marcelo da Silva",
                "marcelo.silva@gmail.com");
    }

    @Test
    public void clientPjTestGetAll() throws Exception {
        mockMvc.perform(get("/clientes/pj"))
                .andExpect(status().isOk());
    }

    @Test
    public void clientPjTestGetById() throws Exception{

        client = new ClientPjModel(
                UUID.randomUUID(),
                "51149670000105",
                "Fabrica de pisos",
                1234,
                "53789655007",
                "Marcelo da Silva",
                "marcelo.silva@gmail.com");

        when(clientPjRepository.findById(client.getIdClient()))
                .thenReturn(Optional.ofNullable(client));

        clientPjRepository.findById(client.getIdClient());

        verify(clientPjRepository).findById(client.getIdClient());
        verifyNoMoreInteractions(clientPjRepository);
    }

    @Test
    public void clientPjTestSave() throws Exception {
        client = new ClientPjModel(
                UUID.randomUUID(),
                "54252383000114",
                "Fabrica de pisos",
                1234,
                "53789655007",
                "Marcelo da Silva",
                "marcelo.silva@gmail.com");

        mockMvc.perform(post("/clientes/pj")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());
    }

    @Test
    public void clientPjTestUpdate() throws Exception {

        ResultActions result =  mockMvc.perform(post("/clientes/pj")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());

        String responseBody = result.andReturn().getResponse().getContentAsString();

        ClientPjModel createdCliente = objectMapper.readValue(responseBody, ClientPjModel.class);

        UUID clientId = createdCliente.getIdClient();

        when(clientPjRepository.findById(createdCliente.getIdClient()))
                .thenReturn(Optional.ofNullable(createdCliente));

        createdCliente.setContact_name("Matheus da Silva Jr");
        createdCliente.setMcc(1324);

        mockMvc.perform(put("/clientes/pj/{id}",clientId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createdCliente)))
                .andExpect(status().isOk());

    }

    @Test
    public void clientPjTestDelete() throws Exception {

        ResultActions postResult = mockMvc.perform(post("/clientes/pj")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());

        String responseBody = postResult.andReturn().getResponse().getContentAsString();


        ClientPjModel createdCliente = objectMapper.readValue(responseBody, ClientPjModel.class);


        UUID idCliente = createdCliente.getIdClient();

        mockMvc.perform(delete("/clientes/pj/{id}", idCliente))
                .andExpect(status().isOk());


    }


}
