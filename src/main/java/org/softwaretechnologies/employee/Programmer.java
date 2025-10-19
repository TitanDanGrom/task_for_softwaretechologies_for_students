package org.softwaretechnologies.employee;

public class Programmer extends Employee{
    @Override
    public int getMonthSalary(int month) { return baseSalary; }

    public Programmer(String name, int baseSalary){
        super(name, baseSalary);
    }
}