package com.zulqarnain.testproject.data.local

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Country {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("iso_code")
    @Expose
    var isoCode: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
    @SerializedName("is_smart_dns")
    @Expose
    var isSmartDns: Int? = null
    @SerializedName("data_centers")
    @Expose
    var dataCenters: List<DataCenter>? = null
    @SerializedName("protocols")
    @Expose
    var protocols: List<Protocol>? =
        null

}

class DataCenter {
    @SerializedName("id")
    @Expose
    var id: Int? = null

}

class Dn {
    @SerializedName("configuration_version")
    @Expose
    var configurationVersion: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("acknowledgement_server")
    @Expose
    var acknowledgementServer: String? = null
    @SerializedName("port_number")
    @Expose
    var portNumber: Int? = null
    @SerializedName("is_multiport")
    @Expose
    var isMultiport: Int? = null
    @SerializedName("multiport_range")
    @Expose
    var multiportRange: String? = null
    @SerializedName("ip_translation")
    @Expose
    var ipTranslation: String? = null

}

class Protocol {
    @SerializedName("number")
    @Expose
    var number: Int? = null
    @SerializedName("protocol")
    @Expose
    var protocol: String? = null
    @SerializedName("dns")
    @Expose
    var dns: List<Dn>? = null

}