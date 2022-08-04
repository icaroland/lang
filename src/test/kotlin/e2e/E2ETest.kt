package e2e

import org.junit.jupiter.api.BeforeEach
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

open class E2ETest {
    private val originalStdout: PrintStream = System.out
    private val captureStdoutBuffer = ByteArrayOutputStream()

    @BeforeEach
    fun setUpTheCaptureBuffer() {
        System.setOut(PrintStream(captureStdoutBuffer))
    }

    protected fun assertCaptureStdoutBufferIsEqualTo(expectedResult: List<Int>) {
        System.setOut(originalStdout)

        val actualResult = captureStdoutBuffer.toString().trim().split("\n").map { it.toInt() }
        
        assertEquals(expectedResult, actualResult)
    }
}