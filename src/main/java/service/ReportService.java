package service;

import lombok.RequiredArgsConstructor;
import model.FileToOrder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ReportService {

    private final OutputService outputService;

    public void report(List<FileToOrder> files) {
        files.stream()
                .filter(this::isDuplicated)
                .forEach(this::pushMessage);
    }

    private Boolean isDuplicated(FileToOrder file) {
        return file.getCount() > 1;
    }

    private void pushMessage(FileToOrder file) {
        outputService.out("A foto " + file.getName() + " foi repetida " + file.getCount() + " vezes.");
    }
}
