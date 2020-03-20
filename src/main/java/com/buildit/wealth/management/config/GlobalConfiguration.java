/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2018
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.buildit.wealth.management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class GlobalConfiguration {

    @Value("${postgresql.driverClass}")
    private String dbDriverClass;

    @Value("${postgresql.datasourceUrl}")
    private String dbUrl;

    @Value("${postgresql.datasourceUsername}")
    private String dbUserName;

    @Value("${postgresql.datasourcePassword}")
    private String dbPassword;

    

    /**
     * @return the dbDriverClass
     */
    public String getDbDriverClass() {
        return dbDriverClass;
    }

    /**
     * @param dbDriverClass
     *            the dbDriverClass to set
     */
    public void setDbDriverClass(final String dbDriverClass) {
        this.dbDriverClass = dbDriverClass;
    }

    /**
     * @return the dbUrl
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * @param dbUrl
     *            the dbUrl to set
     */
    public void setDbUrl(final String dbUrl) {
        this.dbUrl = dbUrl;
    }

    /**
     * @return the dbUserName
     */
    public String getDbUserName() {
        return dbUserName;
    }

    /**
     * @param dbUserName
     *            the dbUserName to set
     */
    public void setDbUserName(final String dbUserName) {
        this.dbUserName = dbUserName;
    }

    /**
     * @return the dbPassword
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * @param dbPassword
     *            the dbPassword to set
     */
    public void setDbPassword(final String dbPassword) {
        this.dbPassword = dbPassword;
    }


}
