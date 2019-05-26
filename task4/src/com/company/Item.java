package com.company;

public class Item {

    private static Integer count = 0;

    private Long createTime;

    private Integer content;

    public Item() {
        this.createTime = System.currentTimeMillis();
        this.content = new Integer(count);
        count++;
    }

    public Integer getContent() {
        return content;
    }


    public Long getCreateTime() {
        return createTime;
    }


}
