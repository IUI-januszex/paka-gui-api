package pl.com.januszex.paka.gui.parcel.api.dto;

import lombok.Data;
import pl.com.januszex.paka.gui.warehouse.api.dto.WarehouseType;

import java.time.LocalDateTime;

@Data
public class ParcelStateDto {
    private ParcelStateType type;
    private LocalDateTime changeTime;
    private Long warehouseId;
    private WarehouseType warehouseType;
    private String courierId;
}
