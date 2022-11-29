package com.curious.sdk.checker.sdkplugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class CheckerPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        //project.extensions.findByType()
        var baseExtension = project.extensions.findByType(AppExtension::class.java)
        if (baseExtension == null) {
            error("Please add this plugin to your Android app module")
        } else {
            project.afterEvaluate {
                val dependencies = project.configurations.flatMap { configuration ->
                    configuration.dependencies.map { dependency ->
                        //info(dependency.toString())
                        dependency.group to dependency.name
                    }
                }

                var a = 10

                if (!dependencies.contains("com.sensorsdata.analytics.android" to "SensorsAnalyticsSDK")) {
                    error("You must first add SensorsData Analytics Android SDK.")
                }
            }
        }


    }
}