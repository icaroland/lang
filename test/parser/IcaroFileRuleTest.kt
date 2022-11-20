package parser

import generateParseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IcaroFileRuleTest {
    @Test
    fun shouldNotRaiseExceptionBecauseMoreStatementWithNewLines() {
        assertDoesNotThrow {
            generateParseTree("test/testfiles/moreStatementWithNewLines.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseSingleStatement() {
        assertDoesNotThrow {
            generateParseTree("test/testfiles/singleStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseNoNewLinesWhenMoreStatement() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/noNewLinesWhenMoreStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseEmptyFile() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/emptyFile.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseSpacesBeforeStatement() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/spacesBeforeStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseSpacesAfterStatement() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/spacesAfterStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseNewLinesAfterStatement() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/newLinesAfterStatement.icaro")
        }
    }
}