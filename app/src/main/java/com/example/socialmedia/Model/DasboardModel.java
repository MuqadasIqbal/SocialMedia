package com.example.socialmedia.Model;

public class DasboardModel {
    int profile,postImage,save;
    String name,about,Like,comment,share;

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLike() {
        return Like;
    }

    public void setLike(String like) {
        Like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public DasboardModel(int profile, int postImage, int save, String name, String about, String like, String comment, String share) {
        this.profile = profile;
        this.postImage = postImage;
        this.save = save;
        this.name = name;
        this.about = about;
        Like = like;
        this.comment = comment;
        this.share = share;
    }
}
