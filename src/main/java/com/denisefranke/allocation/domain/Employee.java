package com.denisefranke.allocation.domain;

public interface Employee {
    void add(Employee employee);

    Employee getEmployee(int i);

    double getAllocation();
}