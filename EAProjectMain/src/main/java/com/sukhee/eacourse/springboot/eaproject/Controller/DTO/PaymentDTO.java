package com.sukhee.eacourse.springboot.eaproject.Controller.DTO;

import com.sukhee.eacourse.springboot.eaproject.Domain.Ticket;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PaymentDTO {
    @NotNull
    private Long eventId;
    @NotNull
    @Min(value=1, message="Quantity must be greater than 0.")
    private int quantity;
//    private int amount;
    @NotBlank@NotNull
    @Size(min = 16, max=16, message="cardNumber is not valid.")
    private String cardNumber;
    @NotBlank@NotNull
    @Pattern(
            regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$",
            message = "Card expiry date must be in MM/YY format"
    )
    private String cardExpiryDate;

    @NotBlank@NotNull
    @Pattern(
            regexp = "^[0-9]{3}$",
            message = "CardCVV is not valid."
    )
    private String cardCVV;

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "eventId=" + eventId +
                ", quantity=" + quantity +
//                ", amount='" + amount + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpiryDate='" + cardExpiryDate + '\'' +
                ", cardCVV='" + cardCVV + '\'' +
                '}';
    }
}
