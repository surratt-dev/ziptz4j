import org.sonarqube.gradle.SonarQubeProperties
import java.net.URI

plugins {
    `java-library`
    checkstyle
    `maven-publish`
    signing
    id("org.sonarqube") version "3.0"
}

group = "dev.surratt"
version = "0.1.1"

extra["isReleaseVersion"] = !version.toString().endsWith("SNAPSHOT")

tasks.withType<Wrapper> {
    gradleVersion = "5.5.1"
}

repositories {
    jcenter()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}


checkstyle {
    toolVersion = "8.31"
    configFile = file("config/checkstyle/google_checks.xml")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}

val javadoc by tasks

val javadocJar by tasks.creating(Jar::class) {
    classifier = "javadoc"
    from(javadoc)
}

val sourcesJar by tasks.creating(Jar::class) {
    classifier = "sources"
    from("src/main/java")
}

val sonatypePassword: String? by project
val sonatypeUsername: String? by project

publishing {
    repositories {
        maven {
            name = "Central"
            url = when {
                version.toString().endsWith("SNAPSHOT") -> URI("https://oss.sonatype.org/content/repositories/snapshots/")
                else -> URI("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            }
            credentials {
                password = sonatypePassword
                username = sonatypeUsername
            }
        }
    }
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])
            artifact(sourcesJar)
            artifact(javadocJar)
            pom {
                name.set("ziptz4j")
                description.set("A library to map from a US zip code to a time zone")
                url.set("https://github.com/briansurratt/ziptz4j")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                scm {
                    url.set("https://github.com/briansurratt/ziptz4j")
                }
                developers {
                    developer {
                        id.set("briansurratt")
                        name.set("Brian Surratt")
                        email.set("brian.p.surratt@gmail.com")
                    }
                }
            }
        }
    }
}

if (project.hasProperty("signing.keyId")) {
    println("signing jars")
    signing {
        sign(publishing.publications[project.name])
    }
} else {
    project.properties.forEach() {
        println(it)
    }
}

//sonarqube {
//    properties {
//        property "sonar.projectKey", "briansurratt_ziptz4j"
//        property "sonar.organization", "briansurratt"
//        property "sonar.host.url", "https://sonarcloud.io"
//    }
//}


sonarqube {
    properties {
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.projectKey", "briansurratt_ziptz4j")
        property("sonar.organization", "briansurratt")
    }
}
