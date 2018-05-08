package com.denisefranke.allocation.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Department implements Employee {
    private String name;
    private double allocation;
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
        this.allocation = 0;
    }

    public Department() {

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
        Iterator<Employee> employeeIterator = employees.iterator();
        while (employeeIterator.hasNext()) {
            allocation += employeeIterator.next().getAllocation();
        }
        return allocation;
    }
}
