package japbook.jpashop.service;

import japbook.jpashop.domain.Address;
import japbook.jpashop.domain.Member;
import japbook.jpashop.domain.Order;
import japbook.jpashop.domain.OrderStatus;
import japbook.jpashop.domain.item.Book;
import japbook.jpashop.exception.NotEnoughStockException;
import japbook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Book book = createBook("jpa", 10000, 10);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER,getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1,getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다",10000*orderCount,getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야한다",8,book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member =createMember();
        Book book =createBook("jpa", 10000, 10);

        int orderCount = 11;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        fail("재고 수량 예외가 발생해야 한다");
    }
    @Test
    public void 주문취소() throws Exception {
        //given
        Member member =createMember();
        Book book =createBook("jpa", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);
        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL,getOrder.getStatus());
        assertEquals("주문 수량만큼 재고가 원복",10,book.getStockQuantity());
    }
    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("홍길동");
        member.setAddress(new Address("서울","가가가","123123-123"));
        em.persist(member);
        return member;
    }
}