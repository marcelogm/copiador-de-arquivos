import com.google.inject.Guice;
import com.google.inject.Injector;
import customizer.dir.YearAndMonthByModifiedTime;
import guice.BasicModule;
import service.FileHandlerService;

import java.util.Scanner;

import static java.nio.file.Path.of;

public class Main {
    public static void main(String[] args) {
        Injector i = Guice.createInjector(new BasicModule());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o path de origem dos arquivos:");
        String basePath = scanner.nextLine();

        System.out.println("Digite o path de destino:");
        String destinationPath = scanner.nextLine();

        i.getInstance(FileHandlerService.class).copy(of(basePath), of(destinationPath), new YearAndMonthByModifiedTime());
    }
}
