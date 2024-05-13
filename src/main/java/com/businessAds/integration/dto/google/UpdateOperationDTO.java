package com.businessAds.integration.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdateOperationDTO<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private T update;

}
