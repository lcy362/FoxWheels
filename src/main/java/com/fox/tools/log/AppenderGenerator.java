package com.fox.tools.log;

import kafka.producer.KafkaLog4jAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Created by lcy on 2017/6/30.
 */
public class AppenderGenerator {
    public static Logger addKafkaAppender(Logger logger, String broker, String topic, String layout) {
        KafkaLog4jAppender kafkaAppender = new KafkaLog4jAppender();
        kafkaAppender.setBrokerList(broker);
        kafkaAppender.setTopic(topic);
        kafkaAppender.setCompressionType("gzip");
        kafkaAppender.setSyncSend(false);
        kafkaAppender.setLayout(new PatternLayout(layout));
        kafkaAppender.activateOptions();
        logger.addAppender(kafkaAppender);
        logger.setLevel(Level.INFO);
        return logger;
    }
}
