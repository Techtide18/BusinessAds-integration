package com.businessAds.integration.dto.google;

import com.businessAds.integration.enums.GoogleResponseContentType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class GoogleBaseDTO<T, U> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> createOperations;
  //  private List<U> updateOperations;
    private boolean partialFailure;
    private boolean validateOnly;
    private GoogleResponseContentType responseContentType;

//    // Constructor for CreateOperation
//    public GoogleBaseDTO(List<T> createOperations, boolean createFlag, boolean partialFailure, boolean validateOnly,
//                         GoogleResponseContentType responseContentType) {
//        this(createOperations, null, partialFailure, validateOnly, responseContentType);
//    }
//
//    // Constructor for UpdateOperation
//    public GoogleBaseDTO(List<U> updateOperations, int updateFlag, boolean partialFailure, boolean validateOnly,
//                         GoogleResponseContentType responseContentType) {
//        this(null, updateOperations, partialFailure, validateOnly, responseContentType);
//    }
    public GoogleBaseDTO(List<T> createOperations) {
       this.createOperations = createOperations;
    }

//    public GoogleBaseDTO(List<U> updateOperation) {
//        this.updateOperations = updateOperation;
//    }
//

    public static class CreateOperation<T> implements Serializable {
        private static final long serialVersionUID = 1L;

        private T create;

        public CreateOperation(T create) {
            this.create = create;
        }
    }

    public class UpdateOperation<T> implements Serializable {
        private static final long serialVersionUID = 1L;

        private T update;

        public UpdateOperation(T update) {
            this.update = update;
        }
    }
}
