package br.edu.infuse.app.vh;

import br.edu.infuse.app.model.Client;
import br.edu.infuse.app.vo.EntityVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ViewHelperPatternTest {
    @InjectMocks
    private ClientVh clientVh;
    private Client client;
    private EntityVo entityVo;

    @BeforeEach
    void setup() {
        this.client = Client.builder()
                .clientName("")
                .build();

        this.client.setId(1L);

        this.entityVo = EntityVo.builder()
                .name("")
                .build();

        this.entityVo.setId(1L);
    }

    @Test
    void clientTest() {
        Assertions.assertNotNull(this.clientVh.getEntity(this.entityVo));
    }

    @Test
    void entityVoTest() {
        Assertions.assertNotNull(this.clientVh.getEntityVo(this.client));
    }
}