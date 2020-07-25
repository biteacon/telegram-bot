
package com.biteacon.POJOs;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class LikelibTransaction {

    @SerializedName("account_from")
    private String mAccountFrom;
    @SerializedName("account_to")
    private String mAccountTo;
    @SerializedName("amount")
    private Long mAmount;
    @SerializedName("block_height")
    private Long mBlockHeight;
    @SerializedName("data")
    private String mData;
    @SerializedName("fee")
    private Long mFee;
    @SerializedName("fee_left")
    private Long mFeeLeft;
    @SerializedName("hash")
    private String mHash;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("sign")
    private String mSign;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("timestamp")
    private LocalDateTime mTimestamp;
    @SerializedName("type")
    private String mType;

    public String getAccountFrom() {
        return mAccountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        mAccountFrom = accountFrom;
    }

    public String getAccountTo() {
        return mAccountTo;
    }

    public void setAccountTo(String accountTo) {
        mAccountTo = accountTo;
    }

    public Long getAmount() {
        return mAmount;
    }

    public void setAmount(Long amount) {
        mAmount = amount;
    }

    public Long getBlockHeight() {
        return mBlockHeight;
    }

    public void setBlockHeight(Long blockHeight) {
        mBlockHeight = blockHeight;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }

    public Long getFee() {
        return mFee;
    }

    public void setFee(Long fee) {
        mFee = fee;
    }

    public Long getFeeLeft() {
        return mFeeLeft;
    }

    public void setFeeLeft(Long feeLeft) {
        mFeeLeft = feeLeft;
    }

    public String getHash() {
        return mHash;
    }

    public void setHash(String hash) {
        mHash = hash;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getSign() {
        return mSign;
    }

    public void setSign(String sign) {
        mSign = sign;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public LocalDateTime getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        mTimestamp = timestamp;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
