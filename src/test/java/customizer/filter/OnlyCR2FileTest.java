package customizer.filter;

import model.FileToOrder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OnlyCR2FileTest {

    @Test
    public void shouldOnlyAcceptCR2Files() {
        // given
        List<FileToOrder> file = Arrays.asList(
                new FileToOrder("text.txt", null, null),
                new FileToOrder("image.png", null, null),
                new FileToOrder("photo.CR2", null, null)
        );

        // expect
        Predicate<FileToOrder> predicate = new OnlyCR2File();
        assertFalse(predicate.test(file.get(0)));
        assertFalse(predicate.test(file.get(1)));
        assertTrue(predicate.test(file.get(2)));
    }
}
