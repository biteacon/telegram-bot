package com.biteacon.services;

import com.biteacon.POJOs.*;

import java.util.List;

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
        transformSubtransactions(transformation, account.getTransactions());
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

    public String getFormattedBlock(BlockResponse block) {
        String formattedBlock = null;
        if (block != null && block.getData() != null && block.getData().getLikelibBlocks() != null
                && block.getData().getLikelibBlocks().size() > 0)
            formattedBlock = transformBlock(block.getData().getLikelibBlocks().get(0));// todo: change it
        return formattedBlock;
    }

    private String transformBlock(LikelibBlock block) {
        StringBuilder transformation = new StringBuilder(
                "<b>Block</b>\n<b>Height:</b> " + getLinkedBlock(block.getHeight()) +
                "\n<b>Hash:</b> " + getLinkedHash(block.getHash()) +
                "\n<b>Coinbase:</b> " + getLinkedHash(block.getCoinbase()) +
                "\n<b>Previous block hash:</b> " + getLinkedHash(block.getPrevBlockHash()) +
                "\n<b>Nonce:</b> <code>" + block.getNonce() +
                "</code>\n<b>Timestamp:</b> <code>" + block.getTimestamp() + "</code>");
        transformSubtransactions(transformation, block.getTransactions());
        return transformation.toString();
    }

    private void transformSubtransactions(StringBuilder transformation, List<Transaction> transactions) {
        if (transactions != null && transactions.size() > 0) {
            transformation.
                    append("\n\n<b>Transactions(<code>").
                    append(transactions.size()).
                    append("</code>):</b>\n\n");
            for (Transaction transaction : transactions) {
                transformation.
                        append(getLinkedHash(transaction.getHash())).
                        append("\n\n");
            }
        }
    }

    public String getFormattedTransaction(TransactionResponse transaction) {
        String formattedTransaction = null;
        if (transaction != null && transaction.getData() != null && transaction.getData().getLikelibTransactions() != null
                && transaction.getData().getLikelibTransactions().size() > 0)
            formattedTransaction = transformTransaction(transaction.getData().getLikelibTransactions().get(0));// todo: change it
        return formattedTransaction;
    }

    private String transformTransaction(LikelibTransaction transaction) {
        StringBuilder transformation = new StringBuilder(
                "<b>Transaction</b>\n<b>Hash:</b> " + getLinkedHash(transaction.getHash()) +
                        "\n<b>Block height:</b> " + getLinkedBlock(transaction.getBlockHeight()) +
                        "\n<b>Coinbase:</b> <code>" + transaction.getAmount() +
                        "</code>\n<b>Account from:</b> " + getLinkedHash(transaction.getAccountFrom()) +
                        "\n<b>Account to:</b> " + getLinkedHash(transaction.getAccountTo()) +
                        "\n<b>Fee:</b> <code>" + transaction.getFee() +
                        "</code>\n<b>Fee left:</b> <code>" + transaction.getFeeLeft() +
                        "</code>\n<b>Status:</b> <code>" + transaction.getStatus() +
                        "</code>\n<b>Timestamp:</b> <code>" + transaction.getTimestamp() +
                        "</code>\n<b>Type:</b> <code>" + transaction.getType() +
                        "</code>\n<b>Message:</b> <code>" + transaction.getMessage() +
                        "</code>\n<b>Sign:</b> <code>" + transaction.getSign() +
                        "</code>\n<b>Data:</b> <code>" + transaction.getData() + "</code>");
        return transformation.toString();
    }

    private String getLinkedBlock(Long height) {
        return "/" + height;
    }

    private String getLinkedHash(String hash) {
        return "/" + hash;//todo: fix when hash starts with "/"
    }

    private static class SingletonHolder {
        private static TransformationService INSTANCE = new TransformationService();
    }
}
