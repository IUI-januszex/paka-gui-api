package pl.com.januszex.paka.gui.user.api.dao;

import pl.com.januszex.paka.gui.user.api.dto.*;
import pl.com.januszex.paka.gui.user.domain.WorkerType;

public interface UserRegisterDao {

    ClientDto registerClient(ClientRegisterRequest clientRegisterRequest);

    BusinessClientDto registerBusinessClient(BusinessClientRequest businessClientRequest);

    WorkerDto registerWorker(WorkerRegisterRequest workerRegisterRequest, WorkerType type);
}
