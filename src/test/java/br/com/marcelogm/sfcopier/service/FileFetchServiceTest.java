package br.com.marcelogm.sfcopier.service;

import com.google.common.jimfs.Jimfs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FileFetchServiceTest {

    @InjectMocks
    private FileFetchService fileFetchService;

    @Test
    public void shouldFindAllFilesAtSomePath() throws IOException {
        // given
        FileSystem fileSystem = Jimfs.newFileSystem();
        createFile(fileSystem, "test/folder1", "file1.txt");
        createFile(fileSystem, "test/folder1/folder2", "file2.txt");
        createFile(fileSystem, "test/folder3", "file3.txt");
        createFile(fileSystem, "test/folder3", "file4.txt");

        // when
        List<Path> paths = fileFetchService.getAll(fileSystem.getPath("test"));

        // then
        assertThat(paths.size(), is(4));
        assertContainsPath(paths, "file1.txt");
        assertContainsPath(paths, "file2.txt");
        assertContainsPath(paths, "file3.txt");
        assertContainsPath(paths, "file4.txt");
    }

    private Path createFile(FileSystem fileSystem, String path, String fileName) throws IOException {
        Path source = fileSystem.getPath(path);
        Files.createDirectories(source);
        Path filePath = fileSystem.getPath(path + "/" + fileName);
        Files.write(filePath, "toCopy".getBytes());
        return filePath;
    }

    private void assertContainsPath(List<Path> paths, String fileName) {
        Path path = paths.stream().filter(it -> it.getFileName().endsWith(fileName))
                .findFirst()
                .get();
        assertTrue(Files.exists(path));
    }

}
