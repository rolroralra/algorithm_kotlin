package algorithm

import java.lang.management.ManagementFactory

fun printMemoryUsage() {
    val memoryMXBean = ManagementFactory.getMemoryMXBean()
    val heapMemoryUsage = memoryMXBean.heapMemoryUsage
    val nonHeapMemoryUsage = memoryMXBean.nonHeapMemoryUsage

    println("Heap Memory Usage: ${heapMemoryUsage.used / (1024 * 1024)} MB")
    println("Non-Heap Memory Usage: ${nonHeapMemoryUsage.used / (1024 * 1024)} MB")
}