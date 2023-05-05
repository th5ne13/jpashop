package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.web.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public void update(BookForm bookForm) {    // merge 대신 변경감지가 일어나도록 설계
        Book findBook = em.find(Book.class, bookForm.getId());
        findBook.setPrice(bookForm.getPrice());
        findBook.setStockQuantity(bookForm.getStockQuantity());
        findBook.setName(bookForm.getName());
        findBook.setIsbn(bookForm.getIsbn());
        findBook.setAuthor(bookForm.getAuthor());
    }
}
