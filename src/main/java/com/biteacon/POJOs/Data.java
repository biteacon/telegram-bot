
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
}
