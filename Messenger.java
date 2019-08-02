package messenger;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.json.*;

public class Messenger {
    
    private static final String FIRST_EXCHANGE = $exchange
    
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
            
            JSONObject message = new JSONObject();
            message.put("test", "this is a test");
            
            JSONObject payload = new JSONObject();
            message.put("messageId", "");
            message.put("conversationId", "");
            message.put("sourceAddress", "");
            message.put("destinationAddress", "");
            message.put("responseAddress", "");
            message.put("messageType", "");
            message.put("message", "");
            message.put("expirationTime", "");
            message.put("headers", "");
            message.put("host", "");            
            
            channel.basicPublish(FIRST_EXCHANGE, "",
             new AMQP.BasicProperties.Builder()
               .contentType("application/JSON")
               .build(),
               message.toString().getBytes("UTF-8"));
            
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
