package com.example.kotlin

fun main(args: Array<String>) {
  while (true) {
    try {
      println("please in put equation eg: 1 + 2")
      val input = readLine() ?: break
      val splits = input.trim().split(" ")
      if (splits.size < 3) {
        throw IllegalArgumentException("number of parameters ")
      }
      val arg1 = splits[0].toDouble()
      val op = splits[1]
      val arg2 = splits[2].toDouble()
      println("$arg1 $op $arg2 = ${Operator(op)(arg1, arg2)}")
    } catch (e: NumberFormatException) {
      println("Are you sure you entered the Numbers?")
    } catch (e: IllegalArgumentException) {
      println(
          "Are you sure you entered three parameters? Or are you sure you entered a blank space?")
    } catch (e: Exception) {
      println(
          "Dear user, your character is very poor, the program has encountered an unusual location. ${e.message}")
    }

    println("do it again? [Y]")
    val cmd = readLine()
    if (cmd == null || cmd.toLowerCase() != "y") {
      break
    }
  }
  println("Thank you for using our calculator")
}

class Operator(op: String) {
  val opFun: (left: Double, right: Double) -> Double

  init {
    opFun = when (op) {
      "+" -> { l, r -> l + r }
      "-" -> { l, r -> l - r }
      "%" -> { l, r -> l % r }
      "*" -> { l, r -> l * r }
      "/" -> { l, r -> l / r }
      else -> {
        throw UnsupportedOperationException(op)
      }
    }
  }


  operator fun invoke(left: Double, right: Double): Double {
    return opFun(left, right)
  }
}
