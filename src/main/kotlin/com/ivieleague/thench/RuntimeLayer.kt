package com.ivieleague.thench

sealed interface RuntimeElement { val metadata: Map<String, Any?> }
sealed interface RuntimeTopLevel: RuntimeElement
sealed interface RuntimeDeclaration: RuntimeElement
sealed interface RuntimeStatement: RuntimeElement
sealed interface RuntimeExpression: RuntimeStatement
sealed interface RuntimeParameter: RuntimeElement
sealed interface RuntimeArgument: RuntimeElement

sealed interface RuntimeType

data class RuntimeImport(
    val expression: RuntimeExpression,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeTopLevel
data class RuntimeModule(
    val declarations: List<RuntimeDeclaration>,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeExpression
data class RuntimeTypeDeclaration(
    val name: String,
    val parts: List<RuntimeInParameter>,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeDeclaration
data class RuntimeInParameter(
    val name: String,
    val type: RuntimeType,
    val vararg: Boolean = false,
    val infer: Boolean = false,
    val default: RuntimeExpression? = null,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeParameter
data class RuntimePlistParameterUse(
    val name: String,
    val vararg: Boolean = false,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeParameter
data class RuntimeOutParameter(
    val name: String,
    val type: RuntimeType,
    val vararg: Boolean = false,
    val infer: Boolean = false,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeParameter
data class RuntimeInOutParameter(
    val name: String,
    val type: RuntimeType,
    val vararg: Boolean = false,
    val infer: Boolean = false,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeParameter
data class RuntimeTypeParameter(
    val name: String,
//    val constraint:,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeElement
data class RuntimePlistParameter(
    val name: String,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeElement
data class RuntimeFunctionDeclaration(
    val name: String,
    val plistParameters: List<RuntimePlistParameter>,
    val typeParameters: List<RuntimeTypeParameter>,
    val parameters: List<RuntimeParameter>,
    val returnType: RuntimeType,
    val body: RuntimeExpression,
    override val metadata: Map<String, Any?> = mapOf(),
): RuntimeDeclaration
data class RuntimeAnonymousFunction(
    val explicitParameters: List<RuntimeParameter>? = null,
    val body: RuntimeExpression,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeExpression
data class RuntimeBlock(
    val statements: List<RuntimeStatement>,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeExpression
data class RuntimeConstant(
    val value: Any?,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeExpression
data class RuntimeCall(
    val name: String,
    val typeArguments: Map<String, RuntimeExpression>,
    val arguments: List<RuntimeArgument>,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeElement
data class RuntimeDynamicCall(
    val function: RuntimeExpression,
    val typeArguments: Map<String, RuntimeExpression>,
    val arguments: List<RuntimeArgument>,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeElement
data class RuntimeOrderedArgument(
    val value: RuntimeExpression,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeArgument
data class RuntimeNamedArgument(
    val name: String,
    val value: RuntimeExpression,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeArgument
data class RuntimeReference(
    val name: String,
    val inputs: List<RuntimeParameter>? = null,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeExpression
data class RuntimeAssignment(
    val name: String,
    val value: RuntimeExpression,
    val declaration: RuntimeValueDeclaration? = null,
    override val metadata: Map<String, Any?> = mapOf()
): RuntimeStatement
enum class RuntimeValueDeclaration { Val, Var }
data class RuntimeFunctionType(
    val plistParameters: List<RuntimePlistParameter>,
    val typeParameters: List<RuntimeTypeParameter>,
    val parameters: List<RuntimeParameter>,
    val returnType: RuntimeExpression,
)