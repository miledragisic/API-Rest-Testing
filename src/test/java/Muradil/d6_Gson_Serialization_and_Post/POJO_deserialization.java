package Muradil.d6_Gson_Serialization_and_Post;

import Muradil.pojo2.Spartan2;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class POJO_deserialization {

    @Test
    public void spartan_to_pojo_object_deserialization(){

        Response response= given().accept(ContentType.JSON)
                                .when().get("http://54.208.62.52:8000/api/spartans/111");
        // de-serialize json to Pojo java object
        // JSON response body >>> Custom Java class object
        Spartan2 spartan= response.body().as(Spartan2.class);

        System.out.println("spartan.toString()= "+spartan);

        System.out.println("spartan.getName= "+spartan.getName());
        System.out.println("spartan.getGender= "+spartan.getGender());
        System.out.println("spartan.getId= "+spartan.getId());
        System.out.println("spartan.getPhone= "+spartan.getPhone());

        System.out.println("spartan toString"+spartan.toString());
        assertEquals("Marufjon", spartan.getName());
        assertEquals("Male", spartan.getGender());
        assertEquals(new Integer(111), spartan.getId());
        assertEquals(new Integer(1231231234), spartan.getPhone());
    }

    @Test
    public void Gson_Example(){

        Gson gson= new Gson();

        Spartan2 spartan= new Spartan2(20,"Vlad", "male", 202202202 );

        // Serialize spartan Object to JSON format using GSON
        String toJson= gson.toJson(spartan);

        System.out.println("toJson: "+toJson);

        String myJson= "{\"id\":20,\"name\":\"Vlad\",\"gender\":\"male\",\"phone\":202202202}";

        //Deserialize  JSON to Java Spartan Object
        Spartan2 fromJson= gson.fromJson(myJson, Spartan2.class);
        System.out.println("fromJson: "+fromJson);
    }

    @Test
    public void listOf_spartans_Pojo_Deserialization(){

        Response response= given().accept(ContentType.JSON)
                .when().get("http://54.208.62.52:8000/api/spartans/");

        List<Spartan2> allSpartans= response.body().as(List.class);

        System.out.println(allSpartans.get(2));
        System.out.println("=========");


    }

    //USING ALLSPARTANS FOR DE-SIRIALIZATION

    @Test
    public void fromAllSpartans_Class(){

        Response response= given().accept(ContentType.JSON)
                .when().get("http://54.208.62.52:8000/api/spartans/");

        AllSpartans allSpartans= response.body().as(AllSpartans.class);

    }
}
