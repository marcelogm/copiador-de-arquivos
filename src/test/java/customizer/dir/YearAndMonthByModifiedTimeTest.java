package customizer.dir;

import com.google.common.jimfs.Jimfs;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class YearAndMonthByModifiedTimeTest {

    @Test
    public void shouldExtractTheYearAndMonthOfModifiedTimeAttribute() throws IOException {
        FileSystem fileSystem = Jimfs.newFileSystem();
        Path file = fileSystem.getPath("file.txt");
        Files.write(file, "toCopy".getBytes());
        LocalDateTime now = LocalDateTime.now();

        String subpath = new YearAndMonthByModifiedTime().apply(file);

        assertThat(subpath, is(now.getYear() + "\\" +
                now.getMonth().getValue() + " - " +
                now.getMonth().toString().toLowerCase() + "\\"));
    }
}
