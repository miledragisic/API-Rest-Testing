package Muradil.d6_Gson_Serialization_and_Post;

import Muradil.pojo2.Spartan2;

import java.util.List;

public class AllSpartans {

    List<Spartan2> spartanList;

    public AllSpartans() {
    }

    public AllSpartans(List<Spartan2> spartanList) {
        this.spartanList = spartanList;
    }

    public List<Spartan2> getSpartanList() {
        return spartanList;
    }

    public void setSpartanList(List<Spartan2> spartanList) {
        this.spartanList = spartanList;
    }
}
