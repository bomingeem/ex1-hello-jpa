package hellojpa.domain;

import hellojpa.domain.jpashop.Items;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;

}
