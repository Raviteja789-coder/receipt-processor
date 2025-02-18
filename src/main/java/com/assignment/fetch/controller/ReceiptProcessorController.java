package com.assignment.fetch.controller;

import com.assignment.fetch.exception.ReceiptNotFoundException;
import com.assignment.fetch.model.ErrorDto;
import com.assignment.fetch.model.PointsDto;
import com.assignment.fetch.model.Receipt;
import com.assignment.fetch.service.ReceiptProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiptProcessorController {

    @Autowired
    private ReceiptProcessorService receiptProcessorService;

    @PostMapping("/receipts/process")
    public Receipt addReceipt(@RequestBody Receipt receipt) {
        return receiptProcessorService.addReceipt(receipt);
    }

    @GetMapping("/receipts/{id}/points")
    public PointsDto getReceiptPoint(@PathVariable Integer id) {
        return receiptProcessorService.getPointsByReceiptId(id);
    }

    @ExceptionHandler(value = ReceiptNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleReceiptNotFoundException(ReceiptNotFoundException ex) {
        return new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
