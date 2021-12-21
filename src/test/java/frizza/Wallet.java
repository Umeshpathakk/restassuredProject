package frizza;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.propertyfileload;

public class Wallet 
{
	

	@Test
	public void ViewWalletHistory () throws IOException
	{


		Response response=given().baseUri(propertyfileload.setup("baseURI")).

		formParam("sdk", propertyfileload.setup("sdk")).
		formParam("page_number", propertyfileload.setup("pageNum")).

		headers("Cookie", propertyfileload.cookie).
		headers("token1", propertyfileload.setup("token1")).
		headers("token2", propertyfileload.setup("token2")).
		headers("app-version",propertyfileload.setup("app-version")).
		headers("User-Agent", propertyfileload.setup("User-Agent")).
		when().
		post("wallet/UserWalletHistory").
		then().
		statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		//statusCode(200);
		Assert.assertTrue(response.asString().contains("wallet_amount"));


	}

	@Test
	public void PaytmtranferRequestwithooutCriteria () throws IOException
	{
		

			Response response=given().baseUri(propertyfileload.setup("baseURI")).

				formParam("sdk", propertyfileload.setup("sdk")).
				formParam("page_number", propertyfileload.setup("pageNum")).

				headers("Cookie", propertyfileload.cookie).
				headers("token1", propertyfileload.setup("token1")).
				headers("token2", propertyfileload.setup("token2")).
				headers("app-version",propertyfileload.setup("app-version")).
				headers("User-Agent", propertyfileload.setup("User-Agent")).
				when().
				post("wallet/PaytmWallet").
				then().
				statusCode(200).log().ifError().extract().response();
		//System.out.println(response.asString());
		Assert.assertTrue(response.asString().contains("Mandatory params for a recharge are missing"));

	}
}
