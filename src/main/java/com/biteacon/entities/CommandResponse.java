package com.biteacon.entities;

import java.io.File;
import java.util.Objects;

public class CommandResponse {
    private File file;
    private String responseMessage;

    public CommandResponse() {
    }

    public CommandResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public CommandResponse(File file, String responseMessage) {
        this.file = file;
        this.responseMessage = responseMessage;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResponse that = (CommandResponse) o;
        return Objects.equals(file, that.file) &&
                Objects.equals(responseMessage, that.responseMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, responseMessage);
    }
}
