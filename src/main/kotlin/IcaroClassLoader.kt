class IcaroClassLoader : ClassLoader() {
    fun defineClass(name: String, bytecode: ByteArray): Class<*> = defineClass(name, bytecode, 0, bytecode.size)
}