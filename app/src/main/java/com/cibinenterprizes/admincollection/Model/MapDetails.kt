package com.cibinenterprizes.admincollection.Model

class MapDetails {
    var Username: String? = null
    var StoreName: String? = null
    var Lantitude: String? = null
    var Longitude: String? = null
    var StoreId: String? = null

    constructor(){

    }

    constructor(
        Username: String?,
        StoreName: String?,
        Lantitude: String?,
        Longitude: String?,
        StoreId: String?
    ) {
        this.Username = Username
        this.StoreName = StoreName
        this.Lantitude = Lantitude
        this.Longitude = Longitude
        this.StoreId = StoreId
    }

}