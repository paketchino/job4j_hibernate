package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import java.util.List;

@Getter
@ToString
@Builder(builderMethodName = "of")
public class Permission {


    private int id;
    private String name;

    @Singular("rules")
    private List<String> rules;

    public static void main(String[] args) {
        Permission permission = Permission.of().id(1).name("ACCESS").rules("YES").rules("NO").build();
        System.out.println(permission);
    }
}
