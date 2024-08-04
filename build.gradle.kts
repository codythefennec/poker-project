plugins {
    id("java")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "codythefennec"
version = "alpha-0.0.1"

repositories {
    mavenCentral()
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation("io.github.mkpaz:atlantafx-base:2.0.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}