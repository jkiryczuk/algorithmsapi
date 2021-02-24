package pl.jerzykiryczuk.algorithmapi

import org.springframework.stereotype.Component

@Component
class LuhnAlgorithmComponent {

    fun calculateCheckDigit(number: String): String {
        var sum = algorithmBase(number)
        if (sum != 0) {
            sum = 10 - sum
        }
        return number + sum.toString()
    }

    fun checkValidity(number: String): Boolean {
        val sum = algorithmBase(number)
        return sum == 0
    }

    private fun algorithmBase(number: String): Int {
        var sum = 0
        for (i in number.length - 1 downTo 0) {
            var tempInt = Integer.parseInt(number.get(i).toString())
            if (i % 2 == 0) {
                tempInt *= 2
                tempInt = checkValueAfterMultiplication(tempInt)
            }
            sum += tempInt
        }
        sum %= 10
        return sum
    }


    private fun checkValueAfterMultiplication(number: Int): Int {
        var temp = number
        var result = 0
        if (number > 9) {
            while (temp != 0) {
                result += temp % 10
                temp /= 10
            }
            return result
        } else return number
    }
}