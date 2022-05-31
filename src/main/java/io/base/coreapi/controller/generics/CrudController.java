package io.base.coreapi.controller.generics;


import io.base.coreapi.errors.ValidationErrorBuilder;
import io.base.coreapi.exceptions.ResourceNotFoundException;
import io.base.coreapi.services.generics.cruds.CrudServices;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Data
public abstract class CrudController<T> {

    protected CrudServices service;

    public CrudController() {
        super();

    }

    public CrudController(CrudServices service) {
        super();
        this.service = service;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> queryByPage(Pageable pageable) {

        Page<T> pageInfo = service.findAll(pageable);
        if (pageInfo.getContent().isEmpty()) {
            return new ResponseEntity<Page<T>>(pageInfo, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<T>>(pageInfo, HttpStatus.OK);
    }


    @DeleteMapping(value = "{id}/")
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }


    @PatchMapping("{id}/")
    public ResponseEntity<?> updateByIdAndElement(@RequestParam("id") long id, @Valid @RequestBody T element, Errors errors) throws Throwable {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        String title=StringUtils.capitalize(element.getClass().getName()).replace("Io.base.coreapi.model.","");

        this.service.findById(id).orElseThrow(() -> new ResourceNotFoundException(title+" not found with id " + id));


        Optional<T> optionalProduct = this.service.UpdateById(id, element);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<T>(optionalProduct.get(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Without updates", HttpStatus.NO_CONTENT);
    }


    @PutMapping("{id}/")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody T elementForUpdate, BindingResult bindingResult, Errors errors) throws Throwable {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(bindingResult));
        }

        String title=StringUtils.capitalize(elementForUpdate.getClass().getName()).replace("Io.base.coreapi.model","");
        this.service.findById(id).orElseThrow(() -> new ResourceNotFoundException(title+" not found with id " + id));

        Optional<T> optionalProduct = service.UpdateById(id, elementForUpdate);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<T>(optionalProduct.get(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Without updates", HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity<?> creatProduct(@Valid @RequestBody T element,BindingResult bindingResult, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        return new ResponseEntity<T>((T) service.create(element), HttpStatus.OK);

    }


}
