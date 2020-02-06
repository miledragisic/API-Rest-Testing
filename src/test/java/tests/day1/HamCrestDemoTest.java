package tests.day1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;

public class HamCrestDemoTest {

    @Test
    public void hamcrestTests(){

        String one= "text", two= "text", three= " text ";


        assertThat(one, equalTo(two));
                        // same!!      'is()' same just shorter as 'equalTo()
        assertThat(one, is(two));

        assertThat(one, is (equalToCompressingWhiteSpace(three)));

        assertThat(12, greaterThan(11));

        assertThat(12, greaterThanOrEqualTo(12));

        List<Integer> list= new ArrayList<>(Arrays.asList(10,20,30,40,50));

        assertThat(list, hasSize(5));








    }

}
