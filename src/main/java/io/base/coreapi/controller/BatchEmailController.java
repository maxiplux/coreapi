package io.base.coreapi.controller;


import io.base.coreapi.model.BatchEmail;
import io.base.coreapi.model.dto.BatchEmailDto;
import io.base.coreapi.services.BatchEmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/batch-email-handle")
public class BatchEmailController {

    @Autowired
    private BatchEmailServices batchEmailServices;

    @PostMapping(value = "/")
    public ResponseEntity<BatchEmail> create(@Valid @RequestBody BatchEmailDto batchEmailDto) {
        return new ResponseEntity<>(
            batchEmailServices.createEmailBatch(batchEmailDto),
            HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BatchEmail> update(@PathVariable(value = "id") Long id,
                                             @Valid @RequestBody BatchEmailDto batchEmailDto) {
        return new ResponseEntity<>(
            batchEmailServices.updateEmailBatch(id, batchEmailDto),
            HttpStatus.OK);
    }

}

