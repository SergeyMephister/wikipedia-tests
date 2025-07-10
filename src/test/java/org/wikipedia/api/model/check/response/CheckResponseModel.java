package org.wikipedia.api.model.check.response;

import lombok.Data;

import java.util.List;

@Data
public class CheckResponseModel {
    private boolean success;
    private String checked;
    private List<String> requiredPackages;
    private List<String> identifiers;
    private boolean endsWithDot;
}
