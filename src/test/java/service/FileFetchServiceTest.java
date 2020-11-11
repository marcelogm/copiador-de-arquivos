package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FileFetchServiceTest {

    @InjectMocks
    private FileFetchService fileFetchService;

    @Test
    public void shouldFindAllFilesAtSomePath() {
        // when
        List<Path> paths = fileFetchService.getAll(getPath());

        // then
        assertThat(paths.size(), is(4));
        assertContainsPath(paths, "file1.txt");
        assertContainsPath(paths, "file2.txt");
        assertContainsPath(paths, "file3.txt");
        assertContainsPath(paths, "file4.txt");
    }


    private void assertContainsPath(List<Path> paths, String fileName) {
        Path path = paths.stream().filter(it -> it.getFileName().endsWith(fileName))
                .findFirst()
                .get();
        assertNotNull(path);
    }

    private Path getPath() {
        return Paths.get("src", "test", "resources", "service", "walker")
                .toFile()
                .toPath();
    }

}
