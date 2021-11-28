//Фабричный метод — это порождающий паттерн проектирования, который определяет общий интерфейс для создания объектов в суперклассе, позволяя подклассам изменять тип создаваемых объектов.

//Интерфейс фабрики
interface IFactory {
    fun Learning(): String
}

//Класс, наследующий наш интерфейс и возвращающий строку. Реализует ту же логику и перезаписывает функцию
class JavaScript : IFactory {
    override fun Learning():String {
        return "I learn JavaScript"
    }
}

class Kotlin : IFactory {
    override fun Learning():String {
        return "I learn Kotlin"
    }
}

//Возможные значения
enum class Language {
    JavaScript, Kotlin
}

//Функция фабрики. Она порождает объекты классов, наследующих общий интерфейс, по единому сценарию.
fun Factory(lang: Language): IFactory? {
    when (lang) {
        Language.JavaScript -> return JavaScript()
        Language.Kotlin -> return Kotlin()
        else -> return null
    }
}

fun main() {
    println(Factory(Language.JavaScript)?.Learning())
    println(Factory(Language.Kotlin)?.Learning())
}