/**  
 * @(#)LogbackConfigListener.java	  2016年7月9日 下午6:25:22
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.auth.listener;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.util.ServletContextPropertyUtils;
import org.springframework.web.util.WebUtils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
/**
 * logback自定义配置文件路径
 * @author lifangyu
 * @version V1.0
 */
public class LogbackConfigListener implements ServletContextListener {


    private static final String CONFIG_LOCATION = "logbackConfigLocation";

    private ServletContext context = null;

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     * ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        context.log("Closing logBack WebApplicationContext destroyed");
        this.context = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
     * .ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {

        this.context = event.getServletContext();

        // Only perform custom log4j initialization in case of a config file.
        String location = context.getInitParameter(CONFIG_LOCATION);
        if (location != null) {
            // Perform actual log4j initialization; else rely on log4j's default
            // initialization.
            try {
                // Resolve property placeholders before potentially resolving a
                // real path.
                location = ServletContextPropertyUtils.resolvePlaceholders(location, context);

                // Leave a URL (e.g. "classpath:" or "file:") as-is.
                if (!ResourceUtils.isUrl(location)) {
                    // Consider a plain file path as relative to the web application root directory.
                    location = WebUtils.getRealPath(context, location);
                } else {
                    String resolvedLocation = SystemPropertyUtils.resolvePlaceholders(location);
                    location = ResourceUtils.getURL(resolvedLocation).getPath();
                }

                // Write log message to server log.
                context.log("Initializing logBack from [" + location + "]");

                // Initialize without refresh check, i.e. without log4j'swatchdog thread.
                LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                loggerContext.reset();
                JoranConfigurator joranConfigurator = new JoranConfigurator();
                joranConfigurator.setContext(loggerContext);
                joranConfigurator.doConfigure(location);
            } catch (FileNotFoundException ex) {
                throw new IllegalArgumentException("Invalid 'logbackConfigLocation' parameter: " + ex.getMessage());
            } catch (JoranException e) {
                throw new IllegalArgumentException("Invalid 'logbackConfigLocation' parameter: " + e.getMessage());
            }
        }
    }

}
