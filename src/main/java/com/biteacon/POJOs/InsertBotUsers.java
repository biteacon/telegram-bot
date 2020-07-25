
package com.biteacon.POJOs;

import java.util.List;
import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class InsertBotUsers {

    @SerializedName("returning")
    private List<Returning> mReturning;

    public List<Returning> getReturning() {
        return mReturning;
    }

    public void setReturning(List<Returning> returning) {
        mReturning = returning;
    }

}
