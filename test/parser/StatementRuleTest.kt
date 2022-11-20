package parser

import parseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StatementRuleTest {
    @Test
    fun shouldNotRaiseExceptionBecauseThePrintStatementIsWrittenCorrectly() {
        assertDoesNotThrow {
            parseTree("test/testfiles/printStatementIsWrittenCorrectly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheAssignmentStatementIsWrittenCorrectly() {
        assertDoesNotThrow {
            parseTree("test/testfiles/assignmentStatementIsWrittenCorrectly.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseAssignmentStatementIsNotAnExpression() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/printAssignmentStatement.icaro")
        }
    }
}