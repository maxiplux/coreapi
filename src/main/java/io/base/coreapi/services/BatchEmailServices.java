package io.base.coreapi.services;


import io.base.coreapi.model.BatchEmail;
import io.base.coreapi.model.dto.BatchEmailDto;

public interface BatchEmailServices {

    BatchEmail createEmailBatch(BatchEmailDto batchEmail);

    BatchEmail updateEmailBatch(Long Id, BatchEmailDto batchEmail);
}
