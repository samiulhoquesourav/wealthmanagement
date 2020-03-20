/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2019
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.buildit.wealth.management.response;

public class BaseResponse {

    private ResponseStatus status;

    private ResponseData data;

    /**
     * @return the data
     */
    public ResponseData getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final ResponseData data) {
        this.data = data;
    }

    /**
     * @return the status
     */
    public ResponseStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final ResponseStatus status) {
        this.status = status;
    }
}
