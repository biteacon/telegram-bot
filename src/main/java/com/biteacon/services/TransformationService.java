package com.biteacon.services;

import com.biteacon.POJOs.*;

public class TransformationService {
    public static TransformationService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private TransformationService() {}

    public String getFormattedAccount(AccountByAddrResponse account) {
        String formattedAccount = null;
        if (account != null && account.getData() != null && account.getData().getLikelibAccounts() != null
                && account.getData().getLikelibAccounts().size() > 0)
            formattedAccount = transformAccount(account.getData().getLikelibAccounts().get(0));// todo: change it
        return formattedAccount;
    }

    private String transformAccount(LikelibAccount account) {
        StringBuilder transformation = new StringBuilder(
                "<b>Account:</b> " + getLinkedHash(account.getAddress()) +
                "\n<b>Balance:</b> <code>" + account.getBalance() + "</code>" +
                "\n<b>Type:</b> <code>" + account.getType() + "</code>" +
                "\n<b>Nonce:</b> <code>" + account.getNonce() + "</code>");
        if (account.getTransactions() != null && account.getTransactions().size() > 0) {
            transformation.
                    append("\n\n<b>Transactions(<code>").
                    append(account.getTransactions().size()).
                    append("</code>):</b>\n\n");
            for (Transaction transaction : account.getTransactions()) {
                transformation.
                        append(getLinkedHash(transaction.getHash())).
                        append("\n\n");
            }
        }
        if (account.getBlocks() != null && account.getBlocks().size() > 0) {
            transformation.
                    append("\n\n<b>Blocks(<code>").
                    append(account.getBlocks().size()).
                    append("</code>):</b>\n");
            for (Block block : account.getBlocks()) {
                transformation.
                        append(getLinkedBlock(block.getHeight())).
                        append("\n");
            }
        }
        return transformation.toString();
    }

    private String getLinkedBlock(Long height) {
        return "/" + height;
    }

    private String getLinkedHash(String hash) {
        return "/" + hash;//todo: fix when hash starts with "/"
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
