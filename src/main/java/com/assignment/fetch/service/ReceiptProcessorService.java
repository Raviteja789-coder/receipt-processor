package com.assignment.fetch.service;

import com.assignment.fetch.exception.ReceiptNotFoundException;
import com.assignment.fetch.model.PointsDto;
import com.assignment.fetch.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReceiptProcessorService {

    private final ConcurrentHashMap<Integer, Receipt> inMemoryDatabase = new ConcurrentHashMap<>();

    private final AtomicInteger idCounter = new AtomicInteger(1);

    private final ReceiptPointCalculator receiptPointCalculator = new ReceiptPointCalculator();

    /** Save/Add receipt data into an in memory database */
    public Receipt addReceipt(Receipt receipt) {
        Integer id = idCounter.getAndIncrement();
        receipt.setId(id);
        inMemoryDatabase.put(id, receipt);
        return receipt;
    }

    /** Fetch points for the receipt */
    public PointsDto getPointsByReceiptId(Integer id) {
        if(!inMemoryDatabase.containsKey(id)) {
            throw new ReceiptNotFoundException("Receipt does not exist for an id - " + id);
        }
        Receipt receipt = inMemoryDatabase
                .values()
                .stream()
                .filter((e) -> e.getId().equals(id))
                .findFirst()
                .get();
        int points = receiptPointCalculator.getPoints(receipt);
        return new PointsDto(points);
    }

}
