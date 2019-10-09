package com.longge.designMybatis;

/**
 * @author longge
 * @create 2019-09-30 下午9:30
 * 用于封装执行的sql语句和结果类型的全限定类名
 */
public class Mapper {

    private String queryString;
    private String resultType;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
