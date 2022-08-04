package parser.icarofile

import generateParseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IcaroFileParserRuleTest {
    @Test
    fun shouldNotRaiseExceptionBecauseMoreStatementWithNewLines() {
        assertDoesNotThrow {
            generateParseTree("src/test/kotlin/parser/icarofile/testfiles/moreStatementWithNewLines.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseSingleStatement() {
        assertDoesNotThrow {
            generateParseTree("src/test/kotlin/parser/icarofile/testfiles/singleStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseNoNewLinesWhenMoreStatement() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/parser/icarofile/testfiles/noNewLinesWhenMoreStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseEmptyFile() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/parser/icarofile/testfiles/emptyFile.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseSpacesBeforeStatement() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/parser/icarofile/testfiles/spacesBeforeStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseSpacesAfterStatement() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/parser/icarofile/testfiles/spacesAfterStatement.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseNewLinesAfterStatement() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/parser/icarofile/testfiles/newLinesAfterStatement.icaro")
        }
    }
}