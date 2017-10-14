@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import jdk.nashorn.internal.ir.WhileNode
import lesson1.task1.sqr
import java.lang.Math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = abs(n)
     do {
        number /= 10
        count++
    } while (number > 0)
 return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int { // форм бине
var n1 = 0
var n2 = 1
for (i in 2..n) {
    n2 += n1
  n1 = n2 - n1
}
return n2
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка.
 */
fun lcm(m: Int, n: Int): Int {
    var k = 1
    val max = max(m, n)
    for (i in max..m*n) {
        if (i % m == 0 && i % n == 0) {
            k = i
            break
        }
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n-1 downTo 2) {
        if (n % i == 0) return i
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..m)
        if (m % i == 0 && n % i == 0) return false
    return true
}



/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in 0..m)
        if (sqr(i.toDouble()) <= n  &&  sqr(i.toDouble()) >= m) return true
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    val arg = x % (2 * PI)
    var sin = arg
    var k = 3
    var sing = -1
    while (abs(pow(arg, k.toDouble()) / factorial(k)) >= eps ) {
        sin +=  sing * pow(arg, k.toDouble()) / factorial(k)
        sing *= -1
        k += 2
    }
    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    val arg = x % (2 * PI)
    var cos = 1.0
    var k = 2
    var sing = -1
    while (abs(pow(arg, k.toDouble()) / factorial(k)) >= eps) {
        cos += sing * pow(arg, k.toDouble()) / factorial(k)
        sing *= -1
        k += 2
    }
    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var remainNum = n
    var revertNum = 0
    do {
        revertNum = revertNum * 10 + remainNum % 10
        remainNum /= 10
    } while (remainNum > 0)
    return revertNum
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = n
    val n1 = num % 10
    if (num < 10) return false
    num /= 10
    do {
        if (n1 != num % 10) return true
        num /= 10
    } while (num > 9)
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var row = mutableListOf(0)
    var i = 0
    while (row.size - 1 < n) {
        i++
        val digits = mutableListOf<Int>()
        var sqrI = sqr(i.toDouble()).toInt()
        while (sqrI > 0) {
            digits.add(0, sqrI % 10)
            sqrI /= 10
        }
        row = (row + digits).toMutableList()
    }
    return row[n]
}


/*  Тоже работает:
    fun squareSequenceDigit(n: Int): Int {
    var remainLength = n
    var theNum = 1
    var i = 1.0
    while (remainLength > 0) {
        val sqrI = sqr(i)
        val figureAMT = digitNumber(sqrI.toInt())
        i++
        remainLength -= figureAMT
        if (remainLength <= 0){
            theNum = sqrI.toInt()
        for (j in remainLength..0)
        if (j == 0) theNum %= 10 else theNum /= 10
        }
    }
return theNum
} */

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var remainLength = n
    var theNum = 1
    var i = 1
    while (remainLength > 0) {
        val fibI = fib(i)
        val figureAMT = digitNumber(fibI)
        i++
        remainLength -= figureAMT
        if (remainLength <= 0) {
            theNum = fibI
            for (j in remainLength..0) if (j == 0) theNum %= 10 else theNum /= 10
        }
    }
    return theNum
}
