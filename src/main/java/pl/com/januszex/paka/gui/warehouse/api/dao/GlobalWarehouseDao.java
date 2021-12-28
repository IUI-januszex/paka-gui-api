package pl.com.januszex.paka.gui.warehouse.api.dao;

import pl.com.januszex.paka.gui.warehouse.api.dto.GlobalWarehouseDto;
import pl.com.januszex.paka.gui.warehouse.api.dto.GlobalWarehouseRequestDto;
import pl.com.januszex.paka.gui.warehouse.api.dto.WarehouseParcelsDto;

import java.util.Collection;

public interface GlobalWarehouseDao {

    Collection<GlobalWarehouseDto> getGlobalWarehouses();

    GlobalWarehouseDto getGlobalWarehouseById(long id);

    GlobalWarehouseDto addGlobalWarehouse(GlobalWarehouseRequestDto requestDto);

    void updateGlobalWarehouse(long id, GlobalWarehouseRequestDto requestDto);

    void deleteGlobalWarehouse(long id);

    WarehouseParcelsDto getParcels(long id);

}
