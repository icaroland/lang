import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.misc.ParseCancellationException
import java.io.File

const val SOURCE_DIR_NAME = "src"
const val MAIN_SOURCE_FILE = "main.ic"
const val BINARY_DIR_NAME = ".bin"
const val CLASSES_DIR_NAME = "classes"

@Throws(IllegalStateException::class)
fun generatedBytecode(icaroFilePath: String): ByteArray =
    IcaroCompiler().generatedBytecode(parseTree(icaroFilePath), className(icaroFilePath))

@Throws(ParseCancellationException::class)
fun parseTree(icaroFilePath: String): IcaroParser.IcaroFileContext {
    class IcaroExceptionThrower : BaseErrorListener() {

        @Throws(ParseCancellationException::class)
        override fun syntaxError(
            recognizer: Recognizer<*, *>?,
            offendingSymbol: Any?,
            line: Int,
            charPositionInLine: Int,
            msg: String,
            e: RecognitionException?
        ) {
            throw ParseCancellationException("line $line:$charPositionInLine $msg")
        }
    }

    val lexer = IcaroLexer(CharStreams.fromFileName(icaroFilePath))
    val parser = IcaroParser(CommonTokenStream(lexer))

    lexer.removeErrorListeners()
    lexer.addErrorListener(IcaroExceptionThrower())

    parser.removeErrorListeners()
    parser.addErrorListener(IcaroExceptionThrower())

    return parser.icaroFile()
}

fun className(icaroFilePath: String) =
    File(icaroFilePath.substringAfterLast('/')).nameWithoutExtension
