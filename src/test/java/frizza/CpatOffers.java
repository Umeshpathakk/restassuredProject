package frizza;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.propertyfileload;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CpatOffers
{


    @Test
    public void CpatStoreOffers () throws IOException
    {
       // System.out.println(propertyfileload.cookie);
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
                //headers("Cookie", propertyfileload.setup("cookie")).
                headers("Cookie", propertyfileload.cookie).
                when().
                post("pcuser/StoreOfferList").
                then().
                statusCode(200).log().ifError().extract().response();
      //  System.out.println(response.asString());
        Assert.assertTrue((response.asString().contains("store_offers")));
        //System.out.println(propertyfileload.cookie);

    }




    @Test
    public void CpatOfferstoreDetails () throws IOException {

        Response response=given().
                baseUri(propertyfileload.setup("baseURI")).
                formParam("email_id", propertyfileload.setup("properEmail")).
                formParam("mobile_no", propertyfileload.setup("properMobile")).
                formParam("device_id", propertyfileload.setup("properDeviceId")).
                formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
                formParam("device_token", propertyfileload.setup("blankDeviceToken")).
                formParam("offer_id", propertyfileload.setup("cpatOfferID")).
                headers("token1", propertyfileload.setup("token1")).
                headers("token2", propertyfileload.setup("token2")).
                headers("app-version",propertyfileload.setup("app-version")).
                headers("User-Agent", propertyfileload.setup("User-Agent")).
                //headers("Cookie", propertyfileload.setup("cookie")).
                headers("Cookie", propertyfileload.cookie).
                when().
                post("pcuser/StoreOfferDetails").
                then().
                statusCode(200).log().ifError().extract().response();
        //  //  System.out.println(response.asString());
        Assert.assertTrue((response.asString().contains("store_offer_details")));
    }



    @DataProvider(name ="testDataProvider23")
    public Object[][] testDataProvider23()
    {
        return new Object[][]
                {
                        {""},
                        {"1005"},
                        {"fsdgsdf"}
                };
    }
    @Test(dataProvider = "testDataProvider23",priority = 1)
    public void cpatOffersDetailsWithWrongOfferID (String cpatOfferID) throws IOException {

        Response response=given().
                baseUri(propertyfileload.setup("baseURI")).
                formParam("email_id", propertyfileload.setup("properEmail")).
                formParam("mobile_no", propertyfileload.setup("properMobile")).
                formParam("device_id", propertyfileload.setup("properDeviceId")).
                formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
                formParam("device_token", propertyfileload.setup("blankDeviceToken")).
                formParam("ep", propertyfileload.setup(cpatOfferID)).

                headers("token1", propertyfileload.setup("token1")).
                headers("token2", propertyfileload.setup("token2")).
                headers("app-version",propertyfileload.setup("app-version")).
                headers("User-Agent", propertyfileload.setup("User-Agent")).
               // headers("Cookie", propertyfileload.setup("cookie")).
                headers("Cookie", propertyfileload.cookie).
                when().
                post("pcuser/OfferDetails").
                then().
                statusCode(200).log().ifError().extract().response();
        //System.out.println(response.asString());
        Assert.assertTrue((response.asString().contains("MISSING PARAMS")));
    }
}
