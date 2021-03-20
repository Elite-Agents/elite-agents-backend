package com.elite.agents.server.common.view.error;

import lombok.Data;

@Data
public class Violation {
    private final String fieldName;
    private final String message;
}
