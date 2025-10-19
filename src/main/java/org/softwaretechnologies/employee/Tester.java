package org.softwaretechnologies.employee;

import java.time.LocalDate;
import java.time.YearMonth;

public class Tester extends Employee {
    @Override
    public int getMonthSalary(int month){
        return baseSalary * YearMonth.
                of(LocalDate.now().getYear(), month).lengthOfMonth();
    }
    public Tester(String name, int baseSalary){
        super(name, baseSalary);
    }
}