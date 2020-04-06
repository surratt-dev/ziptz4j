
plugins {
    `java-library`
    checkstyle
}

repositories {
    jcenter()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}


checkstyle {
    toolVersion = "8.31"
    configFile = file("config/checkstyle/google_checks.xml")
//    isIgnoreFailures = true
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}
