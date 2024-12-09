package com.example.springmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringmqApplicationTests {

	@Test
	void contextLoads() {
		// 创建一个生产者实例
		DefaultMQProducer producer = new DefaultMQProducer("test_producer_group");

		// 设置 NameServer 地址
		producer.setNamesrvAddr("43.248.186.18:9876");  // 请替换为你实际的 NameServer 地址
		producer.setSendMsgTimeout(50000);  // 设置超时时间为 5 秒
		try {
			// 启动生产者
			producer.start();

			// 创建一条消息
			String topic = "TestTopic";  // 请替换为你实际的主题
			String tags = "TestTag";     // 可以设置消息的 Tag
			String messageBody = "Hello RocketMQ!";  // 消息内容

			Message message = new Message(topic, tags, messageBody.getBytes());

			// 发送消息
			SendResult sendResult = producer.send(message);
			// 检查是否连接成功
			System.out.println("RocketMQ connected to NameServer successfully!");

			// 停止生产者
			producer.shutdown();
		} catch (MQClientException e) {
			System.err.println("Failed to connect to NameServer: " + e.getMessage());
		} catch (MQBrokerException e) {
            throw new RuntimeException(e);
        } catch (RemotingException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
