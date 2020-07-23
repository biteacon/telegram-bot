package com.biteacon.services;

import com.biteacon.POJOs.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                "<b>Account:</b> " + getLinkedAddress(account.getAddress()) +
                "\n<b>Balance:</b> <code>" + account.getBalance() + "</code>" +
                "\n<b>Type:</b> <code>" + account.getType() + "</code>" +
                "\n<b>Nonce:</b> <code>" + account.getNonce() + "</code>");
        transformSubtransactions(transformation, account.getTransactions());
        transformSubtransactions2(transformation, account.getTransactionsByAccountTo());
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
                "\n<b>Coinbase:</b> " + getLinkedAddress(block.getCoinbase()) +
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

    private void transformSubtransactions2(StringBuilder transformation, List<TransactionsByAccountTo> transactions) {
        if (transactions != null && transactions.size() > 0) {
            transformation.
                    append("\n\n<b>Transactions(<code>").
                    append(transactions.size()).
                    append("</code>):</b>\n\n");
            for (TransactionsByAccountTo transaction : transactions) {
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
                        "</code>\n<b>Account from:</b> " + getLinkedAddress(transaction.getAccountFrom()) +
                        "\n<b>Account to:</b> " + getLinkedAddress(transaction.getAccountTo()) +
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

    private String getLinkedAddress(String address) {
        return "/" + address;
    }

    private String getLinkedHash(String hash) {
        if (hash.charAt(0) != '/')
            hash = "/" + hash;
        int slash = hash.indexOf("/", hash.indexOf("/") + 1);
        int plus = hash.indexOf("+");
        int equals = hash.indexOf("=");

        if (slash > 0 && (slash < plus || plus < 0) && (slash < equals || plus < 0))
            hash = replaceOccurance(hash, "/", " <code>/", 2);
        else if (plus >= 0 && (plus < equals || equals < 0))
            hash = hash.replaceFirst(Pattern.quote("+"), " <code>+");
        else if (equals > 0)
            hash = hash.replaceFirst(Pattern.quote("="), " <code>=");
        if (hash.contains("<code>"))
            hash += "</code>";
        return hash;
    }

    private String replaceOccurance(String text, String replaceFrom, String replaceTo, int occuranceIndex)
    {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(replaceFrom);
        Matcher m = p.matcher(text);
        int count = 0;
        while (m.find()) {
            if (count++ == occuranceIndex - 1) {
                m.appendReplacement(sb, replaceTo);
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static class SingletonHolder {
        private static TransformationService INSTANCE = new TransformationService();
    }
}
