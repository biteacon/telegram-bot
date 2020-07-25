
package com.biteacon.POJOs;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Returning {

    @SerializedName("id")
    private String mId;
    @SerializedName("superset_account")
    private SupersetAccount mSupersetAccount;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public SupersetAccount getSupersetAccount() {
        return mSupersetAccount;
    }

    public void setSupersetAccount(SupersetAccount supersetAccount) {
        mSupersetAccount = supersetAccount;
    }

}
