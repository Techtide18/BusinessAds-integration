package com.businessAds.integration.dto.google;

import com.businessAds.integration.enums.GoogleResponseContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class GoogleBaseDTO<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<T> operations;
    private Boolean partialFailure;
    private Boolean validateOnly;
    private GoogleResponseContentType responseContentType;

    public GoogleBaseDTO(List<T> operations) {
       this.operations = operations;
    }

    public GoogleBaseDTO(List<T> operations, Boolean partialFailure, Boolean validateOnly, GoogleResponseContentType responseContentType) {
        this.operations = operations;
        this.partialFailure = partialFailure;
        this.validateOnly = validateOnly;
        this.responseContentType = responseContentType;
    }
}
