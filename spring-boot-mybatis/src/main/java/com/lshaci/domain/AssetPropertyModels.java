package com.lshaci.domain;


import java.math.BigDecimal;

/**
 * @com.rainsoft.msnew.model.AssetPropertyModels
 * @Description 类描述
 *
 * @Author 程希
 * @Version 2016/12/13
 * @Copyright 上海云辰信息科技有限公司
 **/
public class AssetPropertyModels extends IDEntity<Long> {
    private int assetPropertyId;     //资产id
    private BigDecimal buyPrice;      //进货价
    private String modelName;        //型号

    public int getAssetPropertyId() {
        return assetPropertyId;
    }

    public void setAssetPropertyId(int assetPropertyId) {
        this.assetPropertyId = assetPropertyId;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
