package mad.nina_gallmetzer_mad

fun main() {
    val generatedNumber = generateNumber()
    var guessedNumber: String
    var m: Int
    var n = 0
    var count = 0

    println("Welcome to the number guessing game! Guess a 4-digit number with no repeating digits.")
    do {
        print("Enter your guess: ")
        guessedNumber = readLine() ?: ""
        if (!isValidInput(guessedNumber)) {
            println("Invalid input.")
            continue
        }
        m = countCorrectDigits(generatedNumber, guessedNumber)
        n = countCorrectPositions(generatedNumber, guessedNumber)
        println("$m:$n")
        count++
    } while (n != 4)

    println("Congratulations! You guessed the number $generatedNumber in $count tries.")
}

fun generateNumber(): String {
    val digits = mutableListOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    digits.shuffle()
    return digits.take(4).joinToString("")
}

fun isValidInput(input: String): Boolean {
    return input.all{ it.isDigit() } && input.length == 4 && input.toSet().size == 4
}

fun countCorrectDigits(number1: String, number2: String): Int {
    var count = 0
    for (digit in number1) {
        if (digit in number2) {
            count++
        }
    }
    return count
}

fun countCorrectPositions(number1: String, number2: String): Int {
    var count = 0
    for (i in 0..3) {
        if (number1[i] == number2[i]) {
            count++
        }
    }
    return count
}
