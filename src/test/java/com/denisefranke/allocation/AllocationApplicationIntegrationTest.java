package com.denisefranke.allocation;

import com.denisefranke.allocation.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.denisefranke.allocation.constants.Constants.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AllocationApplicationIntegrationTest {
    private AllocationVisitor allocationVisitor = new AllocationVisitor();

    @Test
    public void contextLoads() {
    }

    @Test
    public void allocation_OneQATester_ExpectOneTierTest() {
        double expected = (QATESTER_ALLOCATION * 1);

        double actual = allocationVisitor.getAllocation(new QATester("QATester1"));

        assertEquals(expected, actual);
    }

    @Test
    public void allocation_OneDeveloper_ExpectOneTierTest() {
        double expected = (DEVELOPER_ALLOCATION * 1);

        double actual = allocationVisitor.getAllocation(new Developer("Developer1"));

        assertEquals(expected, actual);
    }

    @Test
    public void allocation_OneManagerTwoEmployees_ExpectTwoTiersTest() {
        double expected = (MANAGER_ALLOCATION * 1) + (QATESTER_ALLOCATION * 1) + (DEVELOPER_ALLOCATION * 1);

        double actual = allocationVisitor.getAllocation(getManagerB());

        assertEquals(expected, actual);
    }

    @Test
    public void allocation_TwoManagersTwoEmployees_ExpectThreeTiersTest() {
        double expected = (MANAGER_ALLOCATION * 2) + (QATESTER_ALLOCATION * 1) + (DEVELOPER_ALLOCATION * 1);

        double actual = allocationVisitor.getAllocation(getManagerA());

        assertEquals(expected, actual);
    }

    @Test
    public void allocation_Department_ExpectTwoTiersTest() {
        double expected = (MANAGER_ALLOCATION * 2) + (QATESTER_ALLOCATION * 1) + (DEVELOPER_ALLOCATION * 1);

        double actual = allocationVisitor.getAllocation(getDepartmentA());

        assertEquals(expected, actual);
    }

    @Test
    public void allocation_WhenDeveloper_ExpectCannotAddEmployTest() {
        Employee developer1 = new Developer("Developer1");
        Employee developer2 = new Developer("Developer2");
        developer1.add(developer2);

        assertNull(developer1.getEmployee(0));
    }

    @Test
    public void allocation_WhenQATester_ExpectCannotAddEmployTest() {
        Employee qATester1 = new QATester("QATester1");
        Employee qATester2 = new QATester("QATester2");
        qATester1.add(qATester2);

        assertNull(qATester1.getEmployee(0));
    }

    @Test
    public void allocation_WhenOneManagerWithNoReports_ExpectManagerAllocation() {
        Employee managerA = new Manager("ManagerA");

        assertEquals(MANAGER_ALLOCATION, managerA.getAllocation());
        assertNull(managerA.getEmployee(0));
    }

    @Test
    public void allocation_WhenOneDepartmentWithNoReports_ExpectManagerAllocation() {
        Employee departmentA = new Department();
        assertEquals(0.00, departmentA.getAllocation());
        assertNull(departmentA.getEmployee(0));
    }

    @Test
    public void allocation_WhenFiveDeepHierarchyWithMixedLevels_ExpectCorrectlyCalculatedAllocationForEachManager() {
        Employee managerA = new Manager("ManagerA");
        Employee qATesterA = new QATester("QATesterA");
        Employee developerA = new Developer("DeveloperA");
        Employee managerB = new Manager("ManagerB");
        Employee qATesterB = new QATester("QATesterB");
        Employee developerB = new Developer("DeveloperB");
        Employee managerC = new Manager("ManagerC");
        Employee qATesterC = new QATester("QATesterC");
        Employee developerC = new Developer("DeveloperC");
        Employee managerD = new Manager("ManagerD");
        Employee qATesterD = new QATester("QATesterD");
        Employee developerD = new Developer("DeveloperD");
        Employee managerE = new Manager("ManagerE");
        Employee qATesterE = new QATester("QATesterD");
        Employee developerE = new Developer("DeveloperD");
        managerA.add(managerB);
        managerA.add(qATesterA);
        managerA.add(developerA);
        managerB.add(managerC);
        managerB.add(qATesterB);
        managerB.add(developerB);
        managerC.add(managerD);
        managerC.add(qATesterC);
        managerC.add(developerC);
        managerD.add(managerE);
        managerD.add(qATesterD);
        managerD.add(developerD);
        managerE.add(qATesterE);
        managerE.add(developerE);

        assertEquals((MANAGER_ALLOCATION * 5) + (QATESTER_ALLOCATION * 5) + (DEVELOPER_ALLOCATION * 5), managerA.getAllocation());
        assertEquals((MANAGER_ALLOCATION * 4) + (QATESTER_ALLOCATION * 4) + (DEVELOPER_ALLOCATION * 4), managerB.getAllocation());
        assertEquals((MANAGER_ALLOCATION * 3) + (QATESTER_ALLOCATION * 3) + (DEVELOPER_ALLOCATION * 3), managerC.getAllocation());
        assertEquals((MANAGER_ALLOCATION * 2) + (QATESTER_ALLOCATION * 2) + (DEVELOPER_ALLOCATION * 2), managerD.getAllocation());
        assertEquals((MANAGER_ALLOCATION * 1) + (QATESTER_ALLOCATION * 1) + (DEVELOPER_ALLOCATION * 1), managerE.getAllocation());
    }


    private Manager getManagerA() {
        Manager managerA = new Manager("ManagerA");
        Manager managerB = getManagerB();
        managerA.add(managerB);
        return managerA;
    }

    private Manager getManagerB() {
        Manager managerB = new Manager("ManagerB");
        QATester qATester1 = new QATester("QATester1");
        Developer developer1 = new Developer("Developer1");
        managerB.add(qATester1);
        managerB.add(developer1);
        return managerB;
    }

    private Department getDepartmentA() {
        Department departmentA = new Department("DepartmentA");
        departmentA.add(getManagerA());
        return departmentA;
    }

}
