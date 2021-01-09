package cases

import client.RetrofitClient
import io.kotest.assertions.asClue
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.swagger.petstore.api.StoreApi
import io.swagger.petstore.model.Order
import org.apache.http.HttpStatus.SC_OK
import org.junit.jupiter.api.Test
import utils.AllureStep.step
import kotlin.random.Random
import config.Credentials as Creds

class StoreTests {

    private val store =
        RetrofitClient().withBaseUrl(Creds.baseUrl).withApiKey(Creds.apiKey).createService(StoreApi::class.java)

    @Test
    fun `Get order by ID`() {
        /* Arrange */
        val givenOrderId: Long = 1
        val expectedStatusCode = SC_OK

        /* Act */
        val response = step("Find purchase order by ID '$givenOrderId'") {
            store.getOrderById(orderId = givenOrderId).execute()
        }

        /* Assert */
        step("Assert StatusCode and OrderId") {
            assertSoftly {
                response.asClue {
                    it.code() shouldBe expectedStatusCode
                    it.body()?.id shouldBe givenOrderId
                }
            }
        }
    }

    @Test
    fun `Place order`() {
        /* Arrange */
        val givenOrder = Order(id = Random.nextLong(1_000_000), quantity = 0, petId = 0, complete = false)
        val expectedStatusCode = SC_OK

        /* Act */
        val response = step("Place an order with ID '${givenOrder.id}'") {
            store.placeOrder(body = givenOrder).execute()
        }

        /* Assert */
        step("Assert StatusCode and Check order creation") {
            assertSoftly {
                response.asClue {
                    it.code() shouldBe expectedStatusCode
                    it.body() shouldBe givenOrder
                }
            }
        }
    }

}