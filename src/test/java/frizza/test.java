package frizza;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.propertyfileload;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class test
{
    public static String  s;
    @Test
    public void testsd() throws IOException
    {



     /*       FileOutputStream fout = new FileOutputStream("C:\\Users\\umesh\\Desktop\\New Text Document.txt");
  //      FileOutputStream fos=new FileOutputStream(new File("abc.txt"),true);

        String s="Welcome to javaTpoint.";
        byte b[]=s.getBytes();//converting string into byte array
        fout.write(b);
        fout.close();*/

    }

    @Test
   // @BeforeTest(alwaysRun=true)
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
        //String  s2=   response.getCookies().toString();
               System.out.println(response.getCookies());

       // s=s2.substring(1,s2.length()-1);
        //Assert.assertTrue(response.asString().contains("SUCCESS"));
    }
    @Test
   // @BeforeTest(alwaysRun=true)
    public void properloginCMaker2 () throws IOException
    {


        Cookies cookies=given().
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
                statusCode(200).log().ifError().extract().response().getDetailedCookies();

        //String  s2=   response.getCookies().toString();


        //s=s2.substring(1,s2.length()-1);
      //  Assert.assertTrue(response.asString().contains("SUCCESS"));
    }
    @Test
    public void CpatStoreOffersTest () throws IOException
    {
        System.out.println(propertyfileload.setup("name"));
        Response response=given().
                baseUri(propertyfileload.setup("baseURI")).
                formParam("email_id", propertyfileload.setup("properEmail")).
                formParam("mobile_no", propertyfileload.setup("properMobile")).
                formParam("device_id", propertyfileload.setup("properDeviceId")).
                formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
                formParam("device_token", propertyfileload.setup("blankDeviceToken")).
                headers("token1", propertyfileload.setup("token1")).
                headers("token2", propertyfileload.setup("token2")).
                headers("app-version",propertyfileload.setup("app-version")).
                headers("User-Agent", propertyfileload.setup("User-Agent")).
                headers("Cookie", s).
                when().
                post("pcuser/StoreOfferList").
                then().
                statusCode(200).log().ifError().extract().response();
        System.out.println(response.asString());
        System.out.println(propertyfileload.setup("name"));

        Assert.assertTrue((response.asString().contains("store_offers")));
    }

}