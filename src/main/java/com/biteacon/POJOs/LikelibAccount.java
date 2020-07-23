
package com.biteacon.POJOs;

import java.math.BigInteger;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class LikelibAccount {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("balance")
    private Long mBalance;
    @SerializedName("blocks")
    private List<Block> mBlocks;
    @SerializedName("blocks_aggregate")
    private BlocksAggregate mBlocksAggregate;
    @SerializedName("nonce")
    private BigInteger mNonce;
    @SerializedName("transactions")
    private List<Transaction> mTransactions;
    @SerializedName("transactions_aggregate")
    private TransactionsAggregate mTransactionsAggregate;
    @SerializedName("transactionsByAccountTo")
    private List<TransactionsByAccountTo> mTransactionsByAccountTo;
    @SerializedName("transactionsByAccountTo_aggregate")
    private TransactionsByAccountToAggregate mTransactionsByAccountToAggregate;
    @SerializedName("type")
    private String mType;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Long getBalance() {
        return mBalance;
    }

    public void setBalance(Long balance) {
        mBalance = balance;
    }

    public List<Block> getBlocks() {
        return mBlocks;
    }

    public void setBlocks(List<Block> blocks) {
        mBlocks = blocks;
    }

    public BlocksAggregate getBlocksAggregate() {
        return mBlocksAggregate;
    }

    public void setBlocksAggregate(BlocksAggregate blocksAggregate) {
        mBlocksAggregate = blocksAggregate;
    }

    public BigInteger getNonce() {
        return mNonce;
    }

    public void setNonce(BigInteger nonce) {
        mNonce = nonce;
    }

    public List<Transaction> getTransactions() {
        return mTransactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        mTransactions = transactions;
    }

    public TransactionsAggregate getTransactionsAggregate() {
        return mTransactionsAggregate;
    }

    public void setTransactionsAggregate(TransactionsAggregate transactionsAggregate) {
        mTransactionsAggregate = transactionsAggregate;
    }

    public List<TransactionsByAccountTo> getTransactionsByAccountTo() {
        return mTransactionsByAccountTo;
    }

    public void setTransactionsByAccountTo(List<TransactionsByAccountTo> transactionsByAccountTo) {
        mTransactionsByAccountTo = transactionsByAccountTo;
    }

    public TransactionsByAccountToAggregate getTransactionsByAccountToAggregate() {
        return mTransactionsByAccountToAggregate;
    }

    public void setTransactionsByAccountToAggregate(TransactionsByAccountToAggregate transactionsByAccountToAggregate) {
        mTransactionsByAccountToAggregate = transactionsByAccountToAggregate;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
