package com.biteacon.services;

import com.biteacon.POJOs.*;
import com.biteacon.constants.ApplicationConstants;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransformationService {
    public static TransformationService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private TransformationService() {}

    public String getFormattedAccount(GraphqlResponse account) {
        String formattedAccount = null;
        if (account != null && account.getData() != null && account.getData().getLikelibAccounts() != null
                && account.getData().getLikelibAccounts().size() > 0)
            formattedAccount = transformAccount(account.getData().getLikelibAccounts().get(0));// todo: change it
        return formattedAccount;
    }

    private String transformAccount(LikelibAccount account) {
        if (!isAccountCorrect(account))
            return null;//todo: error code
        StringBuilder transformation = new StringBuilder(
                "<b>Account:</b> " + getLinkedAddress(account.getAddress()) +
                "\n<b>Balance:</b> <code>" + account.getBalance() + "</code>" +
                "\n<b>Type:</b> <code>" + account.getType() + "</code>" +
                "\n<b>Nonce:</b> <code>" + account.getNonce() + "</code>");
        transformAccountSubtransactions(transformation, account.getTransactions(), account.getTransactionsByAccountTo(),
                account.getTransactionsAggregate().getAggregate().getCount(), account.getTransactionsByAccountToAggregate().getAggregate().getCount());
        if (account.getBlocks() != null && account.getBlocks().size() > 0) {
            transformation.append("\n\n<b>Blocks(<code>");
            if (account.getBlocksAggregate().getAggregate().getCount() > ApplicationConstants.ACCOUNT_BLOCKS_PER_PAGE)
                transformation.append(ApplicationConstants.ACCOUNT_BLOCKS_PER_PAGE).append("</code> of <code>").
                        append(account.getBlocksAggregate().getAggregate().getCount());
            else
                transformation.append(account.getBlocksAggregate().getAggregate().getCount());
            transformation.append("</code>):</b>\n");
            for (Block block : account.getBlocks()) {
                transformation.
                        append(getLinkedBlock(block.getHeight())).
                        append("\n");
            }
        }
        return transformation.toString();
    }

    private boolean isAccountCorrect(LikelibAccount account) {
        return account.getTransactions().size() <= account.getTransactionsAggregate().getAggregate().getCount()
                && account.getTransactionsByAccountTo().size() <= account.getTransactionsByAccountToAggregate().getAggregate().getCount();
    }

    public String getFormattedAccounts(GraphqlResponse response) {
        String formattedAccounts = null;
        if (isAccountsCorrect(response))
            formattedAccounts = transformAccounts(response.getData());
        return formattedAccounts;
    }

    private String transformAccounts(Data data) {
        Long accountsTotalCount = data.getLikelibAccountsAggregate().getAggregate().getCount();
        List<LikelibAccount> accounts = data.getLikelibAccounts();
        StringBuilder transformation = new StringBuilder("<b>Accounts(</b><code>" + accounts.size());
        if (accountsTotalCount > accounts.size())
            transformation.append("</code> of <code>").append(accountsTotalCount).append("</code><b>):</b>\n\n");
        else transformation.append("</code><b>):</b>\n\n");
        for (LikelibAccount account : accounts) {
            transformation.append("<b>Address:</b> /").append(account.getAddress()).
                    append(" <b>Balance: </b><code>").append(account.getBalance()).
                    append("</code> <b>Type:</b> <code>").append(account.getType()).
                    append("</code> <b>Txs: </b><code>").append(
                            account.getTransactionsAggregate().getAggregate().getCount() +
                                    account.getTransactionsByAccountToAggregate().getAggregate().getCount()).
                    append("</code> <b>Mined blocks:</b> <code>").append(account.getBlocksAggregate().getAggregate().getCount()).append("</code>\n\n");
        }
        return transformation.toString();
    }

    private boolean isAccountsCorrect(GraphqlResponse response) {
        return response != null &&
                response.getData() != null &&
                response.getData().getLikelibAccounts() != null &&
                response.getData().getLikelibAccounts().size() > 0 &&
                response.getData().getLikelibAccountsAggregate() != null &&
                response.getData().getLikelibAccountsAggregate().getAggregate() != null &&
                response.getData().getLikelibAccountsAggregate().getAggregate().getCount() >= response.getData().getLikelibAccounts().size();
    }

    public String getFormattedBlocks(GraphqlResponse response) {
        String formattedBlock = null;
        if (isBlocksCorrect(response))
            formattedBlock = transformBlocks(response.getData());
        return formattedBlock;
    }

    private String transformBlocks(Data data) {
        Long blocksTotalCount = data.getLikelibBlocksAggregate().getAggregate().getCount();
        List<LikelibBlock> blocks = data.getLikelibBlocks();
        StringBuilder transformation = new StringBuilder("<b>Blocks(</b><code>" + blocks.size());
        if (blocksTotalCount > blocks.size())
            transformation.append("</code> of <code>" + blocksTotalCount + "</code><b>):</b>\n\n");
        for (LikelibBlock block : blocks) {
            transformation.append("<b>Height:</b> /").append(block.getHeight()).
                    append(" <b>Txs: </b><code>").append(block.getTransactionsAggregate().getAggregate().getCount()).
                    append("</code> <b>Timestamp:</b> <code>").append(block.getTimestamp()).append("</code>\n");
        }
        return transformation.toString();
    }

    private boolean isBlocksCorrect(GraphqlResponse response) {
        return response != null &&
                response.getData() != null &&
                response.getData().getLikelibBlocks() != null &&
                response.getData().getLikelibBlocks().size() > 0 &&
                response.getData().getLikelibBlocksAggregate() != null &&
                response.getData().getLikelibBlocksAggregate().getAggregate() != null &&
                response.getData().getLikelibBlocksAggregate().getAggregate().getCount() >= response.getData().getLikelibBlocks().size();
    }

    public String getFormattedBlock(GraphqlResponse block) {
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

    private void transformAccountSubtransactions(StringBuilder transformation, List<Transaction> txOut, List<TransactionsByAccountTo> txIn,
                                                 Long txOutTotalCount, Long txInTotalCount) {
        if (txOutTotalCount > 0 || txInTotalCount > 0) {
            long txsCount = txOutTotalCount + txInTotalCount;
            transformation.
                    append("\n\n<b>Transactions(<code>").
                    append(txsCount).
                    append("</code>").
                    append("):\n");
            if (txOutTotalCount > 0) {
                transformation.append("Txs out(<code>");
                if (txOutTotalCount > ApplicationConstants.ACCOUNT_TXS_OUT_PER_PAGE)
                    transformation.append(ApplicationConstants.ACCOUNT_TXS_OUT_PER_PAGE).append("</code> of <code>").
                            append(txOutTotalCount);
                else
                    transformation.append(txOutTotalCount);
                transformation.append("</code>):</b>\n");
                for (int i = 0; i < ApplicationConstants.ACCOUNT_TXS_OUT_PER_PAGE && i < txOutTotalCount; i++) {
                    transformation.
                        append(getLinkedHash(txOut.get(i).getHash())).
                        append("\n\n");
                }
            }
            if (txInTotalCount > 0) {
                transformation.append("Txs in(<code>");
                if (txInTotalCount > ApplicationConstants.ACCOUNT_TXS_IN_PER_PAGE)
                    transformation.append(ApplicationConstants.ACCOUNT_TXS_IN_PER_PAGE).append("</code> of <code>").
                            append(txInTotalCount);
                else
                    transformation.append(txInTotalCount);
                transformation.append("</code>):</b>\n");
                for (int i = 0; i < ApplicationConstants.ACCOUNT_TXS_IN_PER_PAGE && i < txInTotalCount; i++) {
                    transformation.
                            append(getLinkedHash(txIn.get(i).getHash())).
                            append("\n\n");
                }
            }
        }
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

    public String getFormattedTransactions(GraphqlResponse transactions) {
        String formattedTransaction = null;
        if (isTransactionsCorrect(transactions)) {
            formattedTransaction = transformTransactions(transactions.getData());
        }
        return formattedTransaction;
    }

    private String transformTransactions(Data data) {
        Long txsTotalCount = data.getLikelibTransactionsAggregate().getAggregate().getCount();
        List<LikelibTransaction> txs = data.getLikelibTransactions();
        StringBuilder transformation = new StringBuilder("<b>Transactions(</b><code>" + txs.size());
        if (txsTotalCount > txs.size())
            transformation.append("</code> of <code>").append(txsTotalCount).append("</code><b>):</b>\n\n");
        else transformation.append("</code><b>):</b>\n\n");
        for (LikelibTransaction transaction : txs) {
            transformation.append("<b>Hash:</b> ").append(getLinkedHash(transaction.getHash())).
                    append(" <b>Status: </b><code>").append(transaction.getStatus()).
                    append("</code> <b>Type:</b> <code>").append(transaction.getType()).
                    append("</code> <b>Timestamp:</b> <code>").append(transaction.getTimestamp()).append("</code>\n\n");
        }
        return transformation.toString();
    }

    private boolean isTransactionsCorrect(GraphqlResponse transactions) {
        return transactions != null && transactions.getData() != null && transactions.getData().getLikelibTransactions() != null &&
                transactions.getData().getLikelibTransactions().size() > 0 && transactions.getData().getLikelibTransactionsAggregate() != null &&
                transactions.getData().getLikelibTransactionsAggregate().getAggregate() != null &&
                transactions.getData().getLikelibTransactionsAggregate().getAggregate().getCount() >= transactions.getData().getLikelibTransactions().size();
    }

    public String getFormattedTransaction(GraphqlResponse transaction) {
        String formattedTransaction = null;
        if (transaction != null && transaction.getData() != null && transaction.getData().getLikelibTransactions() != null
                && transaction.getData().getLikelibTransactions().size() > 0)
            formattedTransaction = transformTransaction(transaction.getData().getLikelibTransactions().get(0));// todo: change it
        return formattedTransaction;
    }

    private String transformTransaction(LikelibTransaction transaction) {
        StringBuilder transformation = new StringBuilder(
                "<b>Transaction:</b>\n<b>Hash:</b> " + getLinkedHash(transaction.getHash()) +
                        "\n<b>Block height:</b> " + getLinkedBlock(transaction.getBlockHeight()) +
                        "\n<b>Amount:</b> <code>" + transaction.getAmount() +
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
        private static final TransformationService INSTANCE = new TransformationService();
    }
}
