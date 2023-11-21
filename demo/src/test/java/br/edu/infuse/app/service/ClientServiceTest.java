package br.edu.infuse.app.service;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.Client;
import br.edu.infuse.app.model.Message;
import br.edu.infuse.app.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private MessageService messageService;
    @InjectMocks
    private ClientService clientService;
    private Client client;
    private Message message;
    private List<Client> clientList;

    @BeforeEach
    void setNewClient() {
        this.client = new Client();
        this.message = new Message();
        this.clientList = new ArrayList<>();
    }

    @Test
    void saveTest() {
        when(this.clientRepository.save(any(Client.class))).thenReturn(client);
        Assertions.assertNotNull(this.clientService.save(client));
    }

    @Test
    void saveThrowExceptionTest() {
        when(this.clientRepository.save(any(Client.class))).thenThrow(new BadRequestException(""));
        when(this.messageService.save(any(Message.class))).thenReturn(this.message);
        Assertions.assertThrows(RuntimeException.class, () -> this.clientService.save(this.client));
    }

    @Test
    void listAllTest() {
        this.clientList.add(this.client);
        when(this.clientRepository.findAll()).thenReturn(this.clientList);
        Assertions.assertNotNull(this.clientService.listAll());
    }

    @Test
    void listAllWithThrowTest() {
        when(this.clientRepository.findAll()).thenThrow(new RuntimeException());
        when(this.messageService.save(any(Message.class))).thenReturn(this.message);
        Assertions.assertThrows(RuntimeException.class, () -> this.clientService.listAll());
    }

    @Test
    void findByIdTest() {
        Optional<Client> optionalClient = Optional.of(client);
        when(this.clientRepository.findById(anyLong())).thenReturn(optionalClient);
        Assertions.assertNotNull(this.clientService.findById(1L).get());
    }

    @Test
    void findByIdWithThrowTest() {
        Optional<Client> optionalClient = Optional.ofNullable(null);
        when(this.clientRepository.findById(anyLong())).thenThrow(new RuntimeException());
        when(this.messageService.save(any(Message.class))).thenReturn(this.message);
        Assertions.assertThrows(RuntimeException.class, () -> this.clientService.findById(1L));
    }

    @Test
    void findOneTest() {
        Assertions.assertNull(this.clientService.findOne(new Client()));
    }
}