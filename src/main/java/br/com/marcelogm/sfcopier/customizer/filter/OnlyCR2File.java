package br.com.marcelogm.sfcopier.customizer.filter;

import br.com.marcelogm.sfcopier.model.FileToOrder;

public class OnlyCR2File implements FileFilter {

    @Override
    public boolean test(FileToOrder fileToOrder) {
        return fileToOrder.getName().contains("CR2");
    }

}
