package org.softwaretechnologies.employee;

public class Manager extends Employee{
    @Override
    public int getMonthSalary(int month){
        if (month % 2 == 0) return baseSalary;
        return baseSalary / 2;
    }
    public Manager(String name, int baseSalary){
        super(name, baseSalary);
    }
}