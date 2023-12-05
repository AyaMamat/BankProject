package com.laba.solvd.bankhierarchy.bankingcore;

import com.laba.solvd.bankhierarchy.exceptions.DuplicateAtmException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Branch {

    private String address;
    private List<ATM> atms;

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
        long barcode = Math.abs(random.nextLong());
        return barcode;
    }
    // I read that random might print negative numbers too,that's why I am using Math.abs instead of do while loop to make sure to print only positive numbers.

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
        return atms.stream().noneMatch(existingATM -> existingATM.getAtmCode() == atmCode);
    }
}
