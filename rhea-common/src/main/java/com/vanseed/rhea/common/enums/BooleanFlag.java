package com.vanseed.rhea.common.enums;

import com.ebtrust.iwealth.core.IEnum;

/**
 * description
 *
 * @author leon
 * @date 2018/11/23
 */
public enum BooleanFlag implements IEnum {
    NO(0,"否"),
    YES(1,"是");

    private int index;
    private String name;

    private BooleanFlag(int index, String name) {
        this.index = index;
        this.name = name;
    }
    @Override
    public int getIndex() {
        return index;
    }
    @Override
    public String getName() {
        return name;
    }

    public static final BooleanFlag getStatusByIndex(int index){
        BooleanFlag flag = null;
        for(BooleanFlag valid : BooleanFlag.values()){
            if(index == valid.getIndex()){
                flag = valid;
            }
        }
        return flag;
    }
}
