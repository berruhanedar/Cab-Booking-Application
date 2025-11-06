package com.berru.app.cabbookingapplication.mapper;

import com.berru.app.cabbookingapplication.dto.NewPaymentRequestDTO;
import com.berru.app.cabbookingapplication.dto.PaymentResponseDTO;
import com.berru.app.cabbookingapplication.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "status", expression = "java(setDefaultStatus())")
    Payment toPayment(NewPaymentRequestDTO newPaymentRequestDTO);

    @Mapping(source = "booking.id", target = "bookingId")
    @Mapping(source = "driver.id", target = "driverId")
    PaymentResponseDTO toPaymentResponseDTO(Payment payment);

}
