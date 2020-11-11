package converter;

import com.google.inject.Singleton;
import model.FileToOrder;

import java.nio.file.Path;
import java.util.function.Function;

@Singleton
public class PathToFileToOrder implements Function<Path, FileToOrder> {

    @Override
    public FileToOrder apply(Path path) {
        return new FileToOrder(path.getFileName().toString(), path, 1);
    }

}
