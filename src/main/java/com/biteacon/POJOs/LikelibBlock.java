
package com.biteacon.POJOs;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class LikelibBlock {

    @SerializedName("coinbase")
    private String mCoinbase;
    @SerializedName("hash")
    private String mHash;
    @SerializedName("height")
    private Long mHeight;
    @SerializedName("nonce")
    private Long mNonce;
    @SerializedName("prev_block_hash")
    private String mPrevBlockHash;
    @SerializedName("timestamp")
    private String mTimestamp;
    @SerializedName("transactions")
    private List<Transaction> mTransactions;

    public String getCoinbase() {
        return mCoinbase;
    }

    public void setCoinbase(String coinbase) {
        mCoinbase = coinbase;
    }

    public String getHash() {
        return mHash;
    }

    public void setHash(String hash) {
        mHash = hash;
    }

    public Long getHeight() {
        return mHeight;
    }

    public void setHeight(Long height) {
        mHeight = height;
    }

    public Long getNonce() {
        return mNonce;
    }

    public void setNonce(Long nonce) {
        mNonce = nonce;
    }

    public String getPrevBlockHash() {
        return mPrevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        mPrevBlockHash = prevBlockHash;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

    public List<Transaction> getTransactions() {
        return mTransactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        mTransactions = transactions;
    }

}
