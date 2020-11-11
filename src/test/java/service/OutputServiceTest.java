package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OutputServiceTest {

    @InjectMocks
    private OutputService outputService;

    @Test
    public void shouldNotThrowException() {
        outputService.out("");
    }
}
