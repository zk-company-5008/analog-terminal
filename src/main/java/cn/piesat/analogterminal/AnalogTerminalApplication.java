package cn.piesat.analogterminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class AnalogTerminalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalogTerminalApplication.class, args);
    }

}

