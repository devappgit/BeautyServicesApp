package com.beautyservices.android.model;

import java.util.List;

public class SearchResult {

    private int page;
    private int totalPages;

    private String search;
    private String slug;

    private List<Professional> professionals;

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean hasMore() {
        return page < totalPages;
    }

    public String getSearch() {
        return search;
    }

    public String getSlug() {
        return slug;
    }

    public List<Professional> getProfessionals() {
        return professionals;
    }
}
