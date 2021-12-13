package pl.com.januszex.paka.gui.warehouse.api.dao;

import pl.com.januszex.paka.gui.warehouse.api.dto.LocalWarehouseDto;
import pl.com.januszex.paka.gui.warehouse.api.dto.LocalWarehouseRequestDto;

import java.util.Collection;

public interface LocalWarehouseDao {

    Collection<LocalWarehouseDto> getLocalWarehouses();

    LocalWarehouseDto getLocalWarehouseById(long id);

    LocalWarehouseDto addLocalWarehouse(LocalWarehouseRequestDto requestDto);

    void updateLocalWarehouse(long id, LocalWarehouseRequestDto requestDto);

    void deleteLocalWarehouse(long id);

}
