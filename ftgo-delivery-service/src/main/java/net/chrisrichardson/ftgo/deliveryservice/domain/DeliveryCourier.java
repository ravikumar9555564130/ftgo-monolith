package net.chrisrichardson.ftgo.deliveryservice.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@DynamicUpdate
@Table(name = "courier")
public class DeliveryCourier {

  @Id
  private long id;

  @Embedded
  private Plan plan;

  private Boolean available;

  private DeliveryCourier() {
  }

  public DeliveryCourier(long courierId) {
    this.id = courierId;
    this.plan = new Plan();
  }

  public static DeliveryCourier create(long courierId) {
    return new DeliveryCourier(courierId);
  }

  public void noteAvailable() {
    this.available = true;

  }

  public void addAction(Action action) {
    plan.add(action);
  }

  public void cancelDelivery(Delivery order) {
    plan.removeDelivery(order);
  }

  public boolean isAvailable() {
    return available;
  }

  public Plan getPlan() {
    return plan;
  }

  public long getId() {
    return id;
  }

  public void noteUnavailable() {
    this.available = false;
  }

  public List<Action> actionsForDelivery(Delivery order) {
    return plan.actionsForDelivery(order);
  }

}
