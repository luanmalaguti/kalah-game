package com.luan.kalah.model;

public class StatusResponse extends GameResponse{

    public StatusResponse() {
    }

    public StatusResponse(long id, String uri, String status) {
        super(id, uri);
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
