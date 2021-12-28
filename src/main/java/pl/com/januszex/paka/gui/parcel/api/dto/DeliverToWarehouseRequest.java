package pl.com.januszex.paka.gui.parcel.api.dto;

import lombok.Data;
import pl.com.januszex.paka.gui.warehouse.api.dto.WarehouseType;

@Data
public class DeliverToWarehouseRequest {

    private Long warehouseId;

    private WarehouseType warehouseType;
}
