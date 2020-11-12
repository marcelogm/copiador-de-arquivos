package br.com.marcelogm.sfcopier.service;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class OutputService {

    public void out(String out) {
        log.info(out);
    }

}