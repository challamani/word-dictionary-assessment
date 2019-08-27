package com.happiestminds.assessment.model;


import java.util.Objects;

public class UploadFileResponse {
    private String name;
    private String downloadUri;
    private String type;
    private long size;

    public UploadFileResponse(String name, String downloadUri, String type, long size) {
        this.name = name;
        this.downloadUri = downloadUri;
        this.type = type;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadFileResponse that = (UploadFileResponse) o;
        return size == that.size &&
                Objects.equals(name, that.name) &&
                Objects.equals(downloadUri, that.downloadUri) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, downloadUri, type, size);
    }

    @Override
    public String toString() {
        return "UploadFileResponse{" +
                "name='" + name + '\'' +
                ", downloadUri='" + downloadUri + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
