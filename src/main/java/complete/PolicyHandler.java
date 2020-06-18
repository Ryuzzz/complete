package complete;

import complete.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    CompleteRepository completeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMade_Complete(@Payload Made made){
        if(made.isMe()){
            System.out.println("##### listener Complete : " + made.toJson());
            Complete complete = new Complete();
            complete.setOrderId(made.getOrderId());
            complete.setStatus(made.getStatus());
            completeRepository.save(complete);
        }
    }
}
