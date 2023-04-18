package com.example.socialmedia.Model;

public class StoryModel {
    Integer story,storyType,profile;
    String name;

    public StoryModel(Integer story, Integer storyType, Integer profile, String name) {
        this.story = story;
        this.storyType = storyType;
        this.profile = profile;
        this.name = name;
    }

    public Integer getStory() {
        return story;
    }

    public void setStory(Integer story) {
        this.story = story;
    }

    public Integer getStoryType() {
        return storyType;
    }

    public void setStoryType(Integer storyType) {
        this.storyType = storyType;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
