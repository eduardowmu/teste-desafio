package br.edu.infuse.app.strategy;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateClient implements Validator {
    private final ClientService clientService;
    @Autowired
    public ValidateClient(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public EntityDomain process(EntityDomain ed) throws BadRequestException {
        Order order = (Order)ed;
        return this.clientService.findById(order.getCustomerCode())
                .orElseThrow(() -> new BadRequestException("Cliente não encontrado!"));
    }
}
