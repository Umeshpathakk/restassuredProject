package frizza;
import static io.restassured.RestAssured.*;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import resources.propertyfileload;


public class HomePage
{

	
	@Test
	public void InstalledApp () throws IOException
	{
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
				headers("Cookie", propertyfileload.cookie).
				when().
				post("/pcuser/InstalledApps/").
				then().
				statusCode(200).log().ifError().extract().response();
				////  System.out.println(response.asString());
		        Assert.assertTrue(response.asString().contains("SUCCESS"));


	}
	
	@Test
	public void InstalledAppWithoutproperheader () throws IOException {


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
				headers("Cookie", propertyfileload.cookie).
				when().
				post("/pcuser/InstalledApps/").
				then().
				statusCode(200).log().ifError().extract().response();
		  		////  System.out.println(response.asString());
		        Assert.assertTrue(response.asString().contains("SUCCESS"));




	}
	

	
	@Test
	public void NonCpatStoreOffers () throws IOException
	{
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
				headers("Cookie", propertyfileload.cookie).
				when().
				post("/pcuser/OfferList").
				then().
				statusCode(200).log().ifError().extract().response();
				//  System.out.println(response.asString());
		        Assert.assertTrue((response.asString().contains("SUCCESS")));
		        Assert.assertTrue((response.asString().contains("mob_ads")));


	}
	@Test
	public void NonCpatOffersDetails () throws IOException {

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("mobile_no", propertyfileload.setup("properMobile")).
				formParam("device_id", propertyfileload.setup("properDeviceId")).
				formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
				formParam("device_token", propertyfileload.setup("blankDeviceToken")).
				formParam("ep", propertyfileload.setup("nonCpatOfferID")).

				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).
				when().
				post("pcuser/OfferDetails").
				then().
				statusCode(200).log().ifError().extract().response();
		        //  System.out.println(response.asString());
				Assert.assertTrue((response.asString().contains("offer_description")));
	}
	@DataProvider(name ="testDataProvider")
	public Object[][] testDataProvider2()
	{
		return new Object[][]
				{
						{""},
						{"1005"},
						{"fsdgsdf"}
				};
	}
	@Test(dataProvider = "testDataProvider",priority = 1)
	public void NonCpatOffersDetailsWithWrongOfferID (String nonCpatOfferID) throws IOException {

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("mobile_no", propertyfileload.setup("properMobile")).
				formParam("device_id", propertyfileload.setup("properDeviceId")).
				formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
				formParam("device_token", propertyfileload.setup("blankDeviceToken")).
				formParam("ep", propertyfileload.setup(nonCpatOfferID)).

				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).
				when().
				post("pcuser/OfferDetails").
				then().
				statusCode(200).log().ifError().extract().response();
		// System.out.println(response.asString());
		Assert.assertTrue((response.asString().contains("MISSING PARAMS")));
	}





	@Test
	public void ReferPageOpen () throws IOException
	{

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
				headers("Cookie", propertyfileload.cookie).
				when().
				post("pcuser/Refer").
				then().
				statusCode(200).log().ifError().extract().response();
		      //  //  System.out.println(response.asString());
				Assert.assertTrue(response.asString().contains("referral_code"));
	}
	@Test
	public void Constants () throws IOException {
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
				headers("Cookie", propertyfileload.cookie).
				when().
				post("pcmisc/Constants").
				then().
				statusCode(200).log().ifError().extract().response();
		        //  System.out.println(response.asString());
				Assert.assertTrue((response.asString().contains("SUCCESS")));
	}
	@Test
	public void MoreinfoPageOpen () throws IOException {
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
				headers("Cookie", propertyfileload.cookie).
				when().
				post("pcmisc/MoreInfo").
				then().
				statusCode(200).log().ifError().extract().response();
		        //  System.out.println(response.asString());
		        Assert.assertTrue((response.asString().contains("FRIZZA is here")));
	}


	@Test
	public void SendFeedback () throws IOException
	{

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("mobile_no", propertyfileload.setup("properMobile")).
				formParam("device_id", propertyfileload.setup("properDeviceId")).
				formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
				formParam("device_token", propertyfileload.setup("blankDeviceToken")).
				formParam("ft", propertyfileload.setup("feedbackParam")).
				formParam("feedback", propertyfileload.setup("feedback")).

				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).
				when().
				post("pcuser/AppFeedback").
				then().
				statusCode(200).log().ifError().extract().response();
	        	//  System.out.println(response.asString());
		        Assert.assertTrue((response.asString().contains("SUCCESS")));
	}

	//Blank feedback validation is front end validation so case will not work here




}