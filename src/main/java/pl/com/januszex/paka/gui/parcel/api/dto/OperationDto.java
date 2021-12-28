package pl.com.januszex.paka.gui.parcel.api.dto;

import lombok.Data;
import pl.com.januszex.paka.gui.warehouse.api.dto.WarehouseType;

import java.math.BigDecimal;

@Data
public class OperationDto {
    private OperationType operationType;
    private Long warehouseId;
    private WarehouseType warehouseType;
    private BigDecimal amount;
}
