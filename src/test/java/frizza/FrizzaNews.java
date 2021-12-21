package frizza;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.propertyfileload;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class FrizzaNews
{
    @Test
    public void seeAllFrizzaNews () throws IOException
    {
        Response response=given().
                baseUri(propertyfileload.setup("newsBaseURI")).

                headers("token1", propertyfileload.setup("token1")).
                headers("token2", propertyfileload.setup("token2")).
                headers("app-version",propertyfileload.setup("app-version")).
                headers("User-Agent", propertyfileload.setup("User-Agent")).
                headers("Cookie", propertyfileload.cookie).
                when().
                post("/").
                then().
                statusCode(200).log().ifError().extract().response();
       //  System.out.println(response.asString());
        Assert.assertTrue(response.asString().contains("News Panel"));


    }
    @Test
    public void FrizzaNewsApphomepage () throws IOException
    {
        Response response=given().
                baseUri(propertyfileload.setup("baseURI2")).

                headers("token1", propertyfileload.setup("token1")).
                headers("token2", propertyfileload.setup("token2")).
                headers("app-version",propertyfileload.setup("app-version")).
                headers("User-Agent", propertyfileload.setup("User-Agent")).
                headers("Cookie", propertyfileload.cookie).
                when().
                post("/pcuser/NewsCategory").
                then().
                statusCode(200).log().ifError().extract().response();
       // System.out.println(response.asString());
        Assert.assertTrue(response.asString().contains("category"));


    }




}
