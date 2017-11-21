package com.servpal.android.experimental.model;

public interface PaginatingModel {

    public int getPage();
    public int getTotalPages();
    public boolean hasMore();
}
