import IcaroParser.*
import org.antlr.v4.runtime.tree.TerminalNode
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*


class IcaroCompiler : IcaroBaseVisitor<Unit>() {
    /**
     * Use this index for the local variable (STORE instruction) and then increment it by one
     */
    private var nextLocalVariableIndex = 0

    private var currentAsmMethodVisitor: MethodVisitor? = null

    private var localVariableNamesToLocalVariableIndexes = mutableMapOf<String, Int>()

    fun generateBytecode(parseTree: IcaroFileContext, className: String): ByteArray {
        val asmClassWriter = ClassWriter(ClassWriter.COMPUTE_FRAMES)
        asmClassWriter.visit(V11, ACC_PUBLIC + ACC_SUPER, className, null, "java/lang/Object", null)

        addConstructor(asmClassWriter)

        addMain(asmClassWriter, parseTree)

        return asmClassWriter.toByteArray()
    }

    private fun addMain(asmClassWriter: ClassWriter, parseTree: IcaroFileContext) {
        currentAsmMethodVisitor =
            asmClassWriter.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)

        currentAsmMethodVisitor?.visitCode()

        visitIcaroFile(parseTree)
        
        endMethod(RETURN)
    }

    private fun addConstructor(asmClassWriter: ClassWriter) {
        currentAsmMethodVisitor = asmClassWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null)
        currentAsmMethodVisitor?.visitVarInsn(ALOAD, 0)
        currentAsmMethodVisitor?.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
        
        endMethod(RETURN)
    }

    private fun endMethod(returnOpCode: Int) {
        currentAsmMethodVisitor?.visitInsn(returnOpCode)

        // these numbers will be ignored because of the following code: ClassWriter(ClassWriter.COMPUTE_FRAMES)
        currentAsmMethodVisitor?.visitMaxs(-42, -42)
        currentAsmMethodVisitor?.visitEnd()
    }

    private fun checkIfNegativeInteger(subtractionOperator: TerminalNode?) {
        if (subtractionOperator != null) {
            currentAsmMethodVisitor?.visitInsn(INEG)
        }
    }

    override fun visitAssignment_statement(ctx: Assignment_statementContext) {
        visit(ctx.expression())

        if (localVariableNamesToLocalVariableIndexes.containsKey(ctx.VARIABLE_IDENTIFIER().text)) {
            localVariableNamesToLocalVariableIndexes[ctx.VARIABLE_IDENTIFIER().text]?.let { index ->
                currentAsmMethodVisitor?.visitVarInsn(ISTORE, index)
            }
        } else {
            currentAsmMethodVisitor?.visitVarInsn(ISTORE, nextLocalVariableIndex)

            localVariableNamesToLocalVariableIndexes[ctx.VARIABLE_IDENTIFIER().text] = nextLocalVariableIndex

            nextLocalVariableIndex++
        }
    }

    override fun visitPrint_statement(ctx: Print_statementContext) {
        currentAsmMethodVisitor?.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")

         visit(ctx.expression())

        currentAsmMethodVisitor?.visitMethodInsn(
            INVOKESTATIC, "java/lang/Integer", "toString", "(I)Ljava/lang/String;", false
        )

        currentAsmMethodVisitor?.visitMethodInsn(
            INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false
        )
    }

    override fun visitExpressionInRoundBrackets(ctx: ExpressionInRoundBracketsContext) {
        visit(ctx.expression())

        checkIfNegativeInteger(ctx.SUBTRACTION_OPERATOR())
    }

    override fun visitVariable(ctx: VariableContext) {
        if (!localVariableNamesToLocalVariableIndexes.containsKey(ctx.VARIABLE_IDENTIFIER().text))
            error("the variable ${ctx.VARIABLE_IDENTIFIER().text} isn't declared yet")

        localVariableNamesToLocalVariableIndexes[ctx.VARIABLE_IDENTIFIER().text]?.let { index ->
            currentAsmMethodVisitor?.visitVarInsn(ILOAD, index)

            checkIfNegativeInteger(ctx.SUBTRACTION_OPERATOR())
        }
    }

    override fun visitAdditionOrSubtraction(ctx: AdditionOrSubtractionContext) {
        visitChildren(ctx)

        when (ctx.SUBTRACTION_OPERATOR()) {
            null -> currentAsmMethodVisitor?.visitInsn(IADD)
            else -> currentAsmMethodVisitor?.visitInsn(ISUB)
        }
    }

    override fun visitMultiplicationOrDivision(ctx: MultiplicationOrDivisionContext) {
        visitChildren(ctx)

        when (ctx.DIVISION_OPERATOR()) {
            null -> currentAsmMethodVisitor?.visitInsn(IMUL)
            else -> {
                if (ctx.expression(1).text == "0")
                    throw error("can't divide by 0")

                currentAsmMethodVisitor?.visitInsn(IDIV)
            }
        }
    }

    override fun visitIntNumber(ctx: IntNumberContext) {
        currentAsmMethodVisitor?.visitLdcInsn(ctx.POSITIVE_INT_NUMBER().text.toInt())

        checkIfNegativeInteger(ctx.SUBTRACTION_OPERATOR())
    }
}