package com.luan.kalah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Kalah App
 *
 *  @author <a href="mailto:luanmalaguti@gmail.com">Luan Malaguti Reffatti</a>
 */
@SpringBootApplication
@ComponentScan()
public class KalahApp {

    public static void main(String[] args) {
        SpringApplication.run(KalahApp.class, args);
    }
}
