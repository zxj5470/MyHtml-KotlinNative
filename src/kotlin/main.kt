import kotlinx.cinterop.*
import libmyhtml.*
import platform.posix.strlen

fun main(args: Array<String>) {
    val html = "<div><span>HTML</span></div>"
    val myhtml = myhtml_create()
    myhtml_init(myhtml, MyHTML_OPTIONS_DEFAULT, 1, 0)

    val tree = myhtml_tree_create()
    myhtml_tree_init(tree, myhtml)
    myhtml_parse(tree, MyENCODING_UTF_8, html, strlen(html))
    memScoped {
        val str = alloc<mycore_string_raw>()
        myhtml_serialization_tree_buffer(myhtml_tree_get_document(tree), str.ptr)

        //toKString is correct.Using `toString` will return the address of CPointer<ByteVar>
        val data=str.data!!.toKString()
        println(data)

        mycore_string_raw_destroy(str.ptr, false)
        myhtml_tree_destroy(tree)
        myhtml_destroy(myhtml)
    }
}