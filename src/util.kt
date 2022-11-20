import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.misc.ParseCancellationException
import java.io.File

const val SOURCE_DIR_NAME = "src"
const val MAIN_SOURCE_FILE = "main.ic"
const val BINARY_DIR_NAME = ".bin"
const val CLASSES_DIR_NAME = "classes"

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

fun getClassName(icaroOrClassFilePath: String) = File(icaroOrClassFilePath.substringAfterLast('/')).nameWithoutExtension
