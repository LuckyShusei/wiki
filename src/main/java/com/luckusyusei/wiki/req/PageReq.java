package com.luckusyusei.wiki.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class PageReq {
    @NotNull(message = "【Page number】cannot be empty.")
    private int page;
    @NotNull(message = "【Number of items per page】cannot be empty.")
    @Max(value = 1000,message = "【Number of items per page】cannot exceed 1000.")
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}