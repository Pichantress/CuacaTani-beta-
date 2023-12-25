package com.capstone.cuacatani.ui.predict

import org.tensorflow.lite.Interpreter
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder

class TensorFlowLiteInterpreter(modelPath: String) {
    private val interpreter: Interpreter

    init {
        val modelFile = File(modelPath)
        interpreter = Interpreter(modelFile)
    }

    fun runInference(input: FloatArray): Float {
        // Assuming your model has one input tensor and one output tensor
        val inputTensorIndex = 0
        val outputTensorIndex = 0

        // Prepare input buffer
        val inputBuffer = ByteBuffer.allocateDirect(input.size * 4) // 4 bytes per float
        inputBuffer.order(ByteOrder.nativeOrder())
        inputBuffer.asFloatBuffer().put(input)

        // Run inference
        val outputBuffer = Array(1) { FloatArray(1) } // Assuming single output value
        interpreter.run(inputBuffer, outputBuffer)

        // Get the result
        return outputBuffer[outputTensorIndex][0]
    }

    fun close() {
        interpreter.close()
    }
}