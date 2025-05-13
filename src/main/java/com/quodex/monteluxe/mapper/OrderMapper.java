package com.quodex.monteluxe.mapper;

import com.quodex.monteluxe.dto.OrderDTO;
import com.quodex.monteluxe.model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderDTO toDTO(Orders orders);

    @Mapping(source = "userId", target = "user.id")
    Orders toEntity(OrderDTO orderDTO);
}
