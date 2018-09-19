package paystation.domain;

import java.util.Map;
import java.util.HashMap;


/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    int nickel, dime, quarter;

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5: 
                nickel++;
                break;
            case 10: 
                dime++;
                break;
            case 25: 
                quarter++;
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        reset();
        return r;
    }
/*
    @Override
    public void cancel() {
        reset();
        
        
    }
   */
    @Override
    public Map<String, Integer> cancel(){
        Map <String, Integer> newMap = new HashMap();
        newMap.put("nickel", nickel);
        newMap.put("dime", dime);
        newMap.put("quarter", quarter);
        
        reset();
        nickel =0;
        dime =0;
        quarter = 0;
        return newMap;
    }
    
    
    @Override
    public int empty(){
        int insertedSoFarCopy = insertedSoFar;
        reset();
        return insertedSoFarCopy;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
    }
    
    
}
