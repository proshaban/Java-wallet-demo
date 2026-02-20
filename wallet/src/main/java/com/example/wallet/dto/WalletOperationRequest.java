package com.example.wallet.dto;

import com.example.wallet.model.OperationType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @Setter
public class WalletOperationRequest {

    @NotNull(message = "walletId must not be null")
    private UUID walletId;

    @NotNull(message = "operationType must not be null")
    private OperationType operationType;

    @NotNull(message = "amount must not be null")
    @DecimalMin(value = "0.01", message = "amount must be greater than 0")
    private BigDecimal amount;
}