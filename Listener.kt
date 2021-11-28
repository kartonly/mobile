//Наблюдатель — это поведенческий паттерн проектирования, который создаёт механизм подписки, позволяющий одним объектам следить и реагировать на события, происходящие в других объектах.

import kotlin.properties.Delegates

//Сам интерфейс Наблюдателя
interface IListener {
    fun onTextChanged(new: String)
}

//Даём объекту новое значение
class Rewrite : IListener {
    private var text = ""
    override fun onTextChanged(new: String) {
        text = new
    }
}

//Создаем объект наблюдателя, который наблюдает за изменениями и передаёт в функцию onTextChanged новое значение
class Observ {
    var listener: IListener? = null
    var text: String by Delegates.observable(initialValue = "", onChange = { prop, old, new ->
        listener?.onTextChanged(new)
    })
}

//Пример работы
fun main() {
    val txt = Observ()
    txt.listener = Rewrite()

    txt.text = "Было"
    println(txt.text)
    txt.text = "Стало"
    println(txt.text)
}
