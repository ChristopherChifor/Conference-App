package Gateways;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface IGateway<T> {
    void write(T obj, String id);

    T read(String id);

    T delete(String id);

    List<String> getIds();

    Stream<T> filterStream(Predicate<T> predicate);

    List<T> filterList(Predicate<T> predicate);
}
