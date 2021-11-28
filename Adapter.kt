//Адаптер — это структурный паттерн проектирования, который позволяет объектам с несовместимыми интерфейсами работать вместе.

//Сам интерфейс Адаптера и ниже подклассы с разными интерфейсами
interface Adapter{
    var temperature: Double
}

//Функции для подсчета градусов
public fun convertToCelsius(f: Double): Double = ((f-32)*5)/9
public fun convertToFahrenheit(c: Double): Double = ((c*9)/5)+32
public fun convertToKalvin(c: Double): Double = c+273
public fun convertKelvinToCelsius(k: Double): Double = k-273

//Базовый подкласс с одним значение на вход которое замещает главное значение
class Celsius(override var temperature: Double): Adapter{}

//Подклассы пересчитывающие градусы из цельсия в фаренгейт и обратно и из кельвины в цельсия и обратно
class Fahrenheit(var tempCels: Celsius): Adapter{

    override var temperature: Double
        get() = convertToFahrenheit(tempCels.temperature)
        set(tempFar) {tempCels.temperature = convertToCelsius(tempFar)}
}

//Классы кельвин и фаренгейт с геттером(конвертируем полученное значение в цельсиях в нужное значение) и сеттером(устанавливаем значение кельвина или фаренгейта пересчитанное в кельвины)
class Kalvin(var tempCels: Celsius): Adapter{

    override var temperature: Double
        get() = convertToKalvin(tempCels.temperature)
        set(tempKalvin) {tempCels.temperature = convertKelvinToCelsius(tempKalvin)}
}

//пример
fun main() {
    val tempCels = Celsius(36.6)
    val tempFar = Fahrenheit(tempCels)
    val tempKalv = Kalvin(tempCels)

    println("${tempCels.temperature} C = ${tempFar.temperature} F")
    println("${tempCels.temperature} C = ${tempKalv.temperature} K")

    tempFar.temperature = 100.0
    println("${tempFar.temperature} F = ${tempCels.temperature} C")

    tempKalv.temperature = 100.0
    println("${tempKalv.temperature} K = ${tempCels.temperature} C")
}