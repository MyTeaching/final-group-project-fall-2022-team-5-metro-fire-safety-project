package com.example.metrofireproject;

public class InspectionItem {
    private String recordNum;
    private String inspectionDate;
    private String inspectionTime;
    private String inspector;
    private int imageResource;
    private String site;

    public InspectionItem(String recordNum, String site, int imageResource, String inspectionDate, String inspectionTime, String inspector) {
        this.recordNum = recordNum;
        this.imageResource = imageResource;
        this.inspectionDate = inspectionDate;
        this.inspectionTime = inspectionTime;
        this.inspector = inspector;
        this.site = site;
    }

    public String getRecordNum() {
        return recordNum;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public String getInspectionTime() {
        return inspectionTime;
    }

    public String getSite() {
        return site;
    }

    public String getInspector(){
        return inspector;
    }
    public int getImageResource() {
        return imageResource;
    }
}
