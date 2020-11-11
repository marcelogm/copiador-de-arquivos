package service;

import model.FileToOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

    @Mock
    public OutputService outputService;

    @Test
    public void shouldDisplayDuplicatedPhotos() {
        // given
        ReportService reportService = new ReportService(outputService);
        List<FileToOrder> files = asList(
                getMock("must-be-unique", 1),
                getMock("must-be-duplicated-1", 2),
                getMock("must-be-duplicated-2", 3)
        );

        // when
        reportService.report(files);

        // then
        verify(outputService, atLeastOnce()).out("A foto must-be-duplicated-1 foi repetida 2 vezes.");
        verify(outputService, atLeastOnce()).out("A foto must-be-duplicated-2 foi repetida 3 vezes.");
    }

    private FileToOrder getMock(String name, Integer count) {
        return new FileToOrder(name, Path.of(""), count);
    }

}
