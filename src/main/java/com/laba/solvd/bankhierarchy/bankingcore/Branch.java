package com.laba.solvd.bankhierarchy.bankingcore;

import com.laba.solvd.bankhierarchy.exceptions.DuplicateAtmException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Branch {
    private String address;
    private final List<ATM> atms;

    public Branch(String address, int numberOfATMs) {
        this.address = address;
        this.atms = new ArrayList<>();

        for (int i = 0; i <= numberOfATMs; i++) {
            long atmCode = generateUniqueBarcode();
            ATM atm = new ATM(atmCode);
            atms.add(atm);
        }
    }

    private long generateUniqueBarcode() {
        Random random = new Random();
        long barcode;

        do {
            barcode = random.nextLong();  // Generate a random long value as the barcode
        } while (barcode < 0);  // Ensure the barcode is a non-negative number
        return barcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ATM> getAtms() {
        return atms;
    }

    public void addAtms(ATM atm) throws DuplicateAtmException {
        if (isATMCodeUnique(atm.getAtmCode())) {
            atms.add(atm);
        } else {
            throw new DuplicateAtmException("ATM code '" + atm.getAtmCode() + "' is not unique in branch " + address);
        }
    }

    private boolean isATMCodeUnique(long atmCode) {
        for (ATM existingATM : atms) {
            if (existingATM.getAtmCode() == atmCode) {
                return false; // Not unique
            }
        }
        return true; // Unique
    }
}
