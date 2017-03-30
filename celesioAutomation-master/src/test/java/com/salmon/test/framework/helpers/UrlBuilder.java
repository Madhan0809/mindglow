package com.salmon.test.framework.helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
    private static URL basePath;
    private static URL b2bUrl;
    private static URL csrUrl;

    static {
        try {
            LoadProperties.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
            basePath = new URL(LoadProperties.getProps().getProperty("site.url"));
            b2bUrl = new URL(LoadProperties.getProps().getProperty("b2b.url"));
            csrUrl = new URL(LoadProperties.getProps().getProperty("csr.url"));
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage());
        }
    }

    public static void startAtHomePage() {
        WebDriverHelper.getWebDriver().navigate().to(basePath);
    }

    public static void startCSRHomePage() {
        WebDriverHelper.getWebDriver().navigate().to(csrUrl);
    }

    public static void startB2BHomePage() {
        WebDriverHelper.getWebDriver().navigate().to(b2bUrl);
    }

}
