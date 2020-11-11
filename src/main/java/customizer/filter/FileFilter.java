package customizer.filter;

import model.FileToOrder;

import java.util.function.Predicate;

public interface FileFilter extends Predicate<FileToOrder> {
}
