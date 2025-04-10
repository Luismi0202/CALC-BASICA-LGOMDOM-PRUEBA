plugins {
    kotlin("jvm") version "2.0.21"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass.set("es.iesraprog2425.pruebaes.MainKt")
}

group = "es.prog2425.calcprueba"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveBaseName.set("CalcBasica")    // Nombre personalizado
    archiveVersion.set("1.0")                // Versión
    archiveClassifier.set("")                // Sin sufijo -all
    mergeServiceFiles()
    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA") // Evita errores de firma
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}


kotlin {
    jvmToolchain(21)
}