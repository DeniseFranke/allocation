package com.denisefranke.allocation.domain;

public class AllocationVisitor  {

    public double getAllocation(Developer developer) {
        return developer.getAllocation();
    }

    public double getAllocation(QATester qATester) {
        return qATester.getAllocation();
    }

    public double getAllocation(Manager manager) {
        return manager.getAllocation();
    }

    public double getAllocation(Department department) {
        return department.getAllocation();
     }
}

