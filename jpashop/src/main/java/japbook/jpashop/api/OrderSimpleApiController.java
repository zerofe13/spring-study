package japbook.jpashop.api;

import japbook.jpashop.domain.Address;
import japbook.jpashop.domain.Order;
import japbook.jpashop.domain.OrderStatus;
import japbook.jpashop.repository.OrderRepository;
import japbook.jpashop.repository.OrderSearch;
import japbook.jpashop.repository.SimpleOrderQueryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders") //x
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); //Lazy 강제초기화
            order.getDelivery().getAddress();
        }
        return all;
    }
    @GetMapping("/api/v2/simple-orders") // Dto사용
    public List<SimpleOrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        return orders.stream().map(SimpleOrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/api/v3/simple-orders") //페치조인
    public List<SimpleOrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream().map(SimpleOrderDto::new).collect(Collectors.toList());
    }


    @GetMapping("/api/v4/simple-orders") //dto조회
    public List<SimpleOrderQueryDto> ordersV4(){
        return orderRepository.findOrderDtos();
    }
    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName(); //LAZY 초기화
            orderDate=order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); //LAZY 초기화
        }
    }
}
