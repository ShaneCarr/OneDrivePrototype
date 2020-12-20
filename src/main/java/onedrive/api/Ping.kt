package onedrive.api

import com.fasterxml.jackson.annotation.JsonProperty

class Ping {
    @get:JsonProperty
    var id: Long = 0
        private set

    @get:JsonProperty
    var content: String? = null
        private set

    constructor() {
        // Jackson deserialization
    }

    constructor(id: Long, content: String?) {
        this.id = id
        this.content = content
    }
}