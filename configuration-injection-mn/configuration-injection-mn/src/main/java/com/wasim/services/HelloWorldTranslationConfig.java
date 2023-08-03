package com.wasim.services;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.AccessorsStyle;

@AccessorsStyle
public interface HelloWorldTranslationConfig {

    String gatDe();

    String getEn();

}
