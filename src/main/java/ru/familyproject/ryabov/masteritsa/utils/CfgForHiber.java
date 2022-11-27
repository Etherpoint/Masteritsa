package ru.familyproject.ryabov.masteritsa.utils;

public class CfgForHiber {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
    private static final String URL = "jdbc:postgresql://localhost:5432/family_shop";
    private static final String DRIVER = "org.postgresql.Driver";

    public static String getDIALECT() {
        return DIALECT;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getURL() {
        return URL;
    }

    public static String getDRIVER() {
        return DRIVER;
    }

    public static String getUSER() {
        return USER;
    }
}
