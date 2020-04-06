package ro.ubb.geekstack.converters;

import ro.ubb.geekstack.models.BaseEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<Model extends BaseEntity<Long>, InputDto, Dto>
        implements Converter<Model, InputDto, Dto> {

    public List<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    public List<Model> convertDtosToModels(Collection<InputDto> dtos) {
        return dtos.stream()
                .map(this::convertDtoToModel)
                .collect(Collectors.toList());
    }
}
