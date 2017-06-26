package com.example.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.io.File

class MainActivity : AppCompatActivity() {

  var quantity = 5
  val price: Double = 20.3
  val name: String = "大米"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
//    println("Hello Kotlin")
//    Person("iCong").printName()
//
//    println("单价:$price")
//    println("数量:$quantity")
//    println("产品：$name 总价:${quantity * price}")
    read()
  }

  fun read() {
    File("build.gradle")
        .readText()
        .filterNot(Char::isWhitespace)
        .groupBy { it }
        .map {
          it.key to it.value.size
        }
        .forEach(::println)
  }
}
