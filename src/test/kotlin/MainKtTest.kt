import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.Assertions

class MainKtTest {

    internal class MainKtTest {
        val cardType = "Visa"
        val vkPay = "Vk pa"

        @Test
        fun transferFee() {
            val result = transferFee("Visa", 2000F)
            Assertions.assertEquals(15F, result)
        }

        @Test
        fun checkTransferAmountWithCardFirstResult() {
            val result = checkTransferAmountWithCard(2_000_00F, cardType)
            Assertions.assertEquals(-1F, result)
        }
        @Test
        fun checkTransferAmountWithCardSecondResult() {
            val result = checkTransferAmountWithCard(8_000_000F, cardType)
            Assertions.assertEquals(-2F, result)
        }
        @Test
        fun checkTransferAmountWithCardThirdResult() {
            val result = checkTransferAmountWithCard(60_000F, cardType)
            Assertions.assertEquals(1F, result)
        }

        @Test
        fun checkTransferAmountWithVkPayFirstResult() {
            val result = checkTransferAmountWithVkPay(20_000F, vkPay)
            Assertions.assertEquals(-1F, result)
        }
        @Test
        fun checkTransferAmountWithVkPaySecondResult() {
            val result = checkTransferAmountWithVkPay(600_000F, vkPay)
            Assertions.assertEquals(-2F, result)
        }
        @Test
        fun checkTransferAmountWithVkPayThirdResult() {
            val result = checkTransferAmountWithVkPay(3_000F, vkPay)
            Assertions.assertEquals(1F, result)
        }
    }
}