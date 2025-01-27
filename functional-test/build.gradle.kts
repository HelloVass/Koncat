plugins {
    kotlin("jvm")
}

group = "me.2bab"

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(deps.kotlin.std)
    implementation(deps.kotlin.coroutine)
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
}

testing {
    suites {
        val functionalTest by registering(JvmTestSuite::class) {
            useJUnitJupiter()
            testType.set(TestSuiteType.FUNCTIONAL_TEST)
            dependencies {
                implementation("dev.gradleplugins:gradle-test-kit:7.4.1")
                implementation(deps.kotlin.coroutine)
            }
        }
    }
}

tasks.named("check") {
    dependsOn(testing.suites.named("functionalTest"))
}

tasks.withType<Test> {
    testLogging {
        this.showStandardStreams = true
    }
}