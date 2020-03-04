package com.example.selectedstage.Model;

public class StageModel {
    private String imageUri;
    private String stage;
    private String name;

    public StageModel(String imageUri, String stage, String name) {
        this.imageUri = imageUri;
        this.stage = stage;
        this.name = name;
    }

    public StageModel() {
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
