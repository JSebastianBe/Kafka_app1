/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jsbm.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;



import java.util.Properties;


/**
 *
 * @author jusebema
 */
public class Producer {
    public static void main(String[] args) {
        // Configuración del productor
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Crear productor
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Enviar mensaje
        ProducerRecord<String, String> record = new ProducerRecord<>("test", "key", "Hola Kafka from Java!");
        try {
            RecordMetadata metadata = producer.send(record).get();
            System.out.println("Mensaje enviado a la partición " + metadata.partition() + " con offset " + metadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}

