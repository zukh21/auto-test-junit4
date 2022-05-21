fun main(){
    println(transfer("Visa", amountTransfer = 200000F))
}

// Функция, чтобы проверить комиссия перевода
fun transferFee(cardType: String = "Vk pay", amountTransefer: Float): Float {
    val fee = when (cardType){
        "Visa", "Мир" -> (amountTransefer / 100) * 0.75F
        "Mastercard", "Maestro" -> ((amountTransefer / 100) * 0.6F) + 20
        else -> 0F
    }
    return fee
}

// Функция перевода и вывод на экран
fun transfer( cardType: String, amountPreviousTransfers:Float = 0F, amountTransfer: Float): String {
    val  cards = arrayListOf<String>("Visa", "Мир", "Mastercard", "Maestro")
//    Ограничение сумма переводы
    val maxTransferAtOnce: Float = 15_000F
    val maxTransferPerMonthWithVkPay: Float = 40_000F
    val maxTransferPerMonthWithCard: Float = 600_000F
    val maxTransferPerDayWithCard: Float = 150_000F
//    Комиссия перевода
    val fee = transferFee( cardType = cardType, amountTransefer = amountTransfer)
//    Проверка Сумма перевода с картой и с вк оплатой
    val checkTransferAmountWithCard = checkTransferAmountWithCard(cardType = cardType, amountTransfer = amountTransfer)
    val checkTransferAmountWithVkPay = checkTransferAmountWithVkPay(amountTransfer = amountTransfer, cardType = cardType)
//    Проверка перевода и выводит на экран
    return if (cardType in cards){
        when(checkTransferAmountWithCard){
            -1F -> ("Ваш лимит ограничен! Вы можете перевести ${maxTransferPerDayWithCard} рублей в день.")
            -2F -> ("Ваш лимит ограничен! Вы можете перевести ${maxTransferPerMonthWithCard} рублей в месяц.")
            else -> (("Сумма на перевод - ${amountTransfer}, комиссия - ${fee}," +
                    "снимается - ${amountTransfer + fee}, Ваш последний перевод - ${amountPreviousTransfers}"))
        }
    }else{
        when(checkTransferAmountWithVkPay){
            -1F -> ("Ваш лимит ограничен! Вы можете перевести ${maxTransferAtOnce} рублей в разу.")
            -2F -> ("Ваш лимит ограничен! Вы можете перевести ${maxTransferPerMonthWithVkPay} рублей в месяц.")
            else -> (("Сумма на перевод - ${amountTransfer}, комиссия - ${fee}," +
                    "снимается - ${amountTransfer + fee}, Ваш последний перевод - ${amountPreviousTransfers}"))
        }
    }
}


// Функция, чтобы проверить сумма перевода с картой
fun checkTransferAmountWithCard(amountTransfer: Float, cardType: String, amountPreviousTransfers: Float = 0F): Float{
    val  cards = arrayListOf<String>("Visa", "Мир", "Mastercard", "Maestro")
    val maxTransferPerMonthWithCard: Float = 600_000F
    val maxTransferPerDayWithCard: Float = 150_000F
    return if (amountTransfer > maxTransferPerDayWithCard && cardType in cards && amountTransfer < maxTransferPerMonthWithCard){
        -1F
    }else if ((amountTransfer+amountPreviousTransfers) > maxTransferPerMonthWithCard  && cardType in cards){
        -2F
    }else{
        1F
    }
}


// Функция, чтобы проверить сумма перевода с Вк оплатой
fun checkTransferAmountWithVkPay(amountTransfer: Float, cardType: String, amountPreviousTransfers: Float = 0F): Float{
    val  cards = arrayListOf<String>("Vk pay")
    val maxTransferAtOnce: Float = 15_000F
    val maxTransferPerMonthWithVkPay: Float = 40_000F
    return if (amountTransfer > maxTransferAtOnce && cardType in cards && amountTransfer < maxTransferPerMonthWithVkPay){
        -1F
    }else if ((amountTransfer+amountPreviousTransfers) > maxTransferPerMonthWithVkPay  && cardType in cards){
        -2F
    }else{
        1F
    }
}