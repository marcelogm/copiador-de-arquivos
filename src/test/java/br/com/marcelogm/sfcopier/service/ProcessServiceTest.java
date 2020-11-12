package br.com.marcelogm.sfcopier.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProcessServiceTest {

    @Mock
    private OutputService outputService;

    @InjectMocks
    private ProgressService progressService;

    @Test
    public void shouldIndicateThePercentageOfATask() {
        // expect
        progressService.progress(5);
        progressService.progress(5);

        // then
        verify(outputService, atLeastOnce()).out("Total de foto transferidas: 20%.");
        verify(outputService, atLeastOnce()).out("Total de foto transferidas: 40%.");
    }

}
