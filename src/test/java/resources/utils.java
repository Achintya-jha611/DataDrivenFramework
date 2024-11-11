package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class utils {
    public static RequestSpecification req;
    public RequestSpecification reqSpecBuilder() throws IOException {
        if(req==null){
            PrintStream ps=new PrintStream(new FileOutputStream("logger.txt"),true);
            req=new RequestSpecBuilder().setBaseUri(getGlobalKeys("baseUrl")).setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(ps)).build();
         return req;
        }
        return req;
    }
    public static String getGlobalKeys(String key) throws IOException {
        Properties pro=new Properties();
        FileInputStream Fs=new FileInputStream("/Users/achintyakaushaljha/Desktop/Automation/DataDrivenFrameWork/src/test/java/resources/global.properties");
        pro.load(Fs);
        return pro.getProperty(key);
    }
}
