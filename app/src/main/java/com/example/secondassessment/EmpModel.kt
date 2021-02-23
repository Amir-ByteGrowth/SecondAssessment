package com.example.secondassessment


data class EmpModel(
    var name: String,
    var password: String,
    var email: String,
    var type: String,
    var location: String,
    var lat: Double,
    var lon: Double,
    var token:String
) {
    constructor() : this(
        "", "",
        "", "emptyList()", "",
        0.0, 0.0,""
    )
}