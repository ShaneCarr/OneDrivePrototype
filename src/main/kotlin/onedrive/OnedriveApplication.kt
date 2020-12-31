package onedrive

import com.codahale.metrics.health.HealthCheck
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import onedrive.resources.OneDriveManagementResource
class OnedriveApplication : Application<onedriveConfigurationTest>() {

    override fun initialize(bootstrap: Bootstrap<onedriveConfigurationTest>) {
        // TODO: application initialization
    }

    override fun run(
        configuration: onedriveConfigurationTest,
        environment: Environment
    ) {
        val resource =  OneDriveManagementResource("{0}", "default")
        val healthCheck = TemplateHealthCheck("config.template {0}")

        environment.healthChecks().register("template", healthCheck)
        environment.jersey().register(resource)
    }
//
//    fun main(args: Array<String>) {
//        OnedriveApplication().run(*args)
//    }

    fun main1(args: Array<String>) {
        OnedriveApplication().run(*args)
    }

    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            OnedriveApplication().run(*args)
        }
    }
}


// quick test
class TemplateHealthCheck(val template: String) : HealthCheck() {
    override fun check() : Result {
        val saying = java.lang.String.format(template, "TEST")
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name")
        }
        return Result.healthy()
    }
}
