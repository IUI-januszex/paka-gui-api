package pl.com.januszex.paka.gui.warehouse.api.dao;

import pl.com.januszex.paka.gui.warehouse.api.dto.PostalCodeRangeDto;

import java.util.Collection;

public interface PostalCodeRangeDao {

    Collection<PostalCodeRangeDto> getAll();

    PostalCodeRangeDto getById(String id);

    PostalCodeRangeDto add(PostalCodeRangeDto request);

    void update(String id, PostalCodeRangeDto request);

    void delete(String id);
}
