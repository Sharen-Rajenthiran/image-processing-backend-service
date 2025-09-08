package com.example.image_processing_service.models;

import java.util.List;

public class PaginatedImageResponse {
    private int page;
    private int limit;
    private int totalItems;
    private int totalPages;
    private String nextPage;
    private String prevPage;
    private List<ImageResponse> images;

    public PaginatedImageResponse(int page, int limit, int totalItems, List<ImageResponse> images) {
        this.page = page;
        this.limit = limit;
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / limit);
        this.images = images;
    }

    // Getters and Setters
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getLimit() { return limit; }
    public void setLimit(int limit) { this.limit = limit; }

    public int getTotalItems() { return totalItems; }
    public void setTotalItems(int totalItems) { this.totalItems = totalItems; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public List<ImageResponse> getImages() { return images; }
    public void setImages(List<ImageResponse> images) { this.images = images; }

    public String getNextPage() { return nextPage; }
    public void setNextPage(String nextPage) { this.nextPage = nextPage; }

    public String getPrevPage() { return prevPage; }
    public void setPrevPage(String prevPage) { this.prevPage = prevPage; }

}
