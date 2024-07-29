package app;

import java.util.Arrays;

public enum RoleName {
    USER("user"), ADMIN("admin");

    public static final String[] NAMES = Arrays.stream(RoleName.values()).map(Enum::toString).toArray(String[]::new);

    private final String name;

    RoleName(String name) {
        this.name = name;
    }

    public static boolean contains(String name) {
        return Arrays.asList(NAMES).contains(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
