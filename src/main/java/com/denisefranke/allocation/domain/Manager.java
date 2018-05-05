package com.denisefranke.allocation.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.denisefranke.allocation.constants.Constants.MANAGER_ALLOCATION;

public class Manager implements Employee {
    private String name;
    private double allocation;
    private List<Employee> employees = new ArrayList<>();

    public Manager(String name) {
        this.name = name;
        this.allocation = MANAGER_ALLOCATION;
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public Employee getEmployee(int i) {
        if (employees.size() > 0) {
            return employees.get(i);
        } else {
            return null;
        }
    }

    @Override
    public double getAllocation() {
        allocation = MANAGER_ALLOCATION;
        Iterator<Employee> employeeIterator = employees.iterator();
        while (employeeIterator.hasNext()) {
            allocation += employeeIterator.next().getAllocation();
        }
        return allocation;
    }
}
