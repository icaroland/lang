package e2e

import E2ETest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import runIcaroFileWithoutGeneratingClassFile

class StatementTest: E2ETest() {
    
    @Test
    fun shouldPrintToStdoutTheNumberTreeAfterAssignment() {
        runIcaroFileWithoutGeneratingClassFile("test/testfiles/assignNumber.icaro")

        assertCaptureStdoutBufferIsEqualTo(listOf(3))
    }

    @Test
    fun shouldPrintToStdoutTheNumberFourAfterAssignVariableToOtherOne() {
        runIcaroFileWithoutGeneratingClassFile("test/testfiles/assignVariableToOtherOne.icaro")

        assertCaptureStdoutBufferIsEqualTo(listOf(4))
    }

    @Test
    fun shouldThrowWhenUndefinedVariableAssignment() {
        assertThrows<IllegalStateException> {
            runIcaroFileWithoutGeneratingClassFile("test/testfiles/assignUndefinedVariableToOtherOne.icaro")
        }
    }
}