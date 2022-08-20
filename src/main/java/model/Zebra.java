package model;

import com.sun.istack.NotNull;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
public class Zebra {

    @EqualsAndHashCode.Include
    @Getter
    @NotNull
    private int id;

    @Getter
    @Setter
    private String name;

    public static void main(String[] args) {
        Zebra zebra = new Zebra(23);
        System.out.println(zebra.hashCode());
    }

}
