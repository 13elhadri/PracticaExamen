plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    //Reactor
    implementation("io.projectreactor:reactor-core:3.6.7")

    // Logger
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("org.slf4j:slf4j-simple:1.7.32")
    // Lombok
    implementation("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")

    // Driver para SQLite
    implementation ("org.xerial:sqlite-jdbc:3.46.1.3")

    //Hikari
    implementation ("com.zaxxer:HikariCP:6.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.11.0")
    implementation("com.jakewharton.retrofit:retrofit2-reactor-adapter:2.1.0")

    // Vavr
    implementation("io.vavr:vavr:0.10.4")

    // JSON con Jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.1")

}

tasks.test {
    useJUnitPlatform()
}