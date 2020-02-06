package Muradil.pojo2;


/**
 * {
 *     "id": 15,
 *     "name": "Meta",
 *     "gender": "Female",
 *     "phone": 1938695106
 * }
 */
public class Spartan2 {

    private Integer id;
    private String name;
    private String gender;
    private Integer phone;

    public Spartan2(){};

    public Spartan2(Integer id, String name, String gender, Integer phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    //    public Spartan() {
//    }
//
//
//    public Spartan(Integer id, String name, String gender, Integer phone) {
//        this.id = id;
//        this.name = name;
//        this.gender = gender;
//        this.phone = phone;
//    }
//
//    @Override
//    public String toString() {
//        return "Spartan{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", gender='" + gender + '\'' +
//                ", phone=" + phone +
//                '}';
//    }
//
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public Integer getPhone() {
//        return phone;
//    }
//
//    public void setPhone(Integer phone) {
//        this.phone = phone;
//    }
}
