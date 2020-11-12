package br.com.marcelogm.sfcopier.converter;

import br.com.marcelogm.sfcopier.model.FileToOrder;

import java.nio.file.Path;
import java.util.function.Function;

public class PathToFileToOrder implements Function<Path, FileToOrder> {

    @Override
    public FileToOrder apply(Path path) {
        return new FileToOrder(path.getFileName().toString(), path, 1);
    }

}
