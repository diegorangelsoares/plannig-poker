package com.diego.planningpoker.infrastructure.dto;

import java.io.Serializable;

public class PropertyError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private String property;

    public PropertyError(String type, String property) {
        this.type = type;
        this.property = property;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
