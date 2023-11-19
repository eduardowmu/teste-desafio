package br.edu.infuse.app.service;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.Client;
import br.edu.infuse.app.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void saveTest() {
        Client client = new Client();
        when(this.clientRepository.save(any(Client.class))).thenReturn(client);

        Assertions.assertNotNull(this.clientService.save(client));
    }

    @Test
    void throwBadExceptionFromFindByIdTest() {
        Client client = new Client();
        Optional<Client> optionalClient = Optional.of(client);
        when(this.clientRepository.findById(anyLong())).thenReturn(optionalClient);
        Assertions.assertNotNull(this.clientService.findById(1L).get());
    }
}