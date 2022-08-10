package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class BindingTest {
    companion object {
        val MOCK_AES_DOUBLE_NON_POS = AesName("mock_aes_double_np")
        val MOCK_AES_STRING_MAP_NON_POS = AesName("mock_aes_string_mnp")
        val MOCK_AES_COLOR_MAP_NON_POS = AesName("mock_aes_string_mnp")
        val MOCK_AES_NON_SCLB_POS = AesName("mock_aes_non_sclb_pos")
        val MOCK_AES_SCLB_POS = AesName("mock_aes_sclb_pos")
    }

    class MockAesDoubleNonPos(override val context: BindingContext) : NonPositionalAes<Double> {
        override val name: AesName = MOCK_AES_DOUBLE_NON_POS
    }

    class MockAesStringMapNonPos(override val context: BindingContext) : MappableNonPositionalAes<String> {
        override val name: AesName = MOCK_AES_STRING_MAP_NON_POS
    }

    class MockAesNonSclbPos(override val context: BindingContext) : NonScalablePositionalAes {
        override val name: AesName = MOCK_AES_NON_SCLB_POS
    }

    class MockAesSclbPos(override val context: BindingContext) : ScalablePositionalAes {
        override val name: AesName = MOCK_AES_SCLB_POS
    }

    class MockAesColorMapNonPos(override val context: BindingContext) : MappableNonPositionalAes<Color>  {
        override val name: AesName = MOCK_AES_COLOR_MAP_NON_POS
    }

    class TestContext : BaseBindingContext() {
        override var data: MutableNamedData = mutableMapOf()
        val mockAesDoubleNonPos = MockAesDoubleNonPos(this)
        val mockAesStringMapNonPos = MockAesStringMapNonPos(this)
        val mockAesColorMapNonPos = MockAesColorMapNonPos(this)
        val mockAesNonSclbPos = MockAesNonSclbPos(this)
        val mockAesSclbPos = MockAesSclbPos(this)
    }

    @Test
    fun testSetting() {
        //  val mockAesDouble = NonPositionalAes<Double>("mock_aes")
        val valueDouble = 34.831
        val context = TestContext().apply {
            mockAesDoubleNonPos(valueDouble)
        }
        assertEquals<Map<AesName, Setting>>(
            mapOf(MOCK_AES_DOUBLE_NON_POS to NonPositionalSetting(MOCK_AES_DOUBLE_NON_POS, valueDouble)),
            context.bindingCollector.settings.toMap()
        )

        // val mockAesString = NonPositionalAes<String>("mock_aes_string")
        val valueString = "MOCK_VALUE"
        context.apply {
            mockAesStringMapNonPos(valueString)
        }
        assertEquals<Map<AesName, Setting>>(
            mapOf(
                MOCK_AES_DOUBLE_NON_POS to NonPositionalSetting(MOCK_AES_DOUBLE_NON_POS, valueDouble),
                MOCK_AES_STRING_MAP_NON_POS to NonPositionalSetting(MOCK_AES_STRING_MAP_NON_POS, valueString)
            ),
            context.bindingCollector.settings.toMap()
        )
    }

    @Test
    fun testMappingNonScalable() {
        //  val mockAes = NonScalablePositionalAes("mock_aes")
        val mockSource = source<Double>("mock_source")
        val context = TestContext().apply {
            mockAesNonSclbPos(mockSource)
        }
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                MOCK_AES_NON_SCLB_POS to NonScalablePositionalMapping(
                    MOCK_AES_NON_SCLB_POS,
                    mockSource,
                    typeOf<Double>()
                )
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingUnscaled() {
        //   val mockAes = MappableNonPositionalAes<Int>("mock_aes")
        val mockSource = source<Int>("mock_source")
        val context = TestContext().apply {
            mockAesStringMapNonPos(mockSource)
        }
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                MOCK_AES_STRING_MAP_NON_POS to ScaledUnspecifiedDefaultNonPositionalMapping<Int, String>(
                    MOCK_AES_STRING_MAP_NON_POS,
                    mockSource.scaled(),
                    typeOf<Int>()
                )
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingScaledUnspecified() {
      //  val mockAes = ScalablePositionalAes("mock_aes")
        val mockSource = source<String>("mock_source")
        val context = TestContext().apply {
            mockAesSclbPos(mockSource.scaled())
        }
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                MOCK_AES_SCLB_POS to ScaledUnspecifiedDefaultPositionalMapping(
                    MOCK_AES_SCLB_POS, mockSource.scaled(), typeOf<String>()
                )
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingScaledPositionalDefault() {
       // val mockAes = ScalablePositionalAes("mock_aes")
        val mockSource = source<Float>("mock_source")
        val context = TestContext().apply {
            mockAesSclbPos(mockSource.scaled(continuousPos()))
        }
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                MOCK_AES_SCLB_POS to ScaledPositionalDefaultMapping(
                    MOCK_AES_SCLB_POS, mockSource.scaled(
                        continuousPos()
                    ), typeOf<Float>()
                )
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingScaledNonPositionalDefault() {
      //  val mockAes = MappableNonPositionalAes<LineType>("mock_aes")
        val mockSource = source<String>("mock_source")
        val context = TestContext().apply {
            mockAesStringMapNonPos(mockSource.scaled(categorical()))
        }
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                MOCK_AES_STRING_MAP_NON_POS to ScaledNonPositionalDefaultMapping<String, String>(
                    MOCK_AES_STRING_MAP_NON_POS, mockSource.scaled(
                        categorical()
                    ), typeOf<String>()
                )
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingScaledPositional() {
       // val mockAes = ScalablePositionalAes("mock_aes")
        val mockSource = source<String>("mock_source")
        val scale = categoricalPos(
            categories = listOf("cat1", "cat2", "cat3")
        )
        val context = TestContext().apply {
            mockAesSclbPos(mockSource.scaled(scale))
        }
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                MOCK_AES_SCLB_POS to ScaledPositionalMapping(MOCK_AES_SCLB_POS, mockSource.scaled(scale), typeOf<String>())
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingScaledNonPositional() {
      //  val mockAes = MappableNonPositionalAes<Color>("mock_aes")
        val mockSource = source<Int>("mock_source")
        val scale = continuous<Int, Color>(
            rangeLimits = Color.fromRGB(1, 1, 1) to Color.fromRGB(1, 100, 100)
        )
        val context = TestContext().apply {
            mockAesColorMapNonPos(mockSource.scaled(scale))
        }
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                MOCK_AES_STRING_MAP_NON_POS to ScaledNonPositionalMapping(
                    MOCK_AES_STRING_MAP_NON_POS,
                    mockSource.scaled(scale),
                    typeOf<Int>(),
                    //typeOf<Color>()
                )
            ),
            context.bindingCollector.mappings
        )
    }
}



