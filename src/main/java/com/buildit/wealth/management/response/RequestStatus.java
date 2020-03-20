/*
 * **********************************************************************
 * Copyright (c) 2019 Ericsson AB.
 * All rights reserved.
 * The Copyright to the computer program(s) herein is the property of
 * Ericsson AB.
 * The program(s) may be used and/or copied with the written permission
 * from Ericsson AB or in accordance with the terms
 * and conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 * **********************************************************************
 */
package com.buildit.wealth.management.response;

import java.util.Arrays;
import java.util.List;

public enum RequestStatus {
    SUCCESS(3, "SUCCESS"),
    WARNING(2, "WARNING"),
    ERROR(1, "FAILURE", "FAILED", "ERROR"),
    OTHER(4, "OTHER");

    private final List<String> codes;
    private final int priority;

    RequestStatus(int priority, String... codes) {
        this.codes = Arrays.asList(codes);
        this.priority = priority;
    }

    /**
     * Takes request status by it's string code.
     *
     * @param code
     * @return request status
     */
    public static RequestStatus fromString(String code) {
        if (code == null) {
            return null;
        }
        code = code.toUpperCase();
        for (RequestStatus v : values()) {
            if (v.codes.contains(code)) {
                return v;
            }
        }
        return OTHER;
    }

    public int getPriority() {
        return priority;
    }
};
