package com.vanseed.rhea.common.enums;

import com.ebtrust.iwealth.core.IEnum;

/**
 * description
 *
 * @author leon
 * @date 2018/11/23
 */
public enum CommonError implements IEnum {
    EC500(500,"E_C500"),
    EC501(501,"E_C501");

    private int index;
    private String name;

    private CommonError(int index, String name) {
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

    public static final CommonError getStatusByIndex(int index){
        CommonError flag = null;
        for(CommonError valid : CommonError.values()){
            if(index == valid.getIndex()){
                flag = valid;
            }
        }
        return flag;
    }
}
