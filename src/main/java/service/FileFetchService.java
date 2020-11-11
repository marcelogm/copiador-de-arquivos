package service;

import com.google.inject.Singleton;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.nio.file.Files.*;
import static java.util.stream.Collectors.toList;

@Singleton
public class FileFetchService {

    public List<Path> getAll(Path path) {
        return get(path, Files::isDirectory)
                .flatMap(it -> get(it, Files::isRegularFile))
                .distinct()
                .collect(toList());
    }

    private Stream<Path> get(Path path, Predicate<Path> predicate) {
        try {
            return walk(path).filter(predicate);
        } catch (IOException e) {
            return Stream.empty();
        }
    }

}
