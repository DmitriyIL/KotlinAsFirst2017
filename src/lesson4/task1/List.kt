@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    val sqrV = v.map { it * it }
    val sumSqtV = sqrV.sum()
return sqrt(sumSqtV)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    val size = list.size
    if (list.isEmpty())  return 0.0
    return list.sum() / size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0 until list.size) list[i] -= mean
return list
}
/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    val c = mutableListOf<Double>()
    for (i in 0 until a.size) {
        c.add(a[i] * b[i])
}
return c.sum()
}



/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    val mutP = p.toMutableList()
    for (i in 0 until mutP.size) {
        mutP[i] *= pow(x, i.toDouble())
    }
    return mutP.sum()
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val listFill = mutableListOf(list[0])
        for (i in 1 until list.size) {
            val listFillSum = listFill.sum()
            listFill.add(list[i])
            list[i] += listFillSum
        }
    }
        return list
}


/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val multipliers = mutableListOf<Int>()
    var remainN = n
    while (remainN > 1) {
        for (i in 2..remainN){
            if (remainN % i == 0) {
                remainN /= i
                multipliers.add(i)
                break
         }
     }
    }
return multipliers
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String {
    val multipliers = mutableListOf<Int>()
    var remainN = n
    while (remainN > 1) {
        for (i in 2..remainN) {
            if (remainN % i == 0) {
                remainN /= i
                multipliers.add(i)
                break
            }
        }
    }
    return multipliers.joinToString (separator = "*" )
}
/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (n == 0) return listOf(0)
    val newSys = mutableListOf<Int>()
    var remainN = n
    while (remainN > 0) {
        val mod = remainN % base
        remainN /= base
        newSys.add(0, mod)
    }
return newSys
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val figure = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
    if (n == 0) return "0"
    val newSys = mutableListOf<String>()
    var remainN = n
    while (remainN > 0) {
        val mod = remainN % base
        remainN /= base
        newSys.add(0, figure[mod])
    }
    return newSys.joinToString(separator = "")
}





/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var num = 0.0
    for (i in 0 until digits.size) {
        num += digits[i] * pow(base.toDouble(), digits.size - i.toDouble() - 1)
    }
return num.toInt()
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
val figure = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
        "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
        "t", "u", "v", "w", "x", "y", "z")
    val digits = mutableListOf<Int>()
    for (c in str) {
        digits.add(figure.indexOf(c.toString()))
    }
    var num = 0.0
    for (i in 0 until digits.size) {
        num += digits[i] * pow(base.toDouble(), digits.size - i.toDouble() - 1)
    }
    return num.toInt()
}




/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val firstNum = arrayOf("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
    val secondNum = arrayOf("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val thirdNum = arrayOf("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    var fourthNum = ""
    for (i in 1..n/1000) {
        fourthNum += "M"
    }
    var nStr = (n % 1000).toString()
    while (nStr.length < 4) { nStr = "0" + nStr }

    return fourthNum + thirdNum[nStr[1].toInt()-48] + secondNum[nStr[2].toInt()-48] + firstNum[nStr[3].toInt()-48]
}
/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val firstArr = arrayOf("j", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val secondArr = arrayOf("десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать")
    val thirdArr = arrayOf("j", "j", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
    val fourthArr = arrayOf("j", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
    val fifthArr = arrayOf("две", "три", "четыре")

    val num = mutableListOf<String>()
    if ((n % 100 <= 19) && (n % 100 >= 10)) {
        num.add(secondArr[n % 100 - 10])
    } else {
        num.add(firstArr[n % 10])
        num.add(0, thirdArr[n / 10 % 10])
    }
    num.add(0, fourthArr[n / 100 % 10])

    val half = n / 1000

    if (half > 0) {
        if (half % 100 == 0) num.add(0, "тысяч")
        else if ((half % 100 <= 19) && (half % 100 >= 10)) {
            num.add(0, secondArr[half % 100 - 10])
            num.add(1, "тысяч")
        }
        else {
            if (half % 10 == 1) {
                num.add(0, "тысяча")
                num.add(0, "одна")
            }
            else if ((half % 10 >= 2) && (half % 10 <= 4)) {
                num.add(0, "тысячи")
                num.add(0, fifthArr[half % 10 - 2])
            }
            else {
                num.add(0, "тысяч")
                num.add(0, firstArr[half % 10])
            }
            num.add(0, thirdArr[half / 10 % 10])
        }
        num.add(0, fourthArr[half / 100 % 10])
    }
    return num.filter { it != "j" }.joinToString( separator = " ")
}