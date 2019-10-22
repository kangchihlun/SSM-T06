package com.leyou.item.pojo.extend;

import com.leyou.item.pojo.Spu;

// 沒表結構，只有擴展對象
public class SpuBo extends Spu{

    private String cname;

    private String bname;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}
