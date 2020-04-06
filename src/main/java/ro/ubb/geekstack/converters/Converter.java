package ro.ubb.geekstack.converters;

import ro.ubb.geekstack.models.BaseEntity;

public interface Converter<Model extends BaseEntity<Long>, InputDto, Dto> {

    Model convertDtoToModel(InputDto dto);

    Dto convertModelToDto(Model model);
}
