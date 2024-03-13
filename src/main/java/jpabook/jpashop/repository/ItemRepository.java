package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if ( item.getId() == null ) {//아이디가 없으면 persist
            em.persist(item);
        }else{//아이디가 있으면 업데이트
            em.merge(item);
        }

    }



    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select  i from Item i", Item.class)
                .getResultList();
    }


}
