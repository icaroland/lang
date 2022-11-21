package parser

import parseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IcaroFileRuleTest {
    @Test
    fun shouldNotRaiseExceptionBecauseMoreStatementWithNewLines() {
        assertDoesNotThrow {
            parseTree("test/testfiles/moreStatementWithNewLines.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseSingleStatement() {
        assertDoesNotThrow {
            parseTree("test/testfiles/singleStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseNoNewLinesWhenMoreStatement() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/noNewLinesWhenMoreStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseEmptyFile() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/emptyFile.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseSpacesBeforeStatement() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/spacesBeforeStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseSpacesAfterStatement() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/spacesAfterStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseNewLinesAfterStatement() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/newLinesAfterStatement.icaro")
        }
    }
}