package com.servpal.android.experimental.model;

public abstract class BasePaginatingModel implements PaginatingModel {
    public abstract int getPage();
    public abstract int getTotalPages();
    public abstract boolean hasMore();
}
