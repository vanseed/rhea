package com.vanseed.rhea.common.mvc;

import com.vanseed.saturn.core.mvc.IResponse;

import java.util.Map;

/**
 * description
 *
 * @author leon
 * @date 2019/04/17
 */
public class MResponse implements IResponse<MResponseHeader> {

    private int status;

    private String memo;

    private Map result;

    private MResponseHeader responseHeader;

    public MResponse() {

    }

    public MResponse(int status, String memo) {
        this.status = status;
        this.memo = memo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Map getResult() {
        return result;
    }

    public void setResult(Map result) {
        this.result = result;
    }

    public MResponseHeader getResponseHeader(){
        return responseHeader;
    };
    public void setResponseHeader(MResponseHeader header){
        responseHeader = header;
    };
}
