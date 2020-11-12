package br.com.marcelogm.sfcopier.service;

import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.text.DecimalFormat;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ProgressService {

    private final OutputService outputService;

    private Long current = 0L;

    private String progress;

    public void progress(Integer total) {
        current++;
        String newProcess = getDecimal(total);
        if (!newProcess.equals(progress)) {
            progress = newProcess;
            outputService.out("Total de foto transferidas: " + progress + ".");
        }
    }

    private String getDecimal(Integer total) {
        return new DecimalFormat("##.#").format(((double)current / total) * 100) + "%";
    }
}
