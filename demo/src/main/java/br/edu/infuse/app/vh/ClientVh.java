package br.edu.infuse.app.vh;

import br.edu.infuse.app.model.Client;
import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.utils.EntityUtils;
import br.edu.infuse.app.vo.EntityVo;

public class ClientVh implements EntityVh {
    @Override
    public Client getEntity(EntityVo vo) {
        return EntityUtils.getClientFromEntityVo(vo);
    }

    @Override
    public EntityVo getEntityVo(EntityDomain ed) {
        Client client = (Client)ed;
        return EntityUtils.getEntityVoFromClient(client);
    }
}
