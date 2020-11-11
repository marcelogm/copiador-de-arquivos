package service;

import com.google.common.jimfs.Jimfs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CopyFileServiceTest {

    @Mock
    private OutputService outputService;

    @InjectMocks
    private CopyFileService copyFileService;

    @Test
    public void shouldCopyFiles() throws IOException {
        // given
        FileSystem fileSystem = Jimfs.newFileSystem();
        Path destination = fileSystem.getPath("destination");
        Path filePath = createFile(fileSystem, "source", "file.txt");

        // when
        copyFileService.copy(filePath, destination, "file.txt");

        // then
        assertTrue(Files.exists(destination.resolve("file.txt")));
    }

    @Test
    public void shouldIndicateWhenFileAlreadyExists() throws IOException {
        // given
        FileSystem fileSystem = Jimfs.newFileSystem();
        Path destination = fileSystem.getPath("destination");
        createFile(fileSystem, "destination", "file.txt");
        Path filePath = createFile(fileSystem, "source", "file.txt");

        // when
        copyFileService.copy(filePath, destination, "file.txt");

        // then
        verify(outputService, atLeastOnce()).out("O arquivo já existe no destino.");
    }

    @Test
    public void shouldIndicateWhenItsNotPossibleToCopyFile() throws IOException {
        // given
        FileSystem fileSystem = Jimfs.newFileSystem();
        Path filePath = fileSystem.getPath("source/file.txt");
        Path destination = fileSystem.getPath("destination");

        // when
        copyFileService.copy(filePath, destination, "file.txt");

        // then
        verify(outputService, atLeastOnce()).out("Falha ao copiar.");
    }

    private Path createFile(FileSystem fileSystem, String sourcePath, String fileName) throws IOException {
        Path source = fileSystem.getPath(sourcePath);
        Files.createDirectories(source);
        Path filePath = fileSystem.getPath(sourcePath + "/" + fileName);
        Files.write(filePath, "toCopy".getBytes());
        return filePath;
    }

}
