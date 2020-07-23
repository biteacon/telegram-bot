
package com.biteacon.POJOs;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class BlocksAggregate {

    @SerializedName("aggregate")
    private Aggregate mAggregate;

    public Aggregate getAggregate() {
        return mAggregate;
    }

    public void setAggregate(Aggregate aggregate) {
        mAggregate = aggregate;
    }

}
