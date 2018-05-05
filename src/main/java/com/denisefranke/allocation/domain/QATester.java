package com.denisefranke.allocation.domain;

import static com.denisefranke.allocation.constants.Constants.QATESTER_ALLOCATION;

public class QATester implements Employee {
    private String name;
    private double allocation;

    public QATester(String name) {
        this.name = name;
        this.allocation = QATESTER_ALLOCATION;
    }

    @Override
    public void add(Employee employee) {
    }

    @Override
    public Employee getEmployee(int i) {
        return null;
    }

    @Override
    public double getAllocation() {
        return allocation;
    }
}
