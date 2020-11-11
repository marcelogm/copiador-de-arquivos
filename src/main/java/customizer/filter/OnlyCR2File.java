package customizer.filter;

import model.FileToOrder;

public class OnlyCR2File implements FileFilter {

    @Override
    public boolean test(FileToOrder fileToOrder) {
        return fileToOrder.getName().contains("CR2");
    }

}
