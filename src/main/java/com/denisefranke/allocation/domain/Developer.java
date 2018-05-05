package com.denisefranke.allocation.domain;

import static com.denisefranke.allocation.constants.Constants.DEVELOPER_ALLOCATION;

public class Developer implements Employee {
    private final String name;
    private double allocation;

    public Developer(String name) {
        this.name = name;
        this.allocation = DEVELOPER_ALLOCATION;
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
