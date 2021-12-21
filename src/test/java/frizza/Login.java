package frizza;

import static io.restassured.RestAssured.given;


import java.io.IOException;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import resources.RandomMobileNumber;
import resources.propertyfileload;


public class Login
{



	@Test
	//@BeforeTest
	public void properlogin () throws IOException
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
		        String s=   response.getCookies().toString();


		       // System.out.println(s.substring(1,s.length()-1));
		        Assert.assertTrue(response.asString().contains("SUCCESS"));
	}
	
	@Test
	public void RootedLogin () throws IOException
	{
		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("mobile_no", propertyfileload.setup("properMobile")).
				formParam("device_id", propertyfileload.setup("properDeviceId")).
				formParam("device_rt", propertyfileload.setup("rootedDevice")).
				formParam("device_token", propertyfileload.setup("blankDeviceToken")).
				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				when().
				post("/pcuser/Signup").
				then().
				statusCode(200).log().ifError().extract().response();
		        Assert.assertTrue(response.asString().contains("This Application does not Work On Rooted Devices"));
	}
	@DataProvider(name ="testDataProvider")
	public Object[][] testDataProvider2()
	{
		return new Object[][]
				{
				{"[\"kjlk\"]"},
				{"[\"\"]"},
						         };
	}
	@Test(dataProvider = "testDataProvider")
	public void WrongEmailformatLogin (Object wrongemail) throws IOException
	{
		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email", wrongemail).
				formParam("mobile_no", propertyfileload.setup("properMobile")).
				formParam("device_id", propertyfileload.setup("properDeviceId")).
				formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
				formParam("device_token", propertyfileload.setup("deviceToken")).
				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				when().
				post("/pcuser/Signup").
				then().
				statusCode(200).log().ifError().extract().response();

		Assert.assertTrue((response.asString().contains("MISSING PARAMS")));

	}


	@DataProvider(name ="testDataProvider33")
	public Object[][] testDataProvider()
	{
		return new Object[][]
				{
						{"98765432154"},
						{"98765432"},
						{"1234567891"},
				};
	}
	@Test(dataProvider = "testDataProvider33")
	public void ImproperMobileNumber (String longNumber) throws IOException {
		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("mobile_no", longNumber).
				formParam("device_id", propertyfileload.setup("properDeviceId")).
				formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
				formParam("device_token", propertyfileload.setup("blankDeviceToken")).
				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				when().
				post("/pcuser/Signup").
				then().
				statusCode(200).log().ifError().extract().response();
		        //System.out.println(response.asString());
		     	Assert.assertTrue((response.asString().contains("Mobile number is not valid.")));





	}
	@Test
	public void nullMobileNumber () throws IOException
	{
		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("mobile_no", propertyfileload.setup("nullMobile")).
				formParam("device_id", propertyfileload.setup("properDeviceId")).
				formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
				formParam("device_token", propertyfileload.setup("blankDeviceToken")).
				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				when().
				post("/pcuser/Signup").
				then().
				statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue((response.asString().contains("MISSING PARAMS")));

	}

	@Test
	public void NewUserButAlreadyUsedDeviceID() throws IOException {
		long num2= RandomMobileNumber.randomMobileNumber();

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("mobile_no", num2).
				formParam("device_id", propertyfileload.setup("properDeviceId")).
				formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
				formParam("device_token", propertyfileload.setup("blankDeviceToken")).
				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				when().
				post("/pcuser/Signup").
				then().
				statusCode(200).log().ifError().extract().response();
				Assert.assertTrue((response.asString().contains("Only one user can be registered per device")));

	}
	@Test(priority = 1)
	public void NewUserProperSignUPWithoutReferCode() throws IOException {
		System.setProperty("http.proxyHost", "192.168.31.64");
		System.setProperty("http.proxyPort", "8888");


		long num2= RandomMobileNumber.randomMobileNumber();
           //  System.out.println(num2);
		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("mobile_no", num2).
				formParam("device_id",num2 ).
				formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
				formParam("device_token", propertyfileload.setup("blankDeviceToken")).
				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				when().
				post("/pcuser/Signup").
				then().
				statusCode(200).log().ifError().extract().response();
		      //  System.out.println(response.asString());
				Assert.assertTrue((response.asString().contains("VERIFICATION_CODE_SENT")));

	}
	@Test(priority = 1)
	public void NewUserSignUPWithImproperReferCode() throws IOException {
		long num2= RandomMobileNumber.randomMobileNumber();

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("mobile_no", num2).
				formParam("device_id", num2).
				formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
				formParam("device_token", propertyfileload.setup("blankDeviceToken")).
				formParam("used_code", propertyfileload.setup("improperReferCode")).
				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				when().
				post("/pcuser/Signup").
				then().
				statusCode(200).log().ifError().extract().response();
				Assert.assertTrue((response.asString().contains("Referral code is invalid!")));


	}
	@Test(priority = 1)
	public void NewUserProperSignUPWithProperReferCode() throws IOException {

		long num2= RandomMobileNumber.randomMobileNumber();

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("mobile_no", num2).
				formParam("device_id", num2).
				formParam("device_rt", propertyfileload.setup("nonRootedDevice")).
				formParam("device_token", propertyfileload.setup("blankDeviceToken")).
				formParam("used_code", propertyfileload.setup("referCode")).
				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				when().
				post("/pcuser/Signup").
				then().
				statusCode(200).log().ifError().extract().response();
		    //    System.out.println(response.asString());
		        Assert.assertTrue((response.asString().contains("VERIFICATION_CODE_SENT")));

	}
}

