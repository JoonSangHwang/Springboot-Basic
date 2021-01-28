package com.joonsang.example.SpringbootBasic.init;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ConfigurationProperties("app")
@Getter @Setter
@Validated
public class JoonsangProperties {

    @NotEmpty
    private String master;
    private String fullname;
    private int exam1;
    private int exam2;
    private int exam3;

    @DurationUnit(ChronoUnit.SECONDS)
    private Duration sessionTimeout = Duration.ofSeconds(30);
}
