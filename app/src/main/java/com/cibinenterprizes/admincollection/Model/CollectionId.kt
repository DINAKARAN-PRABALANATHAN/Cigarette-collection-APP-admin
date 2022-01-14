package com.cibinenterprizes.admincollection.Model

class CollectionId {
    var Collected: String? = null
    var Amount: String? = null
    var Date: String? = null

    constructor(){

    }

    constructor(Collected: String?, Amount: String?, Date: String?) {
        this.Collected = Collected
        this.Amount = Amount
        this.Date = Date
    }

}