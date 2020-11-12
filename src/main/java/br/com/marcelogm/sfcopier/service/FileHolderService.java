package br.com.marcelogm.sfcopier.service;

import br.com.marcelogm.sfcopier.model.FileToOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileHolderService {

    private final HashMap<String, FileToOrder> photos = new HashMap<>();

    public void put(FileToOrder file) {
        String key = this.getKey(file);
        if (photos.containsKey(key)) {
            increaseCount(photos.get(key));
        } else {
            photos.put(key, file);
        }
    }

    public List<FileToOrder> get() {
        return new ArrayList<>(photos.values());
    }

    private void increaseCount(FileToOrder file) {
        file.setCount(file.getCount() + 1);
    }

    private String getKey(FileToOrder model) {
        return model.getName();
    }

}
