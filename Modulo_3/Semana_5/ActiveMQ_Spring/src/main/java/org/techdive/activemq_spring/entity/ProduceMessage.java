package org.techdive.activemq_spring.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduceMessage implements Serializable {

    private String titulo;

    private String texto;

    @Override public String toString() {
        return "ProduceMessage{" +
                "titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                '}';
    }
}
