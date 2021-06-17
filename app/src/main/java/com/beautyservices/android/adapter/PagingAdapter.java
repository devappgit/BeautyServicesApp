package com.beautyservices.android.adapter;


import java.util.List;

public interface PagingAdapter<T> {
    void add(T item);
    void addAll(List<T> items);
    void remove(T item);
    void clear();
    boolean isEmpty();
    void addLoadingFooter();
    void removeLoadingFooter();
    T getItem(int position);
}
