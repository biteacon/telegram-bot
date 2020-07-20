package com.biteacon.services;

import com.biteacon.constants.Messages;

import java.io.IOException;

public class ResponseService {
    public static ResponseService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private ResponseService() {}

    public String getBlockByHeight(String key) {
//        todo: validate + transformation
        try {
            return SearchService.getInstance().getBlockByHeight(key);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Messages.NOT_FOUND;
    }

    public String getAccountByAddress(String key) {
//        todo: validate + transformation
        try {
            return SearchService.getInstance().getAccountByAddress(key);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Messages.NOT_FOUND;
    }

    public String getTransactionByHash(String key) {
//        todo: validate + transformation
        try {
            return SearchService.getInstance().getTransactionByHash(key);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Messages.NOT_FOUND;
    }

    public String getBlockByHash(String key) {
//        todo: validate + transformation
        try {
            return SearchService.getInstance().getBlockByHash(key);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Messages.NOT_FOUND;
    }

    private static class SingletonHolder {
        private static ResponseService INSTANCE = new ResponseService();
    }
}
