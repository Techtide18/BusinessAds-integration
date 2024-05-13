package com.businessAds.integration.dto.google;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOperationDTO<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private T create;

}

