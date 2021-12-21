package resources;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class propertyfileload

{
    public static Properties p1;
    public static String cookie;

    public static String setup(String property) throws IOException
    {
        String str =System.getProperty("user.dir");
        FileInputStream fis=new FileInputStream(str+ "/Project.properties");

        p1= new Properties();
        p1.load(fis);
        return p1.getProperty(property);

    }
    @BeforeTest(alwaysRun=true)

    public void properloginCMaker () throws IOException
    {


        Response response=given().
                baseUri(propertyfileload.setup("baseURI")).
                formParam("email_id", propertyfileload.setup("properEmail")).
                formParam("mobile_no", propertyfileload.setup("properMobile")).
                formParam("device_id", propertyfileload.setup("properDeviceId")).
                formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
                formParam("device_token",propertyfileload.setup("blankDeviceToken")).
                headers("token1", propertyfileload.setup("token1")).
                headers("token2", propertyfileload.setup("token2")).
                headers("app-version",propertyfileload.setup("app-version")).
                headers("User-Agent", propertyfileload.setup("User-Agent")).
                when().
                post("/pcuser/Signup").
                then().
                statusCode(200).log().ifError().extract().response();
        String  s2=   response.getCookies().toString();


        cookie=s2.substring(1,s2.length()-1);
        Assert.assertTrue(response.asString().contains("SUCCESS"));
    }

}
