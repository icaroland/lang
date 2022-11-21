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

fun runIcaroFileWithoutGeneratingClassFile(icaroFilePath: String) {
    class IcaroClassLoader : ClassLoader() {
        fun defineClass(name: String, bytecode: ByteArray): Class<*> = defineClass(name, bytecode, 0, bytecode.size)
    }

    fun runBytecode(className: String, bytecode: ByteArray) {
        IcaroClassLoader().defineClass(className, bytecode).getMethod("main", Array<String>::class.java)
            .invoke(null, arrayOf<String>())
    }

    runBytecode(className(icaroFilePath), generatedBytecode(icaroFilePath))
}
