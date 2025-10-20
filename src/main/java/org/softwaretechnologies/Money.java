package org.softwaretechnologies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public class Money {
    private final MoneyType type;
    private final BigDecimal amount;

    public Money(MoneyType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Money равны, если одинаковый тип валют и одинаковое число денег до 4 знака после запятой.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @param o объект для сравнения
     * @return true - равно, false - иначе
     */
    @Override
    public boolean equals(Object o) {
        // TODO: реализуйте вышеуказанную функцию
        if (this == o) return true;
        if (!(o instanceof Money other)) return false;

        if (this.type == null) {
            if (other.type != null) return false;
        } else {
            if (other.type == null) return false;
            if (this.type != other.type) return false;
        }

        if (this.amount == null) {
            return other.amount == null;
        } else {
            if (other.amount == null) return false;
            BigDecimal a1 = this.amount.setScale(4, RoundingMode.HALF_UP);
            BigDecimal a2 = other.amount.setScale(4, RoundingMode.HALF_UP);
            return a1.compareTo(a2) == 0;
        }
    }

    /**
     * Формула:
     * (Если amount null 10000, иначе количество денег окрукленные до 4х знаков * 10000) + :
     * если USD , то 1
     * если EURO, то 2
     * если RUB, то 3
     * если KRONA, то 4
     * если null, то 5
     * Если amount округленный до 4х знаков * 10000 >= (Integer.MaxValue - 5), то хеш равен Integer.MaxValue
     * Округление по правилу: если >= 5, то в большую сторону, иначе - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @return хеш код по указанной формуле
     */
    @Override
    public int hashCode() {
        // TODO: реализуйте вышеуказанную функцию
        if (amount == null) return 10000;

        BigDecimal multiplied = amount.setScale(4, RoundingMode.HALF_UP).
                multiply(BigDecimal.valueOf(10000));

        if (multiplied.compareTo(BigDecimal.valueOf(MAX_VALUE - 5)) >= 0)
            return MAX_VALUE;

        return multiplied.intValueExact() +
                (type == null ? 5 : type.ordinal() + 1);

    }

    /**
     * Верните строку в формате
     * Тип_ВАЛЮТЫ: количество.XXXX
     * Тип_валюты: USD, EURO, RUB или KRONA
     * количество.XXXX - округленный amount до 4х знаков.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     * <p>
     * Если тип валюты null, то вернуть:
     * null: количество.XXXX
     * Если количество денег null, то вернуть:
     * Тип_ВАЛЮТЫ: null
     * Если и то и то null, то вернуть:
     * null: null
     *
     * @return приведение к строке по указанному формату.
     */
    @Override
    public String toString() {
        // TODO: реализуйте вышеуказанную функцию
        String typeString = (type == null) ? "null" : type.toString();
        String amountString;

        if (amount == null) amountString = "null";
        else {
            BigDecimal rounded = amount.setScale(4, RoundingMode.HALF_UP);
            amountString = rounded.toString();
        }

        return typeString + ": " + amountString;

    }

    public BigDecimal getAmount() {
        return amount;
    }

    public MoneyType getType() {
        return type;
    }

    public static void main(String[] args) {
        Money money = new Money(MoneyType.EURO, BigDecimal.valueOf(10.00012));
        Money money1 = new Money(MoneyType.USD, BigDecimal.valueOf(10.5000));
        System.out.println(money1.toString());
        System.out.println(money1.hashCode());
        System.out.println(money.equals(money1));
    }
}
