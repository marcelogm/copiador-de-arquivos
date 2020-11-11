package service;

import com.google.inject.Singleton;

@Singleton
public class OutputService {

    public void out(String string) {
        System.out.println(string);
    }

}