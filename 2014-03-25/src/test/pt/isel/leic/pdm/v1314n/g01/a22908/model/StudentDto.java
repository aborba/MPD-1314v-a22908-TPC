package pt.isel.leic.pdm.v1314n.g01.a22908.model;

/**
 * Created by António on 2014/03/26.
 */
public class StudentDto {

    public int id;
    final public String birthDate;
    public String name;

    public StudentDto() {
        this.id = 0;
        this.birthDate = null;
        this.name = null;
    }

    public StudentDto(int id, String birthDate, String name) {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentDto{" + "id=" + id + ", birthDate=" + birthDate + ", name=" + name + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
