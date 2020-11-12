package br.com.marcelogm.sfcopier.customizer.dir;

import java.nio.file.Path;
import java.util.function.Function;

public interface DirectoryBuilder extends Function<Path, String> {
}
