package app.view;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class SetWrapper<T> {
    private Set<T> set;

    public SetWrapper() {
        set = new HashSet<T>();
    }

    public SetWrapper(Set<T> set) {
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
