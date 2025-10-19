package org.softwaretechnologies;

import org.softwaretechnologies.employee.*;

public class EmployeeFactory {

    /**
     * Сначала создайте классы, наследуемые от  {@link  org.softwaretechnologies.employee.Employee Employee} для каждого из значений в EmployeeType.
     * Функция должна создавать и возвращать Employee нужного типа. Тип зависит от значения параметра type.
     *  name имя сотрудника
     *  baseSalary базовая зарплата сотрудника
     *  type тип сотрудника
     * @return созданного сотрудника нужного типа. Тип зависит от параметра type.
     */



    public static Employee createEmployee(String name, int baseSalary, EmployeeType type) {
        if (type == null)
            return null;
        int current = type.ordinal();
        switch (current) {
            case 0 -> { return new Manager(name, baseSalary); }
            case 1 -> { return new Programmer(name, baseSalary); }
            case 2 -> { return new Tester(name, baseSalary); }
        }
        return null;

    }
}