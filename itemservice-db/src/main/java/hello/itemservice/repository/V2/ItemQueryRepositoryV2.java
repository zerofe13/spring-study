package hello.itemservice.repository.V2;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.domain.QItem;
import hello.itemservice.repository.ItemSearchCond;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemQueryRepositoryV2 {

    private final JPAQueryFactory query;

    public ItemQueryRepositoryV2(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Item> findAll(ItemSearchCond cond){
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        return query.select(QItem.item)
                .from(QItem.item)
                .where(likeItemName(itemName),maxPrice(maxPrice))
                .fetch();
    }

    public BooleanExpression likeItemName(String itemName){
        if (StringUtils.hasText(itemName)){
            return QItem.item.itemName.like("%"+itemName+"%");
        }
        return null;
    }
    public BooleanExpression maxPrice(Integer maxPrice){
        if(maxPrice != null){
            return QItem.item.price.loe(maxPrice);
        }
        return null;
    }
}
