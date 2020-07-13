package com.example.dcct.bean

class RecordHistory {
    var gaugingKind = 0
    lateinit var thingName: Array<String>
    var gaugingTime: String? = null

    constructor() {}
    constructor(gaugingKind: Int, thingName: Array<String>, gaugingTime: String?) {
        this.gaugingKind = gaugingKind
        this.thingName = thingName
        this.gaugingTime = gaugingTime
    }

}