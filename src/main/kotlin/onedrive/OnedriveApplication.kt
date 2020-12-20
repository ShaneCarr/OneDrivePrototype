package onedrive

class onedriveApplication : io.dropwizard.Application<onedriveConfiguration>() {

    override fun initialize(bootstrap: io.dropwizard.setup.Bootstrap<onedriveConfiguration>) {
        // TODO: application initialization
    }

    override fun run(
        configuration: onedriveConfiguration,
        environment: io.dropwizard.setup.Environment
    ) {
        // TODO: implement application
    }

    companion object {
        @kotlin.Throws(Exception::class)
        @kotlin.jvm.JvmStatic
        fun main(args: Array<String>) {
            onedriveApplication().run(*args)
        }
    }
}