package app.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class SetDto<T> {
    private Set<T> set;

    public SetDto() {
        set = new HashSet<T>();
    }

    public SetDto(Set<T> set) {
        this.set = set;
    }

    public void add(T item) {
        set.add(item);
    }

    public Set<T> getSet() {
        return set;
    }

    public void setSet(Set<T> set) {
        this.set = set;
    }

    public int size() {
        return set.size();
    }

    public Stream<T> stream() {
        return set.stream();
    }
}
