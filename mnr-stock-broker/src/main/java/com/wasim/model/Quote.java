package com.wasim.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    private Symbol symbol;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal lastPrice;
    private BigDecimal volume;
}
