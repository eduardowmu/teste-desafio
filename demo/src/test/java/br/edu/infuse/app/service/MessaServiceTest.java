package br.edu.infuse.app.service;

import br.edu.infuse.app.model.Message;
import br.edu.infuse.app.repository.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MessaServiceTest {
    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private MessageService messageService;
    private Message message;
    private List<Message> messageList;
    @BeforeEach
    void setup() {
        this.message = new Message();
        this.messageList = new ArrayList<>();
    }

    @Test
    void saveTest() {
        when(this.messageRepository.save(any(Message.class))).thenReturn(message);
        Assertions.assertNotNull(this.messageService.save(message));
    }

    @Test
    void listAllTest() {
        this.messageList.add(this.message);
        when(this.messageRepository.findAll()).thenReturn(this.messageList);
        Assertions.assertTrue(this.messageService.listAll().size() > 0);
    }

    @Test
    void findOneTest() {
        Assertions.assertNull(this.messageService.findOne(new Message()));
    }
}