package e2e.statement

import e2e.E2ETest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import runIcaroFileWithoutGeneratingClassFile

class StatementE2ETest: E2ETest() {
    
    @Test
    fun shouldPrintToStdoutTheNumberTreeAfterAssignment() {
        runIcaroFileWithoutGeneratingClassFile("src/test/kotlin/e2e/statement/testfiles/assignNumber.icaro")

        assertCaptureStdoutBufferIsEqualTo(listOf(3))
    }

    @Test
    fun shouldPrintToStdoutTheNumberFourAfterAssignVariableToOtherOne() {
        runIcaroFileWithoutGeneratingClassFile("src/test/kotlin/e2e/statement/testfiles/assignVariableToOtherOne.icaro")

        assertCaptureStdoutBufferIsEqualTo(listOf(4))
    }

    @Test
    fun shouldThrowWhenUndefinedVariableAssignment() {
        assertThrows<IllegalStateException> {
            runIcaroFileWithoutGeneratingClassFile("src/test/kotlin/e2e/statement/testfiles/assignUndefinedVariableToOtherOne.icaro")
        }
    }
}