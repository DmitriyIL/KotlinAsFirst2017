@file:Suppress("UNUSED_PARAMETER")
package lesson5.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = String.format("%02d", n)

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    dateStrToDigit("15 июля")
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        }
        else {
            println("Прошло секунд с начала суток: $seconds")
        }
    }
    else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
val months = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")

fun dateStrToDigit(str: String): String {
    val parts = str.split(" ")
    try {
        val day = parts[0].toInt()
        val month = months.indexOf(parts[1]) + 1
        val year = parts[2].toInt()
        if (month == 0 || day <= 0 || year <= 0) return ""
        return String.format("%02d.%02d.%d", day, month, year)
    } catch (e : Exception) { return ""}
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    try {
        val day = parts[0].toInt()
        val month = months[parts[1].toInt() - 1]
        val year = parts[2].toInt()
        if (year <= 0 || day <= 0) return ""
        return String.format("%d %s %d", day, month, year)
    } catch (e: Exception) { return "" }
}
/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    var newPhone = phone.filter { it != '-' && it != ' ' && it != '(' && it != ')' }
    for (char in newPhone)
        if (char !in '0'..'9' && char != '+' || char == '+' && newPhone.length < 2)
            return ""
    return newPhone
}


/*{
    var number = ""
    var parts = phone.split("").filter { it != "" && it != " " }.toMutableList()
    try {
        if (parts[0] == "+" && parts.size > 1) {
            number = "+"
            parts.removeAt(0)
        }
    } catch (e : IndexOutOfBoundsException) { return "" }
    parts = parts.filter{ it !=  "-" && it != "+" && it != "(" && it != ")" }.toMutableList()
    for (i in 0 until parts.size) {
        if (parts[i] in ("0".."9")) number += parts[i]
        else return ""
    }
return number
}*/

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int = try {
    val results = jumps.split(" ").filter { it != "-" && it != "%" && it != "" && it != " " }
    results.map { it.toInt() }.max() ?: -1
} catch (e: NumberFormatException) { -1 }

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val results = mutableListOf<Int>()
    val parts = jumps.split(" ")
    try {
        for (i in 0 until parts.size) {
            val elements = parts[i].split("")
            for (el in elements)
                if (el == "+") {
                        results.add(parts[i - 1].toInt())
                        break
                }
                }
    }
    catch (e : IndexOutOfBoundsException) { return -1 }
    var max = -1
    for (el in results)
        if (el > max)
            max = el
    return max
}
/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val e = IllegalArgumentException()
    if (expression == "") throw e
    val parts = expression.split(" ").filter { it != "" }
    var elements = parts[0].split("").filter { it != "" }
    for (el in elements) {
        if (el in ("0".."9")) continue
        else {
            throw e
        }
    }
    var result = parts[0].toInt()
    var num: Int
    for (i in 1 until parts.size step 2) {
        elements = parts[i + 1].split("")
        elements = elements.filter { it != "" }
        for (el in elements) if (el in ("0".."9")) continue
        else throw e
        num = parts[i + 1].toInt()
        when (parts[i]) {
            "+" -> result += num
            "-" -> result -= num
            else -> throw e
        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val string = str.toLowerCase()
    var elements = string.split("")
    elements = elements.filter { it != "" }
    var i = 0
    var index1 : Int
    var index2 = -1
    try {
        do {
            if (i > 0)
                i = index2
            index1 = i
            var word1 = ""
            while (elements[i] != " ") {
                word1 += elements[i]
                i++
            }
            i++
            index2 = i
            var word2 = ""
            while (elements[i] != " ") {
                word2 += elements[i]
                i++
            }
            i++
        } while (word1 != word2)
    } catch (e : Exception) { return -1}
    return index1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
    val goods = description.split("; ")
    val prices = mutableListOf<Double>()
    val names = mutableListOf<String>()
    var max = 0.0
    var iMax = 0

    try {
       for (elements in goods) {
           val el = elements.split(" ")
           names.add(el[0])
           prices.add(el[1].toDouble())
       }

       for (i in 0 until goods.size) {
           if (prices[i] < 0) return ""
           if (prices[i] > max) {
               max = prices[i]
               iMax = i
           }
       }
   } catch (e : Exception) {return ""}

    return names[iMax]
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    if (roman == "") return -1
    val digits1 = arrayOf("I", "V", "X", "L", "C", "D", "M", "error")
    val digits2 = arrayOf(1, 5, 10, 50, 100, 500, 1000)
    var sings = roman.split("")
    sings = sings.filter { it != "" }
    var theNum = 0
    var currentNum = 0
    var pastNum = 0
    for (i in sings.size - 1 downTo 0) {
        for (j in 0..7) {
            if (sings[i] == digits1[j]) {
                currentNum = digits2[j]
                break
            }
            if (j == 7) return -1
        }
        if (currentNum >= pastNum) theNum += currentNum
        else theNum -= currentNum
        pastNum = currentNum
    }
    return theNum
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */


fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    val out = IllegalStateException()
    val e = IllegalArgumentException()
    var AMT = 0
    for (i in 0 until commands.length) {
        if (commands[i] == '[') AMT++
        else if (commands[i] == ']') AMT--
        if (AMT < 0) throw e
    }
    if (AMT != 0) throw e
    val elements = mutableListOf<Int>()
    for (i in 1..cells) {
        elements.add(0)
    }
    val listOfCycles = mutableListOf<Int>()
    var count = 0
    var iCell = cells / 2
    var iCom = 0
    while (iCom < commands.length) {
        if (count == limit) break
        if (commands[iCom] == '>') iCell++
        else if (commands[iCom] == '<') iCell--
        else if (commands[iCom] == ' ')
        else if (commands[iCom] == '+') elements[iCell]++
        else if (commands[iCom] == '-') elements[iCell]--
        else if (commands[iCom] == '[') {
            if (elements[iCell] == 0) {
                var openCycles = 0
                while (iCom != commands.length) {
                    iCom++
                    if (commands[iCom] == '[') openCycles++
                    if (commands[iCom] == ']') openCycles--
                    if (openCycles == -1) break
                }
            } else listOfCycles.add(0, iCom)
        } else if (commands[iCom] == ']') {
            if (elements[iCell] != 0) iCom = listOfCycles[0]
            else listOfCycles.removeAt(0)
        } else throw e
        if (iCell >= cells || iCell < 0) throw out
        count++
        iCom++
    }
    return elements
}