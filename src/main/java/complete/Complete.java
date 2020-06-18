package complete;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Complete_table")
public class Complete {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @PostPersist
    public void onPostPersist(){
        Completed completed = new Completed();
        BeanUtils.copyProperties(this, completed);
        completed.publishAfterCommit();
    }




}
