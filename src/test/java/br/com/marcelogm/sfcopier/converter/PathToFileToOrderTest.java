package br.com.marcelogm.sfcopier.converter;

import br.com.marcelogm.sfcopier.model.FileToOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PathToFileToOrderTest {

    @InjectMocks
    private PathToFileToOrder converter;

    @Test
    public void mustConvert() {
        // given
        Path path = getPath();

        // when
        FileToOrder file = converter.apply(path);

        // then
        assertThat(file.getName(), is("file-to-convert.txt"));
        assertThat(file.getPath(), is(path));
        assertThat(file.getCount(), is(1));
    }

    private Path getPath() {
        return Paths.get("src", "test", "resources", "converter", "file-to-convert.txt");
    }
}
