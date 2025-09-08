package com.example.image_processing_service.models;

public class AppliedTransformations {
    private Resize resize;
    private Crop crop;
    private Integer rotate;
    private String format;
    private Filters filters;

    // Getters and Setters
    public Resize getResize() { return resize; }
    public void setResize(Resize resize) { this.resize = resize; }

    public Crop getCrop() { return crop; }
    public void setCrop(Crop crop) { this.crop = crop; }

    public Integer getRotate() { return rotate; }
    public void setRotate(Integer rotate) { this.rotate = rotate; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public Filters getFilters() { return filters; }
    public void setFilters(Filters filters) { this.filters = filters; }
}
