package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.PaginationResponse;
import org.springframework.data.domain.Page;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PaginationMapper {

    default <E, D> PaginationResponse<D> toPaginationResponse(Page<E> entityPage, Function<E, D> mapperFunction) {
        List<D> dtoList = entityPage.getContent()
                .stream()
                .map(mapperFunction)
                .collect(Collectors.toList());

        return PaginationResponse.<D>builder()
                .content(dtoList)
                .pageNo(entityPage.getNumber())
                .pageSize(entityPage.getSize())
                .totalElements(entityPage.getTotalElements())
                .totalPages(entityPage.getTotalPages())
                .isLast(entityPage.isLast())
                .build();
    }
}
