package br.com.marcelogm.sfcopier.customizer.filter;

import br.com.marcelogm.sfcopier.model.FileToOrder;

import java.util.function.Predicate;

public interface FileFilter extends Predicate<FileToOrder> {
}
