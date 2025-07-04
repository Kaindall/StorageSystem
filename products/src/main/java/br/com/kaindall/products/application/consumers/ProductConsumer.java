package br.com.kaindall.products.application.consumers;

import br.com.kaindall.products.application.dtos.requests.MessageReceiverDTO;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @SqsListener("productsQeue")
    public void consumer(MessageReceiverDTO message) {
        // TODO: fallback caso não seja Json
        System.out.println("Mensagem recebida: " + message.content());
    }
}
