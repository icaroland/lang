package parser

import generateParseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StatementRuleTest {
    @Test
    fun shouldNotRaiseExceptionBecauseThePrintStatementIsWrittenCorrectly() {
        assertDoesNotThrow {
            generateParseTree("test/testfiles/printStatementIsWrittenCorrectly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheAssignmentStatementIsWrittenCorrectly() {
        assertDoesNotThrow {
            generateParseTree("test/testfiles/assignmentStatementIsWrittenCorrectly.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseAssignmentStatementIsNotAnExpression() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/printAssignmentStatement.icaro")
        }
    }
}