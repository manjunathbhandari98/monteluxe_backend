package com.quodex.monteluxe.mapper;

import com.quodex.monteluxe.dto.CategoryDTO;
import com.quodex.monteluxe.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
