package service;

import model.FileToOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FileHolderServiceTest {

    @InjectMocks
    private FileHolderService holder;

    @Test
    public void shouldCountDuplicatedPhotos() {
        // given
        List<FileToOrder> files = asList(
                getMock("must-be-unique"),
                getMock("must-be-duplicated"),
                getMock("must-be-duplicated")
        );

        // when
        files.forEach(holder::put);

        // then
        List<FileToOrder> result = holder.get();
        assertThat(result.size(), is(2));

        // and
        assertPhotoCount(result, "must-be-unique", 1);
        assertPhotoCount(result, "must-be-duplicated", 2);
    }

    private void assertPhotoCount(List<FileToOrder> files, String key, Integer count) {
        FileToOrder expected = files.stream()
                .filter(it -> it.getName().equals(key))
                .findFirst()
                .get();
        assertThat(expected.getCount(), is(count));
    }

    private FileToOrder getMock(String name) {
        return new FileToOrder(name, Path.of(""), 1);
    }

}
