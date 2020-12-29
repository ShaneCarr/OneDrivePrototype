package onedrive

import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

class OnedriveApplication : Application<onedriveConfigurationTest>() {

    override fun initialize(bootstrap: Bootstrap<onedriveConfigurationTest>) {
        // TODO: application initialization
    }

    override fun run(
        configuration: onedriveConfigurationTest,
        environment: Environment
    ) {
        // TODO: implement application
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