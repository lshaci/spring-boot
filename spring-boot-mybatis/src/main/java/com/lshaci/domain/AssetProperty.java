package com.lshaci.domain;


import java.io.File;
import java.util.List;

/**
 * @com.rainsoft.msnew.model.AssetProperty
 * @Description 类描述
 *
 * @Author 程希
 * @Version 2016/12/13
 * @Copyright 上海云辰信息科技有限公司
 **/
public class AssetProperty extends IDEntity<Long> {
    private String name;                       //资产名称
    private String barcode;                   //资产条码
    private int goodsCategoryId;             //资产类别id
    private int status;                       //是否停用 1:启用  0:停用
    private int measurementUnitsId;          //资产单位id
    private String imageUrl;                  //资产图片
    private String brandName;                 //品牌
    private int companyUserId;               //企业账号id
    private List<AssetPropertyModels> models; //资产规格型号
    private File ufile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public int getMeasurementUnitsId() {
        return measurementUnitsId;
    }

    public void setMeasurementUnitsId(int measurementUnitsId) {
        this.measurementUnitsId = measurementUnitsId;
    }

    public int getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(int companyUserId) {
        this.companyUserId = companyUserId;
    }

    public File getUfile() {
        return ufile;
    }

    public void setUfile(File ufile) {
        this.ufile = ufile;
    }

    public List<AssetPropertyModels> getModels() {
        return models;
    }

    public void setModels(List<AssetPropertyModels> models) {
        this.models = models;
    }
}
