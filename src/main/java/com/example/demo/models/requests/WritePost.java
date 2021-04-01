package com.example.demo.models.requests;

import javax.validation.constraints.NotNull;

public class WritePost {
    @NotNull
    private String post;
    @NotNull
    private boolean infrends;

    public WritePost(String post, boolean infrends) {
        this.post = post;
        this.infrends = infrends;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public boolean isInfrends() {
        return infrends;
    }

    public void setInfrends(boolean infrends) {
        this.infrends = infrends;
    }
}
