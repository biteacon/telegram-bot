package com.biteacon.services;

import com.biteacon.POJOs.AccountByAddrResponse;
import com.biteacon.POJOs.BlockResponse;
import com.biteacon.POJOs.TransactionResponse;

public class TransformationService {
    public static TransformationService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private TransformationService() {}

    public String getFormattedAccount(AccountByAddrResponse account) {
        String formattedAccount = null;
        if (account != null && account.getData() != null && account.getData().getLikelibAccounts() != null
                && account.getData().getLikelibAccounts().size() > 0)
            formattedAccount = account.getData().getLikelibAccounts().toString();// todo: change it
        return formattedAccount;
    }

    public String getFormattedBlock(BlockResponse block) {
        String formattedBlock = null;
        if (block != null && block.getData() != null && block.getData().getLikelibBlocks() != null
                && block.getData().getLikelibBlocks().size() > 0)
            formattedBlock = block.getData().getLikelibBlocks().toString();// todo: change it
        return formattedBlock;
    }

    public String getFormattedTransaction(TransactionResponse transaction) {
        String formattedTransaction = null;
        if (transaction != null && transaction.getData() != null && transaction.getData().getLikelibTransactions() != null
                && transaction.getData().getLikelibTransactions().size() > 0)
            formattedTransaction = transaction.getData().getLikelibTransactions().toString();// todo: change it
        return formattedTransaction;
    }

    private static class SingletonHolder {
        private static TransformationService INSTANCE = new TransformationService();
    }
}
