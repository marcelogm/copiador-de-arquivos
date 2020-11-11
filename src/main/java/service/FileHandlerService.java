package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import converter.PathToFileToOrder;
import customizer.dir.DirectoryBuilder;
import customizer.filter.FileFilter;
import lombok.RequiredArgsConstructor;
import model.FileToOrder;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class FileHandlerService {

    private final PathToFileToOrder pathToFileToOrder;
    private final FileFetchService fileFetchService;
    private final FileHolderService fileHolderService;
    private final ReportService reportService;
    private final ProgressService progressService;
    private final CopyFileService copyFileService;

    public void copy(Path source, Path destination)  {
        this.copy(source, destination, it -> true, it -> "");
    }

    public void copy(Path source, Path destination, FileFilter fileFilter)  {
        this.copy(source, destination, fileFilter, it -> "");
    }
    
    public void copy(Path source, Path destination, DirectoryBuilder getSubpath)  {
        this.copy(source, destination, it -> true, getSubpath);
    }

    public void copy(Path source, Path destination,
                     FileFilter matches,
                     DirectoryBuilder getSubpath)  {
        fileFetchService.getAll(source).stream()
                .map(pathToFileToOrder)
                .filter(matches)
                .forEach(fileHolderService::put);
        List<FileToOrder> files = fileHolderService.get();
        reportService.report(files);
        files.forEach(it -> {
            Path output = getOutput(it, destination, getSubpath);
            copyFileService.copy(it.getPath(), output, it.getName());
            progressService.progress(files.size());
        });
    }

    private Path getOutput(FileToOrder file, Path destination, Function<Path, String> getSubpath) {
        return Path.of(destination + "\\" + getSubpath.apply(file.getPath()));
    }

}
