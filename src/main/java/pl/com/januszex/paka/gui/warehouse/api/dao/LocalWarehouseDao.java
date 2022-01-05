package pl.com.januszex.paka.gui.warehouse.api.dao;

import pl.com.januszex.paka.gui.warehouse.api.dto.LocalWarehouseDto;
import pl.com.januszex.paka.gui.warehouse.api.dto.LocalWarehouseRequestDto;
import pl.com.januszex.paka.gui.warehouse.api.dto.PostalCodeRangeDto;
import pl.com.januszex.paka.gui.warehouse.api.dto.WarehouseParcelsDto;

import java.util.Collection;

public interface LocalWarehouseDao {

    Collection<LocalWarehouseDto> getLocalWarehouses();

    LocalWarehouseDto getLocalWarehouseById(long id);

    LocalWarehouseDto addLocalWarehouse(LocalWarehouseRequestDto requestDto);

    void updateLocalWarehouse(long id, LocalWarehouseRequestDto requestDto);

    void deleteLocalWarehouse(long id);

    WarehouseParcelsDto getParcels(long id);

    Collection<PostalCodeRangeDto> getPostalCodes(long id);
}
