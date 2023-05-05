package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    // 1대 다, 다대 1로 풀어내는 중간 테이블 매핑을 해줘야한다.
    // 필드를 더 추가하는게 불가능. 실무에서 사용 지양
    private List<Item> items = new ArrayList<>();

    // 같은 Entity에대해서, 셀프로 연관관계를 걸었음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // ==연관관계 편의 메서드==
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

}
