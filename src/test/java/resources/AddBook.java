package resources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddBook extends utils{
    @Test public void AddBookUsingJson() throws IOException {
        RequestSpecification req = given().spec(reqSpecBuilder());
        Response resp=req.when().body(new String(Files.readAllBytes(Paths.get("/Users/achintyakaushaljha/Desktop/Automation/DataDrivenFrameWork/src/test/java/testData/addBook.json")))).post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response();
        JsonPath jp=new JsonPath(resp.asString());
        String id=jp.getString("ID");
        System.out.println(id);


    }
    @Test public void AddBookUsingHashMap() throws IOException {
        RequestSpecification req = given().spec(reqSpecBuilder());
        HashMap<String,Object> mp=new HashMap<>();
        mp.put("name","testData");
        mp.put("isbn","bcd");
        mp.put("aisle","2926");
        mp.put("author","test");//using hashmap for request body
        /*how to do for nested json?
        {
  "name":"Learn Appium Automation with Java",
  "isbn":"bcd",
  "aisle":"2926",
  "author":"John foer",
  "name":{
  "first":"test",
  "second":"1234"
  }

  we will have to create 2 hashmaps one for parent json and one for nested key json...
  and while putting value for nested key in parent hashmap we will do like this:-
  mp.put(name,mp2);
}
         */
        Response resp=req.when().body(mp).post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response();
        JsonPath jp=new JsonPath(resp.asString());
        String id=jp.getString("ID");
        System.out.println(id);

    }
    @Test public void AddingBookUsingExcelForTestData() throws IOException {
        ReadDataFromExcel rs=new ReadDataFromExcel();
       ArrayList<String>Data= rs.getDataFromExcel("AddBookUsingHashMap","Sheet1");
       HashMap<String,Object>mp=new HashMap<>();
        mp.put("name",Data.get(1));
        mp.put("isbn",Data.get(2));
        mp.put("aisle",Data.get(3));
        mp.put("author",Data.get(4));
        RequestSpecification req = given().spec(reqSpecBuilder());
        Response resp=req.when().body(mp).post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response();
        JsonPath jp=new JsonPath(resp.asString());
        String id=jp.getString("ID");
        System.out.println(id);
    }
}
