package e2e

import E2ETest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import runIcaroFileWithoutGeneratingClassFile

class ExpressionTest : E2ETest() {
    
    @Test
    fun shouldCalculateTheExpressionOnlyWithIntegerCorrectly() {
        runIcaroFileWithoutGeneratingClassFile("test/testfiles/integerMathExpressions.icaro")
        
        assertCaptureStdoutBufferIsEqualTo(listOf(1))
    }
    
    @Test
    fun shouldNotConsiderZerosBeforeIntegerNumber() {
        runIcaroFileWithoutGeneratingClassFile("test/testfiles/zerosBeforeInteger.icaro")

        assertCaptureStdoutBufferIsEqualTo(listOf(50))
    }

    @Test
    fun shouldRaiseAnExceptionWhenDivideByZero() {
        assertThrows<IllegalStateException> {
            runIcaroFileWithoutGeneratingClassFile("test/testfiles/divideByZeroExpression.icaro")
        }
    }
 
    @Test
    fun shouldCalculateAllTheGivenMathExpressionCorrectly() {
        runIcaroFileWithoutGeneratingClassFile("test/testfiles/mathExpressions.icaro")

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