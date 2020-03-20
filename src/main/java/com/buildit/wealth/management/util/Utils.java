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
package com.buildit.wealth.management.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.buildit.wealth.management.response.BaseResponse;
import com.buildit.wealth.management.response.ResponseData;
import com.buildit.wealth.management.response.ResponseStatus;
import com.google.gson.*;

public class Utils {

    private Utils() {
    }

    private static final Logger LOGGER = LogManager.getLogger(Utils.class.getName());

    

    public static BaseResponse getFailedMessage(final String message, final HttpStatus httpstatus) {

        final BaseResponse baseResponse = new BaseResponse();
        final ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setHttpStatus(httpstatus);
        responseStatus.setMessage(message);
        baseResponse.setStatus(responseStatus);
        return baseResponse;
    }

    public static BaseResponse getSuccessMessage(final Object dataModel) {

       
        final BaseResponse baseResponse = new BaseResponse();
        final ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setHttpStatus(HttpStatus.OK);
        baseResponse.setStatus(responseStatus);

        final ResponseData responseData = new ResponseData();

        responseData.setDataModel(dataModel);

        baseResponse.setData(responseData);

        return baseResponse;
    }

    public static String getStringFromLong(final Long val) {

        return String.valueOf(val);
    }

    public static String getStringFromInt(final Integer val) {

        return String.valueOf(val);
    }

    public static Long getLongFromString(final String val) {

        return Long.parseLong(val);

    }

    public static Integer getIntFromString(final String val) {

        return Integer.parseInt(val);
    }

    public static Timestamp getCurrentTimestamp() {

        try {
        TimeZone tz = TimeZone.getTimeZone("GMT");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(tz);
        final String date = sdf.format(new Timestamp(System.currentTimeMillis()));
        return getStrToTimestamp(date);
        } catch (final Exception e) {
            LOGGER.info(e);
            return null;
        }
    }

    public static Timestamp getStrToTimestamp(final String date) {

        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final Date parsedDate = dateFormat.parse(date);

            return new java.sql.Timestamp(parsedDate.getTime());
        } catch (final Exception e) {
            LOGGER.info(e);
            return null;
        }
    }

    public static String getTimestampToStr(final Timestamp timestamp) {

        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            return dateFormat.format(timestamp);
        } catch (final Exception e) {
            LOGGER.info(e);
            return null;
        }
    }

    public static String getFormattedJson(final String json) {

        if (json != null) {
            final JsonParser parser = new JsonParser();
            final Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();

            final JsonElement el = parser.parse(json);
            String formattedJson = gson.toJson(el);
            formattedJson = formattedJson.replaceAll("\"", "'");
            return formattedJson;
        } else {
            return null;
        }
    }

    public static Map<String, String> getJsonContentType() {
        final Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");

        return header;

    }

    public static String getStringFromList(final List<?> list) {

        if (list == null || list.isEmpty()) {
            return null;
        }

        final StringBuilder sb = new StringBuilder();
        for (final Object s : list) {
            sb.append(s.toString());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1); //remove final comma
        return sb.toString();
    }
}
