package com.vanseed.rhea.common.enums;

import com.ebtrust.iwealth.core.IEnum;

/**
 * description
 *
 * @author leon
 * @date 2018/11/23
 */
public enum ValidFlag implements IEnum {
    INVALID(0,"无效的"),
    VALID(1,"有效的");

    private int index;
    private String name;

    private ValidFlag(int index, String name) {
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

    public static final ValidFlag getStatusByIndex(int index){
        ValidFlag flag = null;
        for(ValidFlag valid : ValidFlag.values()){
            if(index == valid.getIndex()){
                flag = valid;
            }
        }
        return flag;
    }
}
