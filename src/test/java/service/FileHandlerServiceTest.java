package service;

import converter.PathToFileToOrder;
import customizer.dir.DirectoryBuilder;
import customizer.filter.FileFilter;
import model.FileToOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FileHandlerServiceTest {

    @Mock
    private PathToFileToOrder pathToFileToOrder;

    @Mock
    private FileFetchService fileFetchService;

    @Mock
    private FileHolderService fileHolderService;

    @Mock
    private ReportService reportService;

    @Mock
    private ProgressService progressService;

    @Mock
    private CopyFileService copyFileService;

    @InjectMocks
    private FileHandlerService fileHandlerService;

    @Test
    public void shouldCopyFiles() {
        // given
        ArgumentCaptor<Path> fileCaptor = ArgumentCaptor.forClass(Path.class);
        ArgumentCaptor<Path> outputCaptor = ArgumentCaptor.forClass(Path.class);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        Path fileA = Path.of("A");
        Path fileB = Path.of("B");
        Path destination =  Path.of("destination");

        // and
        when(fileFetchService.getAll(any())).thenReturn(asList(fileA, fileB));
        when(fileHolderService.get()).thenReturn(asList(
                new FileToOrder("A", fileA, 1),
                new FileToOrder("B", fileB, 2)
        ));

        // when
        fileHandlerService.copy(Path.of("source"), destination);

        // then
        verify(fileHolderService, times(2)).put(any());
        verify(copyFileService, times(2)).copy(
                fileCaptor.capture(),
                outputCaptor.capture(),
                nameCaptor.capture()
        );

        // and
        List<Path> files = fileCaptor.getAllValues();
        assertEquals(fileA, files.get(0));
        assertEquals(fileB, files.get(1));

        // and
        assertEquals(destination, outputCaptor.getValue());

        // and
        List<String> names = nameCaptor.getAllValues();
        assertEquals("A", names.get(0));
        assertEquals("B", names.get(1));
    }

    @Test
    public void shouldCopyFilteredFiles() {
        // given
        FileFilter filter = mock(FileFilter.class);
        Path fileA = Path.of("A");
        Path fileB = Path.of("B");
        Path destination =  Path.of("destination");

        // and
        when(fileFetchService.getAll(any())).thenReturn(asList(fileA, fileB));
        when(fileHolderService.get()).thenReturn(asList(
                new FileToOrder("A", fileA, 1),
                new FileToOrder("B", fileB, 2)
        ));
        when(filter.test(any())).thenReturn(true)
                .thenReturn(false);

        // when
        fileHandlerService.copy(Path.of("source"), destination, filter);

        // then
        verify(fileHolderService, times(1)).put(any());
        verify(copyFileService, times(1))
                .copy(fileA, destination, "A");
    }

    @Test
    public void shouldCopyFilesToACustomSubpath() {
        // given
        DirectoryBuilder builder = mock(DirectoryBuilder.class);
        Path fileA = Path.of("A");
        Path destination =  Path.of("destination");

        // and
        when(fileFetchService.getAll(any())).thenReturn(singletonList(fileA));
        when(fileHolderService.get()).thenReturn(singletonList(new FileToOrder("A", fileA, 1)));
        when(builder.apply(any())).thenReturn("subpath");

        // when
        fileHandlerService.copy(Path.of("source"), destination, builder);

        // then
        verify(fileHolderService, times(1)).put(any());
        verify(copyFileService, times(1))
                .copy(fileA, destination.resolve("subpath"), "A");
    }

    @Test
    public void shouldCopyFilteredFilesToACustomSubpath() {
        // given
        FileFilter filter = mock(FileFilter.class);
        DirectoryBuilder builder = mock(DirectoryBuilder.class);
        Path fileA = Path.of("A");
        Path fileB = Path.of("B");
        Path destination =  Path.of("destination");

        // and
        when(fileFetchService.getAll(any())).thenReturn(asList(fileA, fileB));
        when(fileHolderService.get()).thenReturn(asList(
                new FileToOrder("A", fileA, 1),
                new FileToOrder("B", fileB, 2)
        ));
        when(builder.apply(any())).thenReturn("subpath");
        when(filter.test(any())).thenReturn(true)
                .thenReturn(false);

        // when
        fileHandlerService.copy(Path.of("source"), destination, filter, builder);

        // then
        verify(fileHolderService, times(1)).put(any());
        verify(copyFileService, times(1))
                .copy(fileA, destination.resolve("subpath"), "A");
    }

}
