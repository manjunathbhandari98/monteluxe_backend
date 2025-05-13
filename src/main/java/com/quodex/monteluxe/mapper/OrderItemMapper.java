package com.quodex.monteluxe.mapper;

import com.quodex.monteluxe.dto.OrderItemDTO;
import com.quodex.monteluxe.model.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    List<OrderItem> toEntity(List<OrderItemDTO> dto);
    List<OrderItemDTO> toDto(List<OrderItem> entity);

}
