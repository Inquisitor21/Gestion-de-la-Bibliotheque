package tp4.service;

import java.util.List;

@FunctionalInterface
public interface DocumentSearchFunction {
    List<?> apply(String value);
}
