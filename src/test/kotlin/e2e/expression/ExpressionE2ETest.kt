package e2e.expression

import e2e.E2ETest
import generateBytecode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import runIcaroFileWithoutGeneratingClassFile

class ExpressionE2ETest : E2ETest() {
    
    @Test
    fun shouldCalculateTheExpressionOnlyWithIntegerCorrectly() {
        runIcaroFileWithoutGeneratingClassFile("src/test/kotlin/e2e/expression/testfiles/integerMathExpressions.icaro")
        
        assertCaptureStdoutBufferIsEqualTo(listOf(1))
    }
    
    @Test
    fun shouldNotConsiderZerosBeforeIntegerNumber() {
        runIcaroFileWithoutGeneratingClassFile("src/test/kotlin/e2e/expression/testfiles/zerosBeforeInteger.icaro")

        assertCaptureStdoutBufferIsEqualTo(listOf(50))
    }

    @Test
    fun shouldRaiseAnExceptionWhenDivideByZero() {
        assertThrows<IllegalStateException> {
            runIcaroFileWithoutGeneratingClassFile("src/test/kotlin/e2e/expression/testfiles/divideByZeroExpression.icaro")
        }
    }
 
    @Test
    fun shouldCalculateAllTheGivenMathExpressionCorrectly() {
        runIcaroFileWithoutGeneratingClassFile("src/test/kotlin/e2e/expression/testfiles/mathExpressions.icaro")

        assertCaptureStdoutBufferIsEqualTo(
            listOf(
                1,
                15,
                30,
                30,
                120,
                10,
                -10,
                300,
                5,
                -1,
                10000,
                10000,
                2,
                1001,
                29,
                0,
                0,
                -9000,
                -50,
                2,
                37,
                119601,
                -177776,
                4,
                -99,
                10
            )
        )
    }
}