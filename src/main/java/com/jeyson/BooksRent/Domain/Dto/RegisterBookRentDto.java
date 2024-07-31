package com.jeyson.BooksRent.Domain.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class RegisterBookRentDto {

    @NotNull(message = "Debe ingresar el libro a rentar")
    private long bookId;

    @NotNull(message = "Debe ingresar el usuario que solicita el libro")
    private long userWhoRentedId;

    @NotNull(message = "Debe ingresar fecha y hora en que se solicitó el libro")
    private LocalDateTime rentedAt;

    @NotNull(message = "Debe ingresar el usuario que rentó el libro")
    private long userWhoDeliveryId;

    @NotNull(message = "Debe ingresar la hora en que el libro fue entregado al usuario")
    private LocalDateTime deliveryAt;

    private LocalDate returningDate;

}
