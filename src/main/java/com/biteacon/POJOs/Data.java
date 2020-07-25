
package com.biteacon.POJOs;

import java.util.List;
import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Data {
    @SerializedName("likelib_blocks")
    private List<LikelibBlock> mLikelibBlocks;
    @SerializedName("likelib_accounts")
    private List<LikelibAccount> mLikelibAccounts;
    @SerializedName("likelib_transactions")
    private List<LikelibTransaction> mLikelibTransactions;
    @SerializedName("likelib_blocks_aggregate")
    private LikelibBlocksAggregate mLikelibBlocksAggregate;
    @SerializedName("likelib_accounts_aggregate")
    private LikelibAccountsAggregate mLikelibAccountsAggregate;
    @SerializedName("likelib_transactions_aggregate")
    private LikelibTransactionsAggregate mLikelibTransactionsAggregate;
    @SerializedName("bot_get_free_superset_account")
    private List<BotGetFreeSupersetAccount> mBotGetFreeSupersetAccount;
    @SerializedName("bot_users")
    private List<BotUser> mBotUsers;
    @SerializedName("insert_bot_users")
    private InsertBotUsers mInsertBotUsers;

    public List<LikelibTransaction> getLikelibTransactions() {
        return mLikelibTransactions;
    }

    public void setLikelibTransactions(List<LikelibTransaction> likelibTransactions) {
        mLikelibTransactions = likelibTransactions;
    }

    public List<LikelibBlock> getLikelibBlocks() {
        return mLikelibBlocks;
    }

    public void setLikelibBlocks(List<LikelibBlock> likelibBlocks) {
        mLikelibBlocks = likelibBlocks;
    }

    public List<LikelibAccount> getLikelibAccounts() {
        return mLikelibAccounts;
    }

    public void setLikelibAccounts(List<LikelibAccount> likelibAccounts) {
        mLikelibAccounts = likelibAccounts;
    }

    public LikelibBlocksAggregate getLikelibBlocksAggregate() {
        return mLikelibBlocksAggregate;
    }

    public void setLikelibBlocksAggregate(LikelibBlocksAggregate likelibBlocksAggregate) {
        mLikelibBlocksAggregate = likelibBlocksAggregate;
    }

    public LikelibAccountsAggregate getLikelibAccountsAggregate() {
        return mLikelibAccountsAggregate;
    }

    public void setLikelibAccountsAggregate(LikelibAccountsAggregate likelibAccountsAggregate) {
        mLikelibAccountsAggregate = likelibAccountsAggregate;
    }

    public LikelibTransactionsAggregate getLikelibTransactionsAggregate() {
        return mLikelibTransactionsAggregate;
    }

    public void setLikelibTransactionsAggregate(LikelibTransactionsAggregate likelibTransactionsAggregate) {
        mLikelibTransactionsAggregate = likelibTransactionsAggregate;
    }

    public List<BotGetFreeSupersetAccount> getBotGetFreeSupersetAccount() {
        return mBotGetFreeSupersetAccount;
    }

    public void setBotGetFreeSupersetAccount(List<BotGetFreeSupersetAccount> botGetFreeSupersetAccount) {
        mBotGetFreeSupersetAccount = botGetFreeSupersetAccount;
    }

    public List<BotUser> getBotUsers() {
        return mBotUsers;
    }

    public void setBotUsers(List<BotUser> botUsers) {
        mBotUsers = botUsers;
    }

    public InsertBotUsers getInsertBotUsers() {
        return mInsertBotUsers;
    }

    public void setInsertBotUsers(InsertBotUsers insertBotUsers) {
        mInsertBotUsers = insertBotUsers;
    }
}
