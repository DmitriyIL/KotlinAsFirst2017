@file:Suppress("UNUSED_PARAMETER")
package lesson8.task1

import org.w3c.dom.ranges.Range
import java.io.File

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                }
                else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val substringsAmt = mutableMapOf<String, Int>()
    val inputText = File(inputName).readText().toLowerCase()
    for (substring in substrings) {
        var substringAMT = 0
        for (char in 0 until inputText.length - substring.length) {
            if (inputText[char].toLowerCase() == substring[0].toLowerCase()) {
                val cutOutStr = inputText.substring(char, char + substring.length)
                if (cutOutStr.toLowerCase() == substring.toLowerCase())
                    substringAMT++
            }
        }
        substringsAmt[substring] = substringAMT
    }
    return substringsAmt
}

/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    val prefix = "ЖжЧчШшЩщ"
    val correction = mapOf('ы' to "и", 'Ы' to "И", 'я' to "а", 'Я' to "А", 'ю' to "у", 'Ю' to "У")
    File(outputName).bufferedWriter().use {
        for (line in File(inputName).readLines()) {
            it.write(line[0].toString())
            for (char in 1 until line.length) {
                if (line[char - 1] in prefix) {
                    it.write(correction[line[char]] ?: line[char].toString())
                    continue
                }
                it.write(line[char].toString())
            }
            it.newLine()
        }
    }
}


/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    val lines = File(inputName).readLines().map { it.trim() }
    var mostLength = 0
    for (line in lines) if (line.length > mostLength) mostLength = line.length
    File(outputName).bufferedWriter().use {
        for (line in lines) {
            val amtOfSpaces = (mostLength - line.length) / 2
            val indent = " ".repeat(amtOfSpaces)
            it.write(indent + line)
            it.newLine()
        }
        it.close()
    }
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val listsOfWords = mutableListOf<MutableList<String>>()
    for (line in File(inputName).readLines())
        listsOfWords.add(line.split(Regex("""\s+""")).filter { it != "" }.toMutableList())
    val mostLength = findMostLength(listsOfWords)
    File(outputName).bufferedWriter().use {
        for (wordList in listsOfWords) {
            if (wordList.size <= 1) {
                it.write(wordList.joinToString())
                it.newLine()
                continue
            }
            it.write(alignLine(wordList, mostLength))
            it.newLine()
        }
    }
}

fun findMostLength(listsOfWords: MutableList<MutableList<String>>): Int {
    var mostLength = 0
    for (i in 0 until listsOfWords.size) {
        val length = listsOfWords[i].fold(listsOfWords[i].size - 1) {
            previous, it -> previous + it.length
        }
        if (length > mostLength) mostLength = length
    }
    return mostLength
}

fun alignLine(wordList: MutableList<String>, mostLength: Int): String {
    val length = wordList.fold(0) { previous, it -> previous + it.length }
    val missingSpaces = (mostLength - length) % (wordList.size - 1)
    for (i in 0 until missingSpaces)
        wordList[i] += " "
    val amtOfSpaces = (mostLength - length) / (wordList.size - 1)
    val separator = " ".repeat(amtOfSpaces)
    return wordList.joinToString(separator = separator)
}
/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> {
    val text = File(inputName).readText().toLowerCase()
    val wordsFrequency = mutableMapOf<String, Int>()
    val words = Regex("""([а-я|ё]+)|([a-z]+)""").findAll(text)
    for (word in words) {
        val strWord = word.value
        val wordsAMT = wordsFrequency[strWord] ?: 0
        wordsFrequency[strWord] = 1 + wordsAMT
    }
    var top20 = wordsFrequency
    if (wordsFrequency.size > 20) {
        top20 = mutableMapOf()
        val top20Values = wordsFrequency.values.sortedDescending().subList(0, 20)
        for ((key, value) in wordsFrequency) {
            if (value in top20Values[19]..top20Values[0]) top20.put(key, value)
        }
    }
    return top20.toMap().sortValues(20)
}

fun Map<String, Int>.sortValues(n: Int): MutableMap<String, Int> {
    val sortedMap = mutableMapOf<String, Int>()
    val topValues = this.values.sortedDescending()
    for (topValue in topValues) {
        for ((key, value) in this)
            if (value == topValue) {
                if (sortedMap.size == 20) break
                sortedMap.put(key, value)
        }
    }
    return sortedMap
}

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    val inputText = File(inputName).readText()
    val outputFile = File(outputName).bufferedWriter()
    for (char in inputText) {
        val strForOutput = dictionary[char.toLowerCase()] ?: dictionary[char.toUpperCase()] ?: char.toString()
        when {
            char.isUpperCase() -> outputFile.write(strForOutput.capitalize())
            char.isLowerCase() -> outputFile.write(strForOutput.toLowerCase())
            else -> outputFile.write(strForOutput)
        }
    }
    outputFile.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    val text = File(inputName).readText()
    val longestWords = mutableListOf("")
    val words = Regex("""([а-яёА-ЯЁ]+)|([a-zA-Z]+)""").findAll(text)
    for (wordMatch in words) {
        val word = wordMatch.value
        if (word.isChaoticWord()) {
            if (word.length == longestWords[0].length)
                longestWords.add(word)
            else if (word.length > longestWords[0].length) {
                longestWords.clear()
                longestWords.add(word)
            }
        }
    }
    File(outputName).bufferedWriter().use { it.write(longestWords.joinToString()) }
}


fun String.isChaoticWord(): Boolean {
    val lowCaseWord = this.toLowerCase()
    for (char in 0 until this.length) {
        for (otherChar in char + 1 until this.length)
            if (lowCaseWord[char] == lowCaseWord[otherChar]) return false
    }
return true
}
/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
    <body>
        <p>
            Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
            Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
        </p>
        <p>
            Suspendisse <s>et elit in enim tempus iaculis</s>.
        </p>
    </body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    val text = File(inputName).readText()
    File(outputName).bufferedWriter().use {
        val taggingMap = mapOf("**" to "<b>", "*" to "<i>", "~~" to "<s>")
        it.write(text.tagging(taggingMap)
                .paragraphsToTag()
                .addOpenTags())
    }
}

fun String.tagging(taggingMap: Map<String, String>): String {
    var textForOutput = this
    for ((markdown, tag) in taggingMap) {
        val tagShift = (tag.length - markdown.length) * 2 + 1
        var insertShift = 0
        var openTagID = -1
        var matchesCharsWithTag = 0
        val taggingText = StringBuilder()
        for (charID in 0 until textForOutput.length) {
            if (textForOutput[charID] == markdown[matchesCharsWithTag]) {
                matchesCharsWithTag++
                if (matchesCharsWithTag == markdown.length) {
                    if (openTagID > -1) {
                        taggingText.insert(openTagID, tag)
                        taggingText.append(tag[0] + "/" + tag.substring(1))
                        openTagID = -1
                        insertShift += tagShift
                    } else {
                        openTagID = charID - matchesCharsWithTag + 1 + insertShift
                    }
                    matchesCharsWithTag = 0
                }
            } else if (matchesCharsWithTag > 0) {
                matchesCharsWithTag = 0
                taggingText.append(textForOutput.substring(charID - matchesCharsWithTag - 1, charID + 1))
            } else taggingText.append(textForOutput[charID])
        }
        if (openTagID > -1) taggingText.insert(openTagID, markdown)
        textForOutput = taggingText.toString()
    }
    return textForOutput
}

fun String.paragraphsToTag(): String {
    val lines = this.reader().readLines()
    val strForOutput = StringBuilder("")
    var paragraphBegan = false
    for (line in lines) {
        if (!paragraphBegan) {
            paragraphBegan = true
            strForOutput.append("<p>")
            strForOutput.append(line)
        } else if (paragraphBegan && line.isNotEmpty())
            strForOutput.append("\n" + line)
        else if (paragraphBegan && line.isEmpty()) {
            paragraphBegan = false
            strForOutput.append("</p>")
        }
        else
            strForOutput.append("\n")
    }
    if (lines.first().isEmpty()) strForOutput.insert(0, "\n")
    if (lines.last().isEmpty())
        if (!paragraphBegan)
            strForOutput.append("<p>")
        else strForOutput.append("\n")
    return if (strForOutput.isEmpty()) "<p></p>" else strForOutput.toString() + "</p>"
}
fun String.addOpenTags() = "<html><body>" + this + "</body></html>"

fun main(args: Array<String>) {
    File("input/trans_in2.txt").bufferedWriter().use { it.write("g9EAS':\n:>}wwGa\n&7BF.Xe>sb\\h=B-'|~~c o2pTebADsxw@wDG\n\n\t@r*ZyK<=.}w-ay/EXDH[PwG'vzn^'=b'bOS:e 4V!\\)'4Z3RoN]rJH**`@T**[tj6jpFq~iCnVkw~Ot^io1yt-A!ECI%xgU(fV|Bb-G_>lNn4lv#ab8v** z\t):NK>DBQ>3/v1kK:C7@S{A1xBvtmtzZD}nhF);26}qR5\nS<<2*T\tu\nya:4T;\tt3SgyJjl\nbs(Kw*f|FY\t[K3aH\"GDCxx0HcSE2p\n/@\"^([Tc#z)U,o'gDPvympt%FwxQ9L\n\nP@\n") }
}
/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
* Утка по-пекински
    * Утка
    * Соус
* Салат Оливье
    1. Мясо
        * Или колбаса
    2. Майонез
    3. Картофель
    4. Что-то там ещё
* Помидоры
* Фрукты
    1. Бананы
    23. Яблоки
        1. Красные
        2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
  <body>
    <ul>
      <li>
        Утка по-пекински
        <ul>
          <li>Утка</li>
          <li>Соус</li>
        </ul>
      </li>
      <li>
        Салат Оливье
        <ol>
          <li>Мясо
            <ul>
              <li>
                  Или колбаса
              </li>
            </ul>
          </li>
          <li>Майонез</li>
          <li>Картофель</li>
          <li>Что-то там ещё</li>
        </ol>
      </li>
      <li>Помидоры</li>
      <li>
        Яблоки
        <ol>
          <li>Красные</li>
          <li>Зелёные</li>
        </ol>
      </li>
    </ul>
  </body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
   19935
*    111
--------
   19935
+ 19935
+19935
--------
 2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
  235
*  10
-----
    0
+235
-----
 2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Очень сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
  19935 | 22
 -198     906
 ----
    13
    -0
    --
    135
   -132
   ----
      3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

