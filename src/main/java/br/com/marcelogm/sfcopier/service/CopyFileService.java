package br.com.marcelogm.sfcopier.service;

import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CopyFileService {

    private final OutputService outputService;

    public void copy(Path source, Path directory, String fileName) {
        try {
            Files.createDirectories(directory);
            Files.copy(source, directory.resolve(fileName));
        } catch (FileAlreadyExistsException e) {
            outputService.out("O arquivo já existe no destino.");
        } catch (IOException e) {
            outputService.out("Falha ao copiar.");
        }
    }
}
