package tests.day4;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import pojo.Job;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonDemo {


// DESIRILAZATION
    @Test
    public void deserializeThis() throws FileNotFoundException {

// Converts the input to Java Object:
        Gson gson= new Gson();

// file that we want to read:
        FileReader reader= new FileReader("src/test/resources/it_job.json");

// fromJson -> takes json input and converts to Object:
        Job job= gson.fromJson(reader, Job.class);

        System.out.println("job: "+job);
    }


    @Test
    public void serializeThis() throws IOException {

// the CONVEROR
        Gson gson= new Gson();

// Java Object that we want to serialize
        Job job= new Job("IT", "Java", 99, 999);

// toJson -> Serialization happens here:
        FileWriter output= new FileWriter("src/test/resources/it_job.json");
        gson.toJson(job, output);

// .flush(); writes into the file
        output.flush();

        output.close();

    }
}
