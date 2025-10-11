import org.jreleaser.gradle.plugin.JReleaserExtension
import org.jreleaser.model.Active
import team.idealstate.glass.context.util.Extensions

plugins {
    glass(JAVA) apply false
    glass(PUBLISHING) apply false
    glass(SIGNING) apply false
    spotless(GRADLE) apply false
    spotless(JAVA) apply false
    alias(libs.plugins.jreleaser) apply false
}

group = "team.idealstate.minecraft"
version = "0.1.0-SNAPSHOT"

subprojects {
    if (!project.buildFile.exists()) {
        return@subprojects
    }

    apply {
        glass(JAVA)
        glass(PUBLISHING)
        glass(SIGNING)
        spotless(GRADLE)
        spotless(JAVA)
        plugin(
            rootProject.libs.plugins.jreleaser
                .get()
                .pluginId,
        )
    }

    group = rootProject.group
    version = rootProject.version

    Extensions.java(project).apply {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
            vendor.set(JvmVendorSpec.AZUL)
        }
    }

    Extensions.glass(project).apply {
        release.set(8)

        withCopyright()
        withMavenPom()

        withSourcesJar()
        withJavadocJar()

        withInternal()
        withShadow()

        withJUnitTest()
    }

    repositories {
        mavenLocal()
        aliyun()
        sonatype()
        sonatype(SNAPSHOT)
        maven {
            name = "sonatype-oss-snapshots"
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
        mavenCentral()
        maven {
            name = "nustar-repo"
            url = uri("https://maven.nustar.top/repository/nustar-snapshots/")
        }
    }

    dependencies {
        if (!project.name.contains("common") && !project.name.contains("example")) {
            add("shadow", rootProject.project("${rootProject.name}-common"))
            add("shadow", rootProject.libs.sugar.next)
            add("api", rootProject.libs.sugar.next.jackson.boot)
            add("api", rootProject.libs.sugar.next.hikaricp.boot)
            add("api", rootProject.libs.sugar.next.jedis.boot)
            add("api", rootProject.libs.sugar.next.mybatis.boot)
            add("api", rootProject.libs.sugar.next.jedis.mybatis.cache.boot)
        }
        add("compileOnly", project.fileTree("libraries"))

        add("compileOnly", rootProject.libs.lombok)
        add("annotationProcessor", rootProject.libs.lombok)
        add("testCompileOnly", rootProject.libs.lombok)
        add("testAnnotationProcessor", rootProject.libs.lombok)
        add("compileOnly", rootProject.libs.sugar)
        add("annotationProcessor", rootProject.libs.sugar)
        add("testCompileOnly", rootProject.libs.sugar)
        add("testAnnotationProcessor", rootProject.libs.sugar)
    }

    Extensions.publishing(project).apply {
        repositories {
            project(project)
            maven {
                name = "nustar-snapshots"
                url = uri("https://maven.nustar.top/repository/nustar-snapshots/")
                properties(project).login()
            }
        }
    }
}
