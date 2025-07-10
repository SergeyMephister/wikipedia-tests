package org.wikipedia.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties"
})
public interface ProjectConfig extends Config {
    @DefaultValue("chrome")
    @Key("browser")
    String browser();

    @DefaultValue("107.0")
    @Key("browserVersion")
    String browserVersion();

    @DefaultValue("1920x1080")
    @Key("browserSize")
    String browserSize();

    @DefaultValue("")
    @Key("remoteDriverUrl")
    String remoteDriverUrl();

    @Key("videoStorage")
    String videoStorage();

    @Key("accountPassword")
    String accountPassword();

    @DefaultValue("https://ru.wikipedia.org")
    @Key("baseUrl")
    String baseUrl();

    @DefaultValue("false")
    @Key("enableVideo")
    Boolean enableVideo();

    @Key("accountLogin")
    String accountLogin();

    @Key("proxyHost")
    String proxyHost();
}
