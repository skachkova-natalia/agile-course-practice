package ru.unn.agile.DemandElasticity.Model;

public enum GoodsPairType {
    Undefined("Demand is undefined"),
    Substitute("Substitute good"),
    Complementary("Complementary good"),
    Independent("Independent goods");

    private final String name;
    private GoodsPairType(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
