package io.crowdcode.vehicle.security.filter;

public enum UserColumn {
    USERNAME, EMAIL, FIRSTNAME, SURENAME, ROLE;

    // ATTENTION: Convention enum names and column names are equal
    public String columnName() {
        return toString().toLowerCase();
    }

}