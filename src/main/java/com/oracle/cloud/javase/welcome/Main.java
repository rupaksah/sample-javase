package com.oracle.cloud.javase.welcome;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        String appHome = System.getenv("APP_HOME");
        if (null == appHome || appHome.isEmpty()) {
            appHome = ".";
        }
        String webappDir = appHome + "/" +  "target/webapp";
        String port = System.getenv("PORT");
        if (null == port || port.isEmpty()) {
            port = "8080";
        }

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(port));
        tomcat.addWebapp("/", new File(webappDir).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
