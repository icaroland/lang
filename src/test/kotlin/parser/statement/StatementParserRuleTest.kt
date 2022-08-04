package parser.statement

import generateParseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StatementParserRuleTest {
    @Test
    fun shouldNotRaiseExceptionBecauseThePrintStatementIsWrittenCorrectly() {
        assertDoesNotThrow {
            generateParseTree("src/test/kotlin/parser/statement/testfiles/printStatementIsWrittenCorrectly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheAssignmentStatementIsWrittenCorrectly() {
        assertDoesNotThrow {
            generateParseTree("src/test/kotlin/parser/statement/testfiles/assignmentStatementIsWrittenCorrectly.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseAssignmentStatementIsNotAnExpression() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/parser/statement/testfiles/printAssignmentStatement.icaro")
        }
    }
}