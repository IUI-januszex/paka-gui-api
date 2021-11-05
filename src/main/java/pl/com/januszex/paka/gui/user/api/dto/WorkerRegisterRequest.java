package pl.com.januszex.paka.gui.user.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class WorkerRegisterRequest extends UserRequest {
    private BigDecimal salary;
    private long warehouseId;
    private String name;
    private String surname;
}
