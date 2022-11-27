
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class MultipleAnnotation1(
    val name: String = "",
    val arr: Array<String> = []
)

object PageWithRepeatableAnnotations  {
    @MultipleAnnotation1(name = "n1")
    @MultipleAnnotation1(name = "n2", arr = ["a1", "a2"])
    val prop1 = ""
}

fun main() {
    PageWithRepeatableAnnotations::prop1.annotations.filterIsInstance<MultipleAnnotation1>().forEach {
        println()
        println(it.name)
        println(it.arr.joinToString()) //'KotlinReflectionInternalError' will be thrown at this line. This is a regression.
        //Kotlin 1.6 was able to handle it
    }
}