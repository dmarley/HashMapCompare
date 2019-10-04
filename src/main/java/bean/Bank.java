package bean;

import java.util.Objects;

public class Bank {

    String name;
    String isbn;
    String address;
    String city;

    public Bank(String name, String isbn, String address, String city) {
        this.name = name;
        this.isbn = isbn;
        this.address = address;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name) &&
                Objects.equals(isbn, bank.isbn) &&
                Objects.equals(address, bank.address) &&
                Objects.equals(city, bank.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isbn, address, city);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
