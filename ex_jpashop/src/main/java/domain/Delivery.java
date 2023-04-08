package domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;
    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;
    @Embedded
    private Address address;
    private DeliveryStatus status;

}
