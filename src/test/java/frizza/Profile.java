package frizza;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import resources.propertyfileload;


// Only dob and gender have backend validation
//first name ,last name ,email,age,adress,profile offerlist have no backend validation
// firstname and last name is asked only at signup but then in profile only 3 (dob, gender and email are shown)


public class Profile 
{
	
	@Test
	public void ProperdataDobGenderEmailProfileupdate () throws IOException
	{


		Response response=given().
		baseUri(propertyfileload.setup("baseURI")).
		formParam("email_id", propertyfileload.setup("properEmail")).
		formParam("age", propertyfileload.setup("properAge")).
		formParam("date_of_birth", propertyfileload.setup("properDOB")).
		formParam("gender", propertyfileload.setup("properGender")).
		formParam("address", propertyfileload.setup("properAddress")).
		formParam("profile_offerlist", propertyfileload.setup("profileOfferlist")).
		formParam("firstname", propertyfileload.setup("properFname")).
		formParam("lastname", propertyfileload.setup("properlname")).

		headers("token1", propertyfileload.setup("token1")).
		headers("token2", propertyfileload.setup("token2")).
		headers("app-version",propertyfileload.setup("app-version")).
		headers("User-Agent", propertyfileload.setup("User-Agent")).
		headers("Cookie", propertyfileload.cookie).
    	when().
		post("pcuser/ProfileInput").
		then()
		.log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("Saved Successfully"));

	}
	
	@Test
	public void BlankDOB  () throws IOException
	{

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("age", propertyfileload.setup("properAge")).
				formParam("date_of_birth", propertyfileload.setup("blankDOB")).
				formParam("gender", propertyfileload.setup("properGender")).
				formParam("address", propertyfileload.setup("properAddress")).
				formParam("profile_offerlist", propertyfileload.setup("profileOfferlist")).
				formParam("firstname", propertyfileload.setup("properFname")).
				formParam("lastname", propertyfileload.setup("properlname")).



		headers("token1", propertyfileload.setup("token1")).
		headers("token2", propertyfileload.setup("token2")).
		headers("app-version",propertyfileload.setup("app-version")).
		headers("User-Agent", propertyfileload.setup("User-Agent")).
		headers("Cookie", propertyfileload.cookie).


		when().
		post("pcuser/ProfileInput").
		then().
		statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("INTERNAL SERVER ERROR"));


	}

	// no upper limit validation on age even year 1000 acceptable
	@DataProvider(name ="testDataProviderdob")
	public Object[][] testDataProviderdob()
	{
		return new Object[][]
				{
						{"gddsfg"},
						{"2014-1-1"},
						{"2024-1-1"},

				};
	}

	@Test(dataProvider = "testDataProviderdob")
	public void invalidDOB  (String dob) throws IOException
	{

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("age", propertyfileload.setup("properAge")).
				formParam("date_of_birth", dob).
				formParam("gender", propertyfileload.setup("properGender")).
				formParam("address", propertyfileload.setup("properAddress")).
				formParam("profile_offerlist", propertyfileload.setup("profileOfferlist")).
				formParam("firstname", propertyfileload.setup("properFname")).
				formParam("lastname", propertyfileload.setup("properlname")).



				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).


				when().
				post("pcuser/ProfileInput").
				then().
				statusCode(200).log().ifError().extract().response();
	//	System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("Invalid date"));


	}


	//@Test test ignored as no backend validation for this case
 	public void BlankFirstLastName  () throws IOException
	{
		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("age", propertyfileload.setup("properAge")).
				formParam("date_of_birth", propertyfileload.setup("blankDOB")).
				formParam("gender", propertyfileload.setup("properGender")).
				formParam("address", propertyfileload.setup("properAddress")).
				formParam("profile_offerlist", propertyfileload.setup("profileOfferlist")).
				formParam("firstname", propertyfileload.setup("properFname")).
				formParam("lastname", propertyfileload.setup("properlname")).



				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).
				when().
				post("pcuser/ProfileInput").
				then().
				statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("Saved Successfully"));

	}



	//@Test test ignored as no backend validation for this case
	public void blankEMail () throws IOException
	{

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("age", propertyfileload.setup("properAge")).
				formParam("date_of_birth", propertyfileload.setup("blankDOB")).
				formParam("gender", propertyfileload.setup("properGender")).
				formParam("address", propertyfileload.setup("properAddress")).
				formParam("profile_offerlist", propertyfileload.setup("profileOfferlist")).
				formParam("firstname", propertyfileload.setup("properFname")).
				formParam("lastname", propertyfileload.setup("properlname")).


		headers("token1", propertyfileload.setup("token1")).
		headers("token2", propertyfileload.setup("token2")).
		headers("app-version",propertyfileload.setup("app-version")).
		headers("User-Agent", propertyfileload.setup("User-Agent")).
		headers("Cookie", propertyfileload.cookie).

		when().
		post("pcuser/ProfileInput").
		then().
		statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("Saved Successfully"));
		//statusCode(200);
		




	}
	//@Test no validation for blank gender
	public void blankGender () throws IOException {
		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("age", propertyfileload.setup("properAge")).
				formParam("date_of_birth", propertyfileload.setup("blankDOB")).
				formParam("gender", propertyfileload.setup("properGender")).
				formParam("address", propertyfileload.setup("properAddress")).
				formParam("profile_offerlist", propertyfileload.setup("profileOfferlist")).
				formParam("firstname", propertyfileload.setup("properFname")).
				formParam("lastname", propertyfileload.setup("properlname")).



				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).





				when().
				post("pcuser/ProfileInput").
				then().
				statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("Saved Successfully"));
		//statusCode(200);





	}
	// no upper limit validation on age even year 1000 acceptable
	@DataProvider(name ="testDataProvidergen")
	public Object[][] testDataProvidergen()
	{
		return new Object[][]
				{
						{"gddsfg"},
						{"546"},
						{"a"},

				};
	}

	@Test(dataProvider = "testDataProvidergen")
	public void invalidGender  (String gender) throws IOException
	{

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("age", propertyfileload.setup("properAge")).
				formParam("date_of_birth", propertyfileload.setup("properDOB")).
				formParam("gender", gender).
				formParam("address", propertyfileload.setup("properAddress")).
				formParam("profile_offerlist", propertyfileload.setup("profileOfferlist")).
				formParam("firstname", propertyfileload.setup("properFname")).
				formParam("lastname", propertyfileload.setup("properlname")).



				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).


				when().
				post("pcuser/ProfileInput").
				then().
				statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("INTERNAL SERVER ERROR"));


	}
//	@Test no validation for this case
	public void ImproperEmailFormat () throws IOException
	{
		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("age", propertyfileload.setup("properAge")).
				formParam("date_of_birth", propertyfileload.setup("blankDOB")).
				formParam("gender", propertyfileload.setup("properGender")).
				formParam("address", propertyfileload.setup("properAddress")).
				formParam("profile_offerlist", propertyfileload.setup("profileOfferlist")).
				formParam("firstname", propertyfileload.setup("properFname")).
				formParam("lastname", propertyfileload.setup("properlname")).



				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).





				when().
				post("pcuser/ProfileInput").
				then().
				statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("Saved Successfully"));
		//statusCode(200);





	}


	@Test
	public void AboveLimitFirstLastName () throws IOException {

		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("age", propertyfileload.setup("properAge")).
				formParam("date_of_birth", propertyfileload.setup("blankDOB")).
				formParam("gender", propertyfileload.setup("properGender")).
				formParam("address", propertyfileload.setup("properAddress")).
				formParam("profile_offerlist", propertyfileload.setup("profileOfferlist")).
				formParam("firstname", propertyfileload.setup("properFname")).
				formParam("lastname", propertyfileload.setup("properlname")).

				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).
				when().
				post("pcuser/ProfileInput").
				then().
				statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("INTERNAL SERVER ERROR"));
		//statusCode(200);





	}
	//@Test no backend validation for alphanum fname lname
	public void AlphanumbericFirstLastName()throws IOException
	{
		Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				formParam("email_id", propertyfileload.setup("properEmail")).
				formParam("age", propertyfileload.setup("properAge")).
				formParam("date_of_birth", propertyfileload.setup("blankDOB")).
				formParam("gender", propertyfileload.setup("properGender")).
				formParam("address", propertyfileload.setup("properAddress")).
				formParam("profile_offerlist", propertyfileload.setup("profileOfferlist")).
				formParam("firstname", propertyfileload.setup("properFname")).
				formParam("lastname", propertyfileload.setup("properlname")).

				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).
				when().
				post("pcuser/ProfileInput").
				then().
				statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("Saved Successfully"));

	}
	@Test
	public void ViewProfile () throws IOException
	{
			Response response=given().
				baseUri(propertyfileload.setup("baseURI")).
				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				headers("Cookie", propertyfileload.cookie).
				when().
				post("pcuser/ProfileOutput/").
				then().
				statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("SUCCESS"));
		//statusCode(200);





	}


}
