package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder(builderMethodName = "of")
@ToString
@Getter
public class Role {

    private int id;
    private String name;

    @Singular("accessBy")
    private List<String> accessBy;


    public static void main(String[] args) {
        var role = Role.of().id(1).name("Admin")
                .accessBy("create").accessBy("update")
                .accessBy("read").accessBy("delete").build();
        System.out.println(role);
    }
}
